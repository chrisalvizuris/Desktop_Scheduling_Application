package Dao;

import Model.Customers;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
            System.out.println(preparedStatement.getUpdateCount() + "row(s) affected.");
        }   else {
            System.out.println("No change.");
        }

        DatabaseConnection.closeConnection();

    }


    public Customers getCustomer(int customerId) {
        DatabaseConnection.beginConnection();




        return null;
    }


    public void updateCustomer(Customers customer) {

    }


    public void deleteCustomer(int customerId) {

    }


    public ObservableList<Customers> getAllCustomers() throws SQLException {

        Connection conn = DatabaseConnection.beginConnection();
        String selectStatement = "SELECT * FROM customers";
        DatabaseQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getResultSet();

        while(resultSet.next()) {

        //TODO: Fill this out
        }
        return null;
    }
}
