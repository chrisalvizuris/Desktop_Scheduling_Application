package Dao;

import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsImp {

    /**
     * This method generates SQL statement to retrieve all contacts from database.
     * @return Returns an observable list of contacts.
     * @throws SQLException Throws an SQLException because database is called
     */
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

    /**
     * This method generates SQL statement to return a specific contact from database.
     * @param contactsID Contact id of contact to return from database.
     * @return Returns a contact from database.
     * @throws SQLException Throws an SQLException because database is called
     */
    public static Contacts getContact(int contactsID) throws SQLException {
        Connection connection = DatabaseConnection.beginConnection();
        String selectStatement = "SELECT * FROM contacts WHERE Contact_ID = " + String.valueOf(contactsID);
        DatabaseQuery.setPreparedStatement(connection, selectStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        Contacts contact;

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            int contactsId = resultSet.getInt("Contact_ID");
            String contactName = resultSet.getString("Contact_Name");
            String email = resultSet.getString("Email");

            contact = new Contacts(contactName, email);
            contact.setContactId(contactsId);

            DatabaseConnection.closeConnection();
            return contact;
        }
        DatabaseConnection.closeConnection();
        return null;
    }
}
