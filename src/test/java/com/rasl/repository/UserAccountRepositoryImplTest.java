package com.rasl.repository;

import com.rasl.utils.DbCredentials;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Scanner;

import static org.junit.Assert.*;

public class UserAccountRepositoryImplTest {

    private static final String SCHEMA = "schema.sql";

    private UserAccountRepositoryImpl repository = new UserAccountRepositoryImpl();

    @BeforeClass
    public static void before(){
        StringBuilder query = new StringBuilder();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = loader.getResourceAsStream(SCHEMA);
             Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                query.append(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(DbCredentials.URL, DbCredentials.USER, DbCredentials.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query.toString())){
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void findByLoginTest() {
        String expectedLogin = "login2";
        String actualLogin = repository.findByLogin(expectedLogin).getLogin();
        assertEquals(expectedLogin, actualLogin);
    }

    @Test
    public void updateLastNameByIdTest() {
        String expectedUpdatedLastName = "newLastName";
        repository.updateLastName(2, expectedUpdatedLastName);
        String actualUpdatedLastName = repository.findByLogin("login2").getLastName();
        assertEquals(expectedUpdatedLastName, actualUpdatedLastName);
    }

    @Test
    public void updateLastNameByLoginTest() {
        String expectedUpdatedLastName = "newLastName";
        repository.updateLastName("login3", expectedUpdatedLastName);
        String actualUpdatedLastName = repository.findByLogin("login3").getLastName();
        assertEquals(expectedUpdatedLastName, actualUpdatedLastName);
    }

    @Test
    public void findByNonexistentLoginTest() {
        String incorrectLogin = "incorrectLogin";
        assertNull(repository.findByLogin(incorrectLogin));
    }

    @Test
    public void updateLastNameByNonexistentIdTest() {
        int incorrectId = 10;
        String lastName = "lastName";
        int expectedUpdatedRowsQty = 0;
        int actualUpdatedRowsQty = repository.updateLastName(incorrectId, lastName);
        assertEquals(expectedUpdatedRowsQty, actualUpdatedRowsQty);
    }

    @Test
    public void updateLastNameByNonexistentLoginTest() {
        String incorrectLogin = "incorrectLogin";
        String lastName = "lastName";
        int expectedUpdatedRowsQty = 0;
        int actualUpdatedRowsQty = repository.updateLastName(incorrectLogin, lastName);
        assertEquals(expectedUpdatedRowsQty, actualUpdatedRowsQty);
    }
}