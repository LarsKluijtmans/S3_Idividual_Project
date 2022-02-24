package com.example.Individual_Project;

import com.example.Individual_Project.business.AllUsers;
import com.example.Individual_Project.business.Login;
import com.example.Individual_Project.business.MyAccount;
import com.example.Individual_Project.business.NotLogedIn.NotLogedIn;
import com.example.Individual_Project.model.NormalUser;
import com.example.Individual_Project.model.User;
import com.example.Individual_Project.model.Users.Account;
import com.example.Individual_Project.repository.Test.NotLogedIn.DbNotLogedIn;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Primary;
import org.springframework.expression.spel.ast.NullLiteral;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserTest {

    /*Login*/
    @Test
    public void Test_Login_With_Correct_Info() {
        // Arrange
        final Login login = new NotLogedIn(new DbNotLogedIn());
        // Act
        User user =  login.GetAccount("lars","lars");

        Account lars1account = new Account("Lars", "Lars");
        User lars1 = new NormalUser(1,"Lars", "Kluijtmans", "lars.kluijtmans@gmail.com",1234567, lars1account);
        // Assert
        assertEquals(lars1, user);
    }

    @Test
    public void Test_Login_With_InCorrect_Info() {
        // Arrange
        final Login login = new NotLogedIn(new DbNotLogedIn());
        // Act
        User user =  login.GetAccount("","");

        Account lars1account = new Account("Lars", "Lars");
        User lars1 = new NormalUser(1,"Lars", "Kluijtmans", "lars.kluijtmans@gmail.com",1234567, lars1account);

        // Assert
        assertEquals(null, user);
    }

    @Test
    public void Test_AddUser() {
        // Arrange
        final Login login = new NotLogedIn(new DbNotLogedIn());

        // Act
        User user =  login.GetAccount("","");

        Account lars1account = new Account("Lars", "Lars");
        User lars1 = new NormalUser(1,"Lars", "Kluijtmans", "lars.kluijtmans@gmail.com",1234567, lars1account);

        // Assert
        assertEquals(null, user);
    }

    /*My Account*/
    @Test
    public void Test_UpdateAccount() {
        // Arrange
        final Login login = new NotLogedIn(new DbNotLogedIn());

        // Act
        User user =  login.GetAccount("","");

        Account lars1account1 = new Account("Lars11", "Lars11");
        User lars1 = new NormalUser(1,"Lars", "Kluijtmans", "lars.kluijtmans@gmail.com",433, lars1account1);

        // Assert
        assertEquals(null, user);
    }

    /*All users*/
    @Test
    public void Test_GetAllAcount() {
        // Arrange
        final Login login = new NotLogedIn(new DbNotLogedIn());

        // Act
        User user =  login.GetAccount("","");

        Account lars1account1 = new Account("Lars11", "Lars11");
        User lars1 = new NormalUser(1,"Lars", "Kluijtmans", "lars.kluijtmans@gmail.com",433, lars1account1);

        // Assert
        assertEquals(null, user);
    }

}
