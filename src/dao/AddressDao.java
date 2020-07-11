package dao;

import entity.Address;
import entity.Customer;

import java.sql.*;

public class AddressDao {
    ConnectionDao connectionDao = new ConnectionDao();

    public void insertAddress(Address address, Customer customer) {

        try {
            Connection connection = connectionDao.getConnection();
            String query = "insert into address(province,city,street,zipCode,customer_username) values (?,?,?,?," +
                    "(SELECT username FROM customer WHERE customer.username=?))";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, address.getProvince());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setInt(4, address.getZipCode());
            preparedStatement.setString(5, customer.getUserName());

            preparedStatement.execute();
            System.out.println("successfully insert");
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Address searchAddress(int customerId) {
        try {
            Connection connection = connectionDao.getConnection();
            String query = "SELECT * FROM `address` inner join customer on address.customer_id=customer.customer_id " +
                    " where  address.customer_id ='" + customerId + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            Address address = new Address();
            while (resultSet.next()) {
                address.setProvince(resultSet.getString("province"));
                address.setCity(resultSet.getNString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setZipCode(resultSet.getInt("zipCode"));
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return address;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
