package Dao;

import Model.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

            FirstLevelDivision city = new FirstLevelDivision(name);
            city.setDivisionId(id);

            allDivisions.add(city);
        }
        DatabaseConnection.closeConnection();
        return allDivisions;
    }
}
