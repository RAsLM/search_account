package com.rasl.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbCredentials {

    private static final String resourceName = "application.properties";
    public static final String URL;
    public static final String USER;
    public static final String PASSWORD;

    static {

        final Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = loader.getResourceAsStream(resourceName)){
            if (inputStream != null) {
                properties.load(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            final String JDBC_DRIVER = properties.getProperty("datasource.jdbc_driver");
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        URL = properties.getProperty("datasource.url");
        USER = properties.getProperty("datasource.username");
        PASSWORD = properties.getProperty("datasource.password");
    }
}
