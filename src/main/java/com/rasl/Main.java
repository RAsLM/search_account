package com.rasl;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    private static Connection connection;
    private static final Properties properties;
    private static final String JDBC_DRIVER;
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
         properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream("src/main/resources/application.properties")){
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

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection opened");
        } catch (SQLException e){
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

        System.out.println();
    }
}
