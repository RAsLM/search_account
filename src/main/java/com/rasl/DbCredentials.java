package com.rasl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbCredentials {
    private static final Properties properties;
    public static final String JDBC_DRIVER;
    public static final String URL;
    public static final String USER;
    public static final String PASSWORD;

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
}
