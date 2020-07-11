package dao;

import entity.Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductsDao {
    ConnectionDao connectionDao = new ConnectionDao();

    public void showProductsList() {
        try {
            Connection connection = connectionDao.getConnection();
            String query = "select * from products";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("______products list_____");
            while (resultSet.next()) {

                String category = resultSet.getString("category");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock");
                String brand = resultSet.getString("brand");
                System.out.println("category : " + category + "\n" +
                        "product name :" + name + "\n" + "price :" + price + "\n" + "Brand :" +
                        brand + "\n" + "stock :" + stock);
                System.out.println("_______________________");
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Products searchProduct(String category, String name) {
        try {
            Connection connection = connectionDao.getConnection();
            String query = "select  * from  products where category = '" + category + "' and  name='" + name + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            Products product = new Products();
            while (resultSet.next()) {
                product.setProductId(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                product.setBrand(resultSet.getString("brand"));
                product.setCategory(resultSet.getString("category"));
                product.setStock(resultSet.getInt("stock"));
            }
            preparedStatement.close();
            connection.close();
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateProductsStock(String name, int itemCount) {
        try {
            Connection connection = connectionDao.getConnection();
            String query = "update products set products.stock =products.stock-? where  products.name like '" + name + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, itemCount);
            preparedStatement.executeUpdate();
            System.out.println("products stock updated");
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertProduct(Products product) {
        try {
            Connection connection = connectionDao.getConnection();
            String query = "insert into products (category,price,stock,brand) values (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getCategory());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getStock());
            preparedStatement.setString(4, product.getBrand());
            preparedStatement.executeUpdate();
            System.out.println("successfully insert");
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
