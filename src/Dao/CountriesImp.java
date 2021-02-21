package Dao;

import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CountriesImp {

    /**
     * This method generates SQL statement to return all countries from database.
     * @return Returns an observable list of countries.
     * @throws SQLException Throws an SQLException because database is called
     */
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

    /**
     * This method generates SQL statement to return a specific country from database.
     * @param countryId Country id of country to return.
     * @return Returns a country.
     * @throws SQLException Throws an SQLException because database is called
     */
    public static Countries getCountry(int countryId) throws SQLException {
        Connection connection = DatabaseConnection.beginConnection();
        String selectCountryStatement = "SELECT * FROM countries WHERE Country_ID = " + String.valueOf(countryId);

        DatabaseQuery.setPreparedStatement(connection, selectCountryStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();
        Countries country;
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            int countryID = resultSet.getInt("Country_ID");
            String countryName = resultSet.getString("Country");
            LocalDate createDate = resultSet.getDate("Create_Date").toLocalDate();
            LocalTime createtIME = resultSet.getTime("Create_Date").toLocalTime();
            LocalDateTime countryCreateDate = LocalDateTime.of(createDate, createtIME);
            String createdBy = resultSet.getString("Created_By");
            LocalDate updateDate = resultSet.getDate("Last_Update").toLocalDate();
            LocalTime updateTime = resultSet.getTime("Last_Update").toLocalTime();
            LocalDateTime countryUpdateDate = LocalDateTime.of(updateDate, updateTime);
            String updatedBy = resultSet.getString("Last_Updated_By");

            country = new Countries(countryName);
            country.setCountryID(countryID);
            country.setCountryCreateDate(countryCreateDate);
            country.setCountryCreatedBy(createdBy);
            country.setCountryLastUpdate(countryUpdateDate);
            country.setCountryLastUpdateBy(updatedBy);

            DatabaseConnection.closeConnection();
            return country;
        }

        DatabaseConnection.closeConnection();
        return null;
    }
}
