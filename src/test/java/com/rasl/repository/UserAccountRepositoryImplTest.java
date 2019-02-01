package com.rasl.repository;

import org.intellij.lang.annotations.Language;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import static org.junit.Assert.*;

public class UserAccountRepositoryImplTest {

    private static final Properties properties;
    private static final String JDBC_DRIVER;
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        String resourceName = "application.properties";
        properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = loader.getResourceAsStream(resourceName)){
            if (inputStream != null) {
                properties.load(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JDBC_DRIVER = properties.getProperty("datasource.jdbc_driver");
        URL = properties.getProperty("datasource.url");
        USER = properties.getProperty("datasource.username");
        PASSWORD = properties.getProperty("datasource.password");
    }

    private UserAccountRepositoryImpl repository = new UserAccountRepositoryImpl();


    @Before
    public void before(){
        @Language("MySQL")
        String createTable = "CREATE TABLE IF NOT EXISTS user_account (\n" +
                "  user_account_id serial not null primary key,\n" +
                "  login varchar(50) not null unique,\n" +
                "  first_name varchar(50) not null,\n" +
                "  middle_name varchar (50) not null,\n" +
                "  last_name varchar (50)\n" +
                ");";

        @Language("MySQL")
        String createInserts = "INSERT INTO user_account (login, first_name, middle_name, last_name) VALUES ('login1', 'first_name1', 'middle_name1', 'last_name1');\n" +
                "INSERT INTO user_account (login, first_name, middle_name, last_name) VALUES ('login2', 'first_name2', 'middle_name2', 'last_name2');\n" +
                "INSERT INTO user_account (login, first_name, middle_name, last_name) VALUES ('login3', 'first_name3', 'middle_name3', 'last_name3');\n" +
                "INSERT INTO user_account (login, first_name, middle_name, last_name) VALUES ('login4', 'first_name4', 'middle_name4', 'last_name4');\n" +
                "INSERT INTO user_account (login, first_name, middle_name, last_name) VALUES ('login5', 'first_name5', 'middle_name5', 'last_name5');";

        try{
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(URL,USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(createTable)){
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(URL,USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(createInserts)){
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    @After
    public void after(){
        @Language("MySQL")
        String dropTable = "DROP TABLE IF EXISTS user_account;";
        try{
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(URL,USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(dropTable)){
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void findByLoginTest() {
        String login = "login2";
        assertEquals(login, repository.findByLogin(login).getLogin());
    }

    @Test
    public void updateLastNameTest() {
        String updateLastName = "newLastName";
        repository.updateLastName(2, updateLastName);
        assertEquals(updateLastName, repository.findByLogin("login2").getLastName());
    }

    @Test
    public void updateLastName1Test() {
        String updateLastName = "newLastName";
        repository.updateLastName("login3", updateLastName);
        assertEquals(updateLastName, repository.findByLogin("login3").getLastName());
    }
}