package com.rasl.repository;

import com.rasl.utils.DbCredentials;
import org.intellij.lang.annotations.Language;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

public class UserAccountRepositoryImplTest {

    private UserAccountRepositoryImpl repository = new UserAccountRepositoryImpl();


    @BeforeClass
    public static void before(){
        @Language("PostgreSQL")
        String createTable =
                "CREATE TABLE IF NOT EXISTS user_account (\n" +
                "  user_account_id serial not null primary key,\n" +
                "  login varchar(50) not null unique,\n" +
                "  first_name varchar(50) not null,\n" +
                "  middle_name varchar (50) not null,\n" +
                "  last_name varchar (50)\n" +
                ");";

        @Language("PostgreSQL")
        String createInserts =
                "INSERT INTO user_account (login, first_name, middle_name, last_name) VALUES ('login1', 'first_name1', 'middle_name1', 'last_name1');\n" +
                "INSERT INTO user_account (login, first_name, middle_name, last_name) VALUES ('login2', 'first_name2', 'middle_name2', 'last_name2');\n" +
                "INSERT INTO user_account (login, first_name, middle_name, last_name) VALUES ('login3', 'first_name3', 'middle_name3', 'last_name3');\n" +
                "INSERT INTO user_account (login, first_name, middle_name, last_name) VALUES ('login4', 'first_name4', 'middle_name4', 'last_name4');\n" +
                "INSERT INTO user_account (login, first_name, middle_name, last_name) VALUES ('login5', 'first_name5', 'middle_name5', 'last_name5');";

        try{
            Class.forName(DbCredentials.JDBC_DRIVER);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(DbCredentials.URL, DbCredentials.USER, DbCredentials.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(createTable)){
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(DbCredentials.URL, DbCredentials.USER, DbCredentials.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(createInserts)){
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