package com.rasl.repository;

import com.rasl.DbCredentials;
import com.rasl.UserAccount;
import org.intellij.lang.annotations.Language;

import java.sql.*;

public class UserAccountRepositoryImpl implements UserAccountRepository {
    @Override
    public UserAccount findByLogin(String login) {
        UserAccount appSecurityAccount = new UserAccount();
        @Language("MySQL")
        String query = "SELECT * FROM user_account WHERE login=?";
        try{
            Class.forName(DbCredentials.JDBC_DRIVER);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(DbCredentials.URL,DbCredentials.USER, DbCredentials.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, login);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    appSecurityAccount.setAppSecurityAccountId(resultSet.getInt("user_account_id"));
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

    @Override
    public int updateLastName(int id, String lastName) {
        int isUpdated = -1;
        @Language("MySQL")
        String query = "UPDATE user_account SET last_name = ? WHERE user_account_id = ?";
        try {
            Class.forName(DbCredentials.JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(DbCredentials.URL, DbCredentials.USER, DbCredentials.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, lastName);
            preparedStatement.setInt(2, id);
            isUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    @Override
    public int updateLastName(String login, String lastName) {
        int isUpdated = -1;
        @Language("MySQL")
        String query = "UPDATE user_account SET last_name = ? WHERE login = ?";
        try{
            Class.forName(DbCredentials.JDBC_DRIVER);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(DbCredentials.URL, DbCredentials.USER, DbCredentials.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, login);
            isUpdated = preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return isUpdated;
    }
}
