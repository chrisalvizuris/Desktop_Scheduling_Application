package Dao;

import Model.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DivisionsImp {

    public static ObservableList<FirstLevelDivision> getAllDivisions() throws SQLException {
        ObservableList<FirstLevelDivision> allDivisions = FXCollections.observableArrayList();
        Connection connection = DatabaseConnection.beginConnection();
        String selectAllStatement = "SELECT * FROM first_level_divisions";

        DatabaseQuery.setPreparedStatement(connection, selectAllStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("Division");
            int id = resultSet.getInt("Division_ID");
            int countryId = resultSet.getInt("COUNTRY_ID");

            FirstLevelDivision city = new FirstLevelDivision(name);
            city.setDivisionId(id);
            city.setDivisionCountryId(countryId);


            allDivisions.add(city);
        }
        DatabaseConnection.closeConnection();
        return allDivisions;
    }

    public static FirstLevelDivision getDivision(int divisionId) throws SQLException {
        Connection connection = DatabaseConnection.beginConnection();
        String selectStatement = "SELECT * FROM first_level_divisions WHERE Division_ID = " + String.valueOf(divisionId);

        DatabaseQuery.setPreparedStatement(connection, selectStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        FirstLevelDivision division;

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            int divisionID = resultSet.getInt("Division_ID");
            String divisionName = resultSet.getString("Division");
            LocalDate createDate = resultSet.getDate("Create_Date").toLocalDate();
            LocalTime createTime = resultSet.getTime("Create_Date").toLocalTime();
            LocalDateTime divisionCreateDate = LocalDateTime.of(createDate, createTime);
            String createdBy = resultSet.getString("Created_By");
            LocalDate updateDate = resultSet.getDate("Last_Update").toLocalDate();
            LocalTime updateTime = resultSet.getTime("Last_Update").toLocalTime();
            LocalDateTime divisionUpdateDate = LocalDateTime.of(updateDate, updateTime);
            String updatedBy = resultSet.getString("Last_Updated_By");
            int countryId = resultSet.getInt("COUNTRY_ID");

            division = new FirstLevelDivision(divisionName);
            division.setDivisionId(divisionID);
            division.setDivisionCreateDate(divisionCreateDate);
            division.setDivisionCreatedBy(createdBy);
            division.setDivisionUpdateDate(divisionUpdateDate);
            division.setDivisionUpdateBy(updatedBy);
            division.setDivisionCountryId(countryId);

            DatabaseConnection.closeConnection();
            return division;
        }
        DatabaseConnection.closeConnection();
        return null;
    }
}
