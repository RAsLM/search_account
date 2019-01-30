package com.rasl;

import org.intellij.lang.annotations.Language;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Main {
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
            properties.load(inputStream);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        JDBC_DRIVER = properties.getProperty("datasource.jdbc_driver");
        URL = properties.getProperty("datasource.url");
        USER = properties.getProperty("datasource.username");
        PASSWORD = properties.getProperty("datasource.password");
    }


    public static void main(String[] args) {
        System.out.println(findByLogin("login2"));
        updateLastNameByLogin("login2", "update3");
        updateLastNameById(3, "test3");
    }


    public static AppSecurityAccount findByLogin(String login){
        AppSecurityAccount appSecurityAccount = new AppSecurityAccount();
        @Language("MySQL")
        String query = "SELECT * FROM app_security_account WHERE login=?";
        try{
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(URL,USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, login);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    appSecurityAccount.setAppSecurityAccountId(resultSet.getInt("app_security_account_id"));
                    appSecurityAccount.setLogin(resultSet.getString("login"));
                    appSecurityAccount.setFirstName(resultSet.getString("first_name"));
                    appSecurityAccount.setMiddleName(resultSet.getString("middle_name"));
                    appSecurityAccount.setLastName(resultSet.getString("last_name"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return appSecurityAccount;
    }

    public static void updateLastNameById (int id, String lastName) {
        @Language("MySQL")
        String query = "UPDATE app_security_account SET last_name = ? WHERE app_security_account_id = ?";
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, lastName);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateLastNameByLogin (String login, String lastName){
        @Language("MySQL")
        String query = "UPDATE app_security_account SET last_name = ? WHERE login = ?";
        try{
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, login);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
