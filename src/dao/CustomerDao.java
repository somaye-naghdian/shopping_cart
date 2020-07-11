package dao;

import entity.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDao {
    ConnectionDao connectionDao = new ConnectionDao();

    public void insertCustomer(Customer customer) {
        try {

            Connection connection = connectionDao.getConnection();
            String query = "insert into customer(name,family,phone,email,username,pass,age) values (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getFamily());
            preparedStatement.setString(3, customer.getPhone());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getUserName());
            preparedStatement.setString(6, customer.getPassword());
            preparedStatement.setInt(7, customer.getAge());

            preparedStatement.executeUpdate();
            System.out.println("successfully insert");
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean searchDuplicateUserName(String username) {
        try {
            Connection connection = connectionDao.getConnection();
            String query = "select username from customer where username = '" + username + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Customer passwordValidation(String username, String password) {
        try {
            Connection connection = connectionDao.getConnection();
            String query = "select name,family,age,phone,email,username,pass" +
                    " from customer where username = '" + username + "'and pass = '" + password + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {

                String name = resultSet.getString("name");
                String family = resultSet.getString("family");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                Customer customer = new Customer(name, family, age, username, password, phone, email);
                return customer;
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Customer> getCustomerList() {
        try {
            Connection connection = connectionDao.getConnection();
            String query = "select * from customer ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            ArrayList<Customer> customers = new ArrayList<>();

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setName(resultSet.getString("name"));
                customer.setFamily(resultSet.getString("family"));
                customer.setAge(resultSet.getInt("age"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setUserName(resultSet.getString("username"));
                customer.setPassword(resultSet.getString("pass"));
                customers.add(customer);
            }
            preparedStatement.close();
            connection.close();
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
