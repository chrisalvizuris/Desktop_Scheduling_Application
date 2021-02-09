package Dao;

import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;

public class CustomerImp {

    public static void addCustomer(Customers customer) throws SQLException {
        Connection conn = DatabaseConnection.beginConnection();
        String insertStatement = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        DatabaseQuery.setPreparedStatement(conn, insertStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        String customerName = customer.getCustomerName();
        String customerAddress = customer.getCustomerAddress();
        String postalCode = customer.getCustomerPostal();
        String phoneNumber = customer.getCustomerPhone();
        LocalDateTime createDate = customer.getCustomerCreateDate();
        String createdBy = customer.getCustomerCreatedBy();
        LocalDateTime updateDate = customer.getCustomerUpdateDate();
        String updatedBy = customer.getCustomerUpdateBy();
        int divisionId = customer.getDivisionId();

        //key-value mapping
        preparedStatement.setString(1, customerName);
        preparedStatement.setString(2, customerAddress);
        preparedStatement.setString(3, postalCode);
        preparedStatement.setString(4, phoneNumber);
        preparedStatement.setString(5, String.valueOf(createDate));
        preparedStatement.setString(6, createdBy);
        preparedStatement.setString(7, String.valueOf(updateDate));
        preparedStatement.setString(8, updatedBy);
        preparedStatement.setString(9, String.valueOf(divisionId));

        preparedStatement.execute();

        //check rows affected
        if(preparedStatement.getUpdateCount() > 0) {
            System.out.println(preparedStatement.getUpdateCount() + " row(s) affected.");
        }   else {
            System.out.println("No change.");
        }

        DatabaseConnection.closeConnection();

    }


    public static Customers getCustomer(int customerId) throws SQLException {
        Connection conn = DatabaseConnection.beginConnection();
        String selectStatement = "SELECT * FROM customers WHERE Customer_ID = " + String.valueOf(customerId);
        DatabaseQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        Customers customer;

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            int customerID = resultSet.getInt("Customer_ID");
            String customerName = resultSet.getString("Customer_Name");
            String customerAddress = resultSet.getString("Address");
            String customerZip = resultSet.getString("Postal_Code");
            String customerPhone = resultSet.getString("Phone");
            LocalDate createDate = resultSet.getDate("Create_Date").toLocalDate();
            LocalTime createTime = resultSet.getTime("Create_Date").toLocalTime();
            LocalDateTime createLDT = LocalDateTime.of(createDate, createTime);
            String createdBy = resultSet.getString("Created_By");
            LocalDate updateDate = resultSet.getDate("Last_Update").toLocalDate();
            LocalTime updateTime = resultSet.getTime("Last_Update").toLocalTime();
            LocalDateTime updateLDT = LocalDateTime.of(updateDate, updateTime);
            String updatedBy = resultSet.getString("Last_Updated_By");
            int divisionId = resultSet.getInt("Division_ID");

            customer = new Customers(customerName, customerAddress, customerZip, customerPhone, createLDT, createdBy, updateLDT, updatedBy);
            customer.setDivisionId(divisionId);
            customer.setCustomerId(customerID);

            //check rows affected
            if(preparedStatement.getUpdateCount() > 0) {
                System.out.println(preparedStatement.getUpdateCount() + " row(s) affected.");
            }   else {
                System.out.println("No change.");
            }
            DatabaseConnection.closeConnection();
            return customer;
        }
        DatabaseConnection.closeConnection();
        return null;
    }


    public static void updateCustomer(Customers customer) throws SQLException {
        Connection conn = DatabaseConnection.beginConnection();
        String updateStatement = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
        DatabaseQuery.setPreparedStatement(conn, updateStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        String newName = customer.getCustomerName();
        String newAddress = customer.getCustomerAddress();
        String newPostal = customer.getCustomerPostal();
        String newPhone = customer.getCustomerPhone();
        LocalDate updateDate = customer.getCustomerUpdateDate().toLocalDate();
        LocalTime updateTime = customer.getCustomerUpdateDate().toLocalTime();
        LocalDateTime customerUpdateDate = LocalDateTime.of(updateDate, updateTime);
        String updatedBy = customer.getCustomerUpdateBy();
        int divisionId = customer.getDivisionId();

        int customerToUpdate = customer.getCustomerId();

        preparedStatement.setString(1, newName);
        preparedStatement.setString(2, newAddress);
        preparedStatement.setString(3, newPostal);
        preparedStatement.setString(4, newPhone);
        preparedStatement.setString(5, String.valueOf(customerUpdateDate));
        preparedStatement.setString(6, updatedBy);
        preparedStatement.setString(7, String.valueOf(divisionId));
        preparedStatement.setString(8, String.valueOf(customerToUpdate));

        preparedStatement.execute();

        //check rows affected
        if(preparedStatement.getUpdateCount() > 0) {
            System.out.println(preparedStatement.getUpdateCount() + " row(s) affected.");
        }   else {
            System.out.println("No change.");
        }

        DatabaseConnection.closeConnection();

    }


    public static void deleteCustomer(int customerId) throws SQLException {

        Connection connection = DatabaseConnection.beginConnection();
        String deleteStatement = "DELETE FROM customers WHERE Customer_ID = " + String.valueOf(customerId);

        DatabaseQuery.setPreparedStatement(connection, deleteStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        preparedStatement.execute();

        //check rows affected
        if(preparedStatement.getUpdateCount() > 0) {
            System.out.println(preparedStatement.getUpdateCount() + " row(s) affected.");
        }   else {
            System.out.println("No change.");
        }

        DatabaseConnection.closeConnection();

    }


    public static ObservableList<Customers> getAllCustomers() throws SQLException {
        ObservableList<Customers> allCustomers = FXCollections.observableArrayList();
        Connection conn = DatabaseConnection.beginConnection();
        String selectStatement = "SELECT * FROM customers";
        DatabaseQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            int customerID = resultSet.getInt("Customer_ID");
            String customersName = resultSet.getString("Customer_Name");
            String customerAddress = resultSet.getString("Address");
            String customerZip = resultSet.getString("Postal_Code");
            String customerPhone = resultSet.getString("Phone");
            LocalDate createDate = resultSet.getDate("Create_Date").toLocalDate();
            LocalTime createTime = resultSet.getTime("Create_Date").toLocalTime();
            LocalDateTime createLDT = LocalDateTime.of(createDate, createTime);
            String createdBy = resultSet.getString("Created_By");
            LocalDate updateDate = resultSet.getDate("Last_Update").toLocalDate();
            LocalTime updateTime = resultSet.getTime("Last_Update").toLocalTime();
            LocalDateTime updateLDT = LocalDateTime.of(updateDate, updateTime);
            String updatedBy = resultSet.getString("Last_Updated_By");
            int divisionId = resultSet.getInt("Division_ID");

            Customers customer = new Customers(customersName, customerAddress, customerZip, customerPhone, createLDT, createdBy, updateLDT, updatedBy);
            customer.setDivisionId(divisionId);
            customer.setCustomerId(customerID);

            allCustomers.add(customer);
            Comparator<Customers> comparator = (customer1, customer2) -> customer1.getCustomerName().compareTo(customer2.getCustomerName());
            allCustomers.sort(comparator);
        }
        DatabaseConnection.closeConnection();
        return allCustomers;
    }
}
