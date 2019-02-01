package com.rasl.repository;

import com.rasl.utils.DbCredentials;
import com.rasl.pojo.UserAccount;
import org.intellij.lang.annotations.Language;

import java.sql.*;

public class UserAccountRepositoryImpl implements UserAccountRepository {

    public UserAccountRepositoryImpl() {
        try{
            Class.forName(DbCredentials.JDBC_DRIVER);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public UserAccount findByLogin(String login) {
        UserAccount userAccount = new UserAccount();
        @Language("PostgreSQL")
        String query = "SELECT * FROM user_account WHERE login=?";
        try(Connection connection = DriverManager.getConnection(DbCredentials.URL,DbCredentials.USER, DbCredentials.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, login);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    userAccount.setAppSecurityAccountId(resultSet.getInt("user_account_id"));
                    userAccount.setLogin(resultSet.getString("login"));
                    userAccount.setFirstName(resultSet.getString("first_name"));
                    userAccount.setMiddleName(resultSet.getString("middle_name"));
                    userAccount.setLastName(resultSet.getString("last_name"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return userAccount;
    }

    @Override
    public int updateLastName(int id, String lastName) {
        @Language("PostgreSQL")
        String query = "UPDATE user_account SET last_name = ? WHERE user_account_id = ?";
        try (Connection connection = DriverManager.getConnection(DbCredentials.URL, DbCredentials.USER, DbCredentials.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, lastName);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateLastName(String login, String lastName) {
        @Language("PostgreSQL")
        String query = "UPDATE user_account SET last_name = ? WHERE login = ?";
        try(Connection connection = DriverManager.getConnection(DbCredentials.URL, DbCredentials.USER, DbCredentials.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, login);
            return preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}
