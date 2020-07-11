package dao;

import entity.Customer;
import entity.OperationLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OperationLogDao {
    ConnectionDao connectionDao = new ConnectionDao();

    public void insertOperationLog(OperationLog operationLog, Customer customer) {
        try {
            Connection connection = connectionDao.getConnection();
            String query = "insert into operationlog(operation,operation_time,operation_date,customer_username)" +
                    "values (?,?,?,(SELECT username FROM customer WHERE customer.username=?))";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(operationLog.getOperation()));
            preparedStatement.setTime(2, operationLog.getTime());
            preparedStatement.setDate(3,  operationLog.getDate());
            preparedStatement.setString(4, customer.getUserName());

            preparedStatement.execute();
            System.out.println("successfully insert into operationLog");
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<OperationLog> getOperationList(String startDate,String endDate) {
        try {
            Connection connection = connectionDao.getConnection();
            String query = "SELECT * FROM `operationlog` WHERE operation_date" +
                    " BETWEEN '" + startDate + "' AND '" + endDate + "' ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            ArrayList<OperationLog> operationLogs = new ArrayList<>();

            while (resultSet.next()) {
                OperationLog operationLog = new OperationLog();
                operationLog.setOperation(resultSet.getString("operation"));
                operationLog.setTime(resultSet.getTime("operation_time"));
                operationLog.setDate(resultSet.getDate("operation_date"));
                operationLog.setAuthority(resultSet.getString("customer_username"));
                operationLogs.add(operationLog);
            }
            preparedStatement.close();
            connection.close();
            return operationLogs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}

