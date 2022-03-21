package com.example.Individual_Project;

import com.example.Individual_Project.business.AccountService;
import com.example.Individual_Project.business.Impl.AccountServiceImpl;
import com.example.Individual_Project.business.ProductService;
import com.example.Individual_Project.business.Impl.ProductServiceImpl;
import com.example.Individual_Project.controller.AccountController;
import com.example.Individual_Project.model.Products.Genre;
import com.example.Individual_Project.model.Products.Product;
import com.example.Individual_Project.model.Products.ProductType;
import com.example.Individual_Project.model.Products.Tag;
import com.example.Individual_Project.model.User;
import com.example.Individual_Project.model.Users.Account;
import com.example.Individual_Project.repository.Test.AccountRepositoryImpl;
import com.example.Individual_Project.repository.Test.ProductRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class tests {

    /*User*/
        @Test
        void test_get_account_by_username_and_password() {
            // Arrange
            AccountController accountController = new AccountController(new AccountServiceImpl(new AccountRepositoryImpl()));
            // Act
            ResponseEntity<User> user =  accountController.getUsersByUsernameAndPassword(new Account("Lars","Lars"));
            // Assert
            assertEquals("Lars", user.getBody().getAccount().getUsername());
            assertEquals("Lars", user.getBody().getAccount().getPassword());
        }
        @Test
        void test_get_account_by_username_and_password_incorrect_info() {
            // Arrange
            AccountService accountService = new AccountServiceImpl(new AccountRepositoryImpl());
            // Act
            User user =  accountService.getAccount(new Account("",""));
            // Assert
            assertNull(user);
        }
        @Test
        void test_get_accounts_by_name() {
            // Arrange
            AccountService accountService = new AccountServiceImpl(new AccountRepositoryImpl());
            // Act
            List<User> users = accountService.getAllAccounts("Lars");
            // Assert
            assertEquals( 1,users.size());
        }
        @Test
        void test_get_all_accounts_wrong_name() {
            // Arrange
            AccountService accountService = new AccountServiceImpl(new AccountRepositoryImpl());
            // Act
            List<User> users = accountService.getAllAccounts("jon");
            // Assert
            assertEquals( 0,users.size());
        }
        @Test
        void test_get_account_by_id() {
            // Arrange
            AccountService accountService = new AccountServiceImpl(new AccountRepositoryImpl());
            // Act
            User user = accountService.getAccount(1);
            // Assert
            assertNotNull(user);
        }
        @Test
        void test_get_all_accounts() {
            // Arrange
            AccountService accountService = new AccountServiceImpl(new AccountRepositoryImpl());
            // Act
            List<User> users = accountService.getAllAccounts();
            // Assert
            assertEquals( 2,users.size());
        }

        @Test
        void test_add_user() {
            // Arrange
            AccountService accountService = new AccountServiceImpl(new AccountRepositoryImpl());

            Account lars1account = new Account("Lars3", "Lars3");
            User lars3 = new User(3,"Lars3", "Kluijtmans3", "lars.kluijtmans@gmail.com3",12345673, lars1account);
            // Act
            boolean result = accountService.addAccount(lars3);
            // Assert
            assertTrue(result);
        }
        @Test
        void test_update_account() {
            // Arrange
            AccountService accountService = new AccountServiceImpl(new AccountRepositoryImpl());

            Account lars1account = new Account("Lars3", "Lars3");
            User lars3 = new User(1,"Lars3", "Kluijtmans3", "lars.kluijtmans@gmail.com3",12345673, lars1account);
            // Act
            boolean result = accountService.updateAccount(lars3);
            // Assert
            assertTrue(result);
        }
        @Test
        void test_delete_account() {
            // Arrange
            AccountService accountService = new AccountServiceImpl(new AccountRepositoryImpl());
            // Act
            boolean result = accountService.deleteAccount(1);
            // Assert
            assertTrue(result);
        }

    /*product*/
        @Test
        void test_get_Products() {
            // Arrange
            ProductService accountService = new ProductServiceImpl(new ProductRepositoryImpl());
            // Act
            List<Product> products = accountService.getAllProducts();
            // Assert
            assertEquals(1, products.size());
        }
        @Test
        void test_get_all_Products() {
            // Arrange
            ProductService accountService = new ProductServiceImpl(new ProductRepositoryImpl());
            // Act
            List<Product> products = accountService.getAllProducts();
            // Assert
            assertEquals(1, products.size());
        }
        @Test
        void test_get_product_by_id() {
            // Arrange
            ProductService accountService = new ProductServiceImpl(new ProductRepositoryImpl());
            // Act
            Product product = accountService.getProduct(1);
            // Assert
            assertEquals(1, product.getProductID());
        }
        @Test
        void test_get_products_by_name() {
            // Arrange
            ProductService accountService = new ProductServiceImpl(new ProductRepositoryImpl());
            // Act
            List<Product> products = accountService.getProducts("");
            // Assert
            assertEquals(0, products.size());
        }
        @Test
        void test_get_products_by_name_no_name_entered() {
            // Arrange
            ProductService accountService = new ProductServiceImpl(new ProductRepositoryImpl());
            // Act
            List<Product> products = accountService.getProducts("pokemon diamond");
            // Assert
            assertEquals(1, products.size());
        }
        @Test
        void test_get_products_by_name_wrong_name_entered() {
            // Arrange
            ProductService accountService = new ProductServiceImpl(new ProductRepositoryImpl());
            // Act
            List<Product> products = accountService.getProducts("pokemon diamond2");
            // Assert
            assertEquals(0, products.size());
        }
        @Test
        void test_add_product() {
            // Arrange
            ProductService accountService = new ProductServiceImpl(new ProductRepositoryImpl());

            Account lars2account = new Account("Lars2", "Lars2");
            User lars2 = new User(2,"Lars2", "Kluijtmans2", "lars.kluijtmans@gmail.com2",2345679, lars2account);

            Tag tag1 = new Tag(1,"NormalGame");
            Tag tag2 = new Tag(2,"VeryOldGame");
            Tag tag3 = new Tag(3,"Most popular");

            List<Tag> tags = new ArrayList<>();
            tags.add(tag1);
            tags.add(tag2);
            tags.add(tag3);

            Product NewProduct = new Product(3,"pokemon Pearl", "Pokemon 2004", "Pokemon", 2004, "Great", Genre.JRPG,"Pokemon pearl a classic game ..", tags, ProductType.Game, lars2);
            // Act
            boolean result = accountService.addProduct(NewProduct);
            Product product = accountService.getProduct(3);
            // Assert
            assertTrue(result);
            assertEquals(NewProduct, product);
        }
        @Test
        void test_update_product() {
            // Arrange
            ProductService accountService = new ProductServiceImpl(new ProductRepositoryImpl());

            Account lars1account = new Account("Lars", "Lars");
            User lars1 = new User(1,"Lars", "Kluijtmans", "lars.kluijtmans@gmail.com",1234567, lars1account);

            Tag tag1 = new Tag(1,"NormalGame");

            List<Tag> tags = new ArrayList<>();
            tags.add(tag1);

            Product NewProduct = new Product(1,"pokemon Pearl", "Pokemon 2004", "Pokemon Pearl", 2004, "Good", Genre.JRPG,"Pokemon pearl a classic game ..", tags, ProductType.Game, lars1);

            // Act
            boolean result = accountService.updateProduct(NewProduct);
            Product product = accountService.getProduct(1);
            // Assert
            assertTrue(result);
            assertEquals("pokemon Pearl", product.getName1());
            assertEquals("Pokemon 2004", product.getName2());
            assertEquals("Pokemon Pearl", product.getSerie());
            assertEquals(2004, product.getYear());
            assertEquals("Good", product.getCondition());
            assertEquals("Pokemon pearl a classic game ..", product.getDescription());
            assertEquals(1, product.getTags().size());
        }
        @Test
        void test_delete_product() {
            // Arrange
            ProductService accountService = new ProductServiceImpl(new ProductRepositoryImpl());
            // Act
            boolean result = accountService.deleteProduct(1);
            Product product = accountService.getProduct(1);
            // Assert
            assertTrue(result);
            assertNull(product);
        }
}
