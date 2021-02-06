package Dao;

import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsImp {

    public static ObservableList<Contacts> getAllContacts() throws SQLException {
        ObservableList<Contacts> allContacts = FXCollections.observableArrayList();
        Connection connection = DatabaseConnection.beginConnection();
        String selectAllStatement = "SELECT * FROM contacts";
        DatabaseQuery.setPreparedStatement(connection, selectAllStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int contactId = resultSet.getInt("Contact_ID");
            String contactName = resultSet.getString("Contact_Name");
            String email = resultSet.getString("Email");

            Contacts contact = new Contacts(contactName, email);
            contact.setContactId(contactId);

            allContacts.add(contact);
        }
        DatabaseConnection.closeConnection();
        return allContacts;
    }
}
