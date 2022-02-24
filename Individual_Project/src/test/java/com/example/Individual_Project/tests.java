package com.example.Individual_Project;

import com.example.Individual_Project.business.Admin.AllAccount;
import com.example.Individual_Project.business.Login;
import com.example.Individual_Project.business.NotLogedIn.NotLogedIn;
import com.example.Individual_Project.model.NormalUser;
import com.example.Individual_Project.model.Products.Genre;
import com.example.Individual_Project.model.Products.Product;
import com.example.Individual_Project.model.Products.ProductType;
import com.example.Individual_Project.model.Products.Tag;
import com.example.Individual_Project.model.User;
import com.example.Individual_Project.model.Users.Account;
import com.example.Individual_Project.repository.Test.Admin.DbAllAccount;
import com.example.Individual_Project.repository.Test.Admin.DbAllProduct;
import com.example.Individual_Project.repository.Test.NormalUser.DbMyPorduct;
import com.example.Individual_Project.repository.Test.NotLogedIn.DbNotLogedIn;
import com.example.Individual_Project.repository.Test.NotLogedIn.DbViewProducts;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class tests {

    /*User*/
        /*Login*/
        @Test
        public void Test_Login_With_Correct_Info() {
            // Arrange
            final Login login = new NotLogedIn(new DbNotLogedIn());

            // Act
            User user =  login.GetAccount("Lars","Lars");

            // Assert
            assertNotEquals(null, user);
        }
        @Test
        public void Test_Login_With_InCorrect_Info() {
            // Arrange
            final Login login = new NotLogedIn(new DbNotLogedIn());
            // Act
            User user =  login.GetAccount("","");

            // Assert
            assertNotNull(user);
        }


        /* to do */
        @Test
        public void Test_AddUser() {
            // Arrange
            final Login login = new NotLogedIn(new DbNotLogedIn());

            // Act
            User user =  login.GetAccount("","");

            // Assert
            assertNotNull( user);
        }

        /*My Account*/
        @Test
        public void Test_UpdateAccount() {
            // Arrange
            final Login login = new NotLogedIn(new DbNotLogedIn());

            // Act
            User user =  login.GetAccount("","");

            // Assert
            assertNull(user);
        }

        /*to do end*/

        /*All users*/
        @Test
        public void Test_GetAllAcount() {
            // Arrange
            final AllAccount AllAcounts = new AllAccount(new DbAllAccount());

            // Act
            List<User> users = AllAcounts.GetAllAccounts();

            // Assert
            assertEquals( 2,users.size());
        }


    /*product*/
        /*View products*/
        @Test
        public void Test_ViewProduct_Get_All_Products() {
            // Arrange
            DbViewProducts Viewproduct = new DbViewProducts();
            // Act
            List<Product> products = Viewproduct.GetAllProducts();
            // Assert
            assertEquals(1, products.size());
        }
        @Test
        public void Test_ViewProduct_Get_Product_by_id() {
            // Arrange
            DbViewProducts Viewproduct = new DbViewProducts();
            // Act
            Product product = Viewproduct.GetProduct(1);
            // Assert
            assertEquals(1, product.getProductID());
        }
        @Test
        public void Test_ViewProduct_Get_Products_by_name() {
            // Arrange
            DbViewProducts Viewproduct = new DbViewProducts();
            // Act
            List<Product> products = Viewproduct.GetProduct("");
            // Assert
            assertEquals(0, products.size());
        }
        @Test
        public void Test_ViewProduct_Get_Products_by_name_No_name_entered() {
            // Arrange
            DbViewProducts Viewproduct = new DbViewProducts();
            // Act
            List<Product> products = Viewproduct.GetProduct("pokemon diamond");
            // Assert
            assertEquals(1, products.size());
        }
        @Test
        public void Test_ViewProduct_Get_Products_by_name_Wrong_name_entered() {
            // Arrange
            DbViewProducts Viewproduct = new DbViewProducts();
            // Act
            List<Product> products = Viewproduct.GetProduct("pokemon diamond2");
            // Assert
            assertEquals(0, products.size());
        }

        /*My products*/
        @Test
        public void Test_MyProducts_Get_All_Product() {
            // Arrange
            DbMyPorduct MyProduct = new DbMyPorduct();
            // Act
            List<Product> products = MyProduct.GetAllMyProducts(1);
            // Assert
            assertEquals(1, products.size());
        }
        @Test
        public void Test_MyProducts_Get_Product_by_id() {
            // Arrange
            DbMyPorduct MyProduct = new DbMyPorduct();
            // Act
            Product product = MyProduct.GetProduct(1);
            // Assert
            assertEquals(1, product.getProductID());
        }
        @Test
        public void Test_MyProducts_Add_Product() {
            // Arrange
            DbMyPorduct MyProduct = new DbMyPorduct();

            Account lars2account = new Account("Lars2", "Lars2");
            User lars2 = new NormalUser(2,"Lars2", "Kluijtmans2", "lars.kluijtmans@gmail.com2",2345679, lars2account);

            Tag tag1 = new Tag(1,"NormalGame");
            Tag tag2 = new Tag(2,"VeryOldGame");
            Tag tag3 = new Tag(3,"Most popular");

            List<Tag> tags = new ArrayList<>();
            tags.add(tag1);
            tags.add(tag2);
            tags.add(tag3);

            Product NewProduct = new Product(3,"pokemon Pearl", "Pokemon 2004", "Pokemon", 2004, "Great", Genre.JRPG,"Pokemon pearl a clasic game ..", tags, ProductType.Game, lars2);

            // Act

            boolean result = MyProduct.AddProduct(NewProduct);
            Product product = MyProduct.GetProduct(3);

            // Assert
            assertTrue(result);
            assertEquals(NewProduct, product);
        }
        @Test
        public void Test_MyProducts_Delete_Product() {
            // Arrange
            DbMyPorduct MyProduct = new DbMyPorduct();

            // Act
            boolean result = MyProduct.DeleteProduct(1);
            Product product = MyProduct.GetProduct(1);

            // Assert
            assertTrue(result);
            assertNull(product);
        }
        @Test
        public void Test_MyProducts_Update_Product() {
            // Arrange
            DbMyPorduct MyProduct = new DbMyPorduct();

            Account lars1account = new Account("Lars", "Lars");
            User lars1 = new NormalUser(1,"Lars", "Kluijtmans", "lars.kluijtmans@gmail.com",1234567, lars1account);

            Tag tag1 = new Tag(1,"NormalGame");

            List<Tag> tags = new ArrayList<>();
            tags.add(tag1);

            Product NewProduct = new Product(1,"pokemon Pearl", "Pokemon 2004", "Pokemon Pearl", 2004, "Good", Genre.JRPG,"Pokemon pearl a clasic game ..", tags, ProductType.Game, lars1);

            // Act
            boolean result = MyProduct.UpdateProduct(NewProduct);
            Product product = MyProduct.GetProduct(1);

            // Assert
            assertTrue(result);
            assertEquals("pokemon Pearl", product.getName1());
            assertEquals("Pokemon 2004", product.getName2());
            assertEquals("Pokemon Pearl", product.getSerie());
            assertEquals(2004, product.getYear());
            assertEquals("Good", product.getCondition());
            assertEquals("Pokemon pearl a clasic game ..", product.getDescription());
            assertEquals(1, product.getTags().size());
        }

        /*All products*/
        @Test
        public void Test_AllProducts_Get_Products() {
            // Arrange
            DbAllProduct AllProduct = new DbAllProduct();
            // Act
            List<Product> products = AllProduct.GetAllProducts();
            // Assert
            assertEquals(1, products.size());
        }
        @Test
        public void Test_AllProducts_Get_Product_by_name() {
            // Arrange
            DbAllProduct AllProduct = new DbAllProduct();
            // Act
            List<Product> products = AllProduct.GetProducts("pokemon diamond");
            // Assert
            assertEquals(1, products.size());
        }
        @Test
        public void Test_AllProducts_Get_Product_by_id() {
            // Arrange
            DbAllProduct AllProduct = new DbAllProduct();

            // Act
            Product product = AllProduct.GetProduct(1);
            // Assert
            assertEquals(1, product.getProductID());
        }
        @Test
        public void Test_AllProducts_Update_Product() {
            // Arrange
            DbAllProduct AllProduct = new DbAllProduct();

            Account lars1account = new Account("Lars", "Lars");
            User lars1 = new NormalUser(1,"Lars", "Kluijtmans", "lars.kluijtmans@gmail.com",1234567, lars1account);

            Tag tag1 = new Tag(1,"NormalGame");

            List<Tag> tags = new ArrayList<>();
            tags.add(tag1);

            Product NewProduct = new Product(1,"pokemon Pearl", "Pokemon 2004", "Pokemon Pearl", 2004, "Good", Genre.JRPG,"Pokemon pearl a clasic game ..", tags, ProductType.Game, lars1);

            // Act
            boolean result = AllProduct.UpdateProduct(NewProduct);
            Product product = AllProduct.GetProduct(1);

            // Assert
            assertTrue(result);
            assertEquals("pokemon Pearl", product.getName1());
            assertEquals("Pokemon 2004", product.getName2());
            assertEquals("Pokemon Pearl", product.getSerie());
            assertEquals(2004, product.getYear());
            assertEquals("Good", product.getCondition());
            assertEquals("Pokemon pearl a clasic game ..", product.getDescription());
            assertEquals(1, product.getTags().size());
        }
        @Test
        public void Test_AllProducts_Delete_Product() {
            // Arrange
            DbAllProduct AllProduct = new DbAllProduct();

            // Act
            boolean result = AllProduct.DeleteProduct(1);
            Product product = AllProduct.GetProduct(1);

            // Assert
            assertTrue(result);
            assertNull(product);
        }
}
