package dao;

import entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
    ConnectionDao connectionDao = new ConnectionDao();


    public Admin passwordValidation(String username, String password) {
        try {
            Connection connection = connectionDao.getConnection();
            String query = "select username,pass" +
                    " from admin where username = '" + username + "'and pass = '" + password + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {

                String username1 = resultSet.getString("username");
                String password1 = resultSet.getString("pass");
                Admin admin = new Admin();
                admin.setUsername(username1);
                admin.setPassword(password1);
                return admin;
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
