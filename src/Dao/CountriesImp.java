package Dao;

import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountriesImp {
    public static ObservableList<Countries> getAllCountries() throws SQLException {
        ObservableList<Countries> allCountries = FXCollections.observableArrayList();
        Connection connection = DatabaseConnection.beginConnection();
        String selectAllStatement = "SELECT * FROM countries";

        DatabaseQuery.setPreparedStatement(connection, selectAllStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String countryName = resultSet.getString("Country");
            int id = resultSet.getInt("Country_ID");

            Countries country = new Countries(countryName);
            country.setCountryID(id);

            allCountries.add(country);
        }
        DatabaseConnection.closeConnection();;
        return allCountries;
    }
}
