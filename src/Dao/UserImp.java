package Dao;

import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UserImp {

    public static void addUser(Users user) throws SQLException {
        Connection connection = DatabaseConnection.beginConnection();
        String insertStatement = "INSERT INTO users(User_Name, Password, Create_Date, Created_By, Last_Update, Last_Updated_By) VALUES (?, ?, ?, ?, ?, ?)";
        DatabaseQuery.setPreparedStatement(connection, insertStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        String userName = user.getUserName();
        String password = user.getPassword();
        LocalDate createDate = user.getUserCreateDate().toLocalDate();
        LocalTime createTime = user.getUserCreateDate().toLocalTime();
        LocalDateTime userCreateDate = LocalDateTime.of(createDate, createTime);
        String createdBy = user.getUserCreatedBy();
        LocalDate updateDate = user.getUserUpdatedDate().toLocalDate();
        LocalTime updateTime = user.getUserUpdatedDate().toLocalTime();
        LocalDateTime userUpdateDate = LocalDateTime.of(updateDate, updateTime);
        String updatedBy = user.getUserUpdatedBy();

        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, String.valueOf(userCreateDate));
        preparedStatement.setString(4, createdBy);
        preparedStatement.setString(5, String.valueOf(userUpdateDate));
        preparedStatement.setString(6, updatedBy);

        preparedStatement.execute();

        //check rows affected
        if(preparedStatement.getUpdateCount() > 0) {
            System.out.println(preparedStatement.getUpdateCount() + " row(s) affected.");
        }   else {
            System.out.println("No change.");
        }

        DatabaseConnection.closeConnection();

    }

    public static Users getUser(int userId) throws SQLException {
        Connection connection = DatabaseConnection.beginConnection();
        String selectStatement = "SELECT * FROM users WHERE User_ID = " + String.valueOf(userId);

        DatabaseQuery.setPreparedStatement(connection, selectStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        Users user;

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            int userID = resultSet.getInt("User_ID");
            String username = resultSet.getString("User_Name");
            String password = resultSet.getString("Password");
            LocalDate createDate = resultSet.getDate("Create_Date").toLocalDate();
            LocalTime createTime = resultSet.getTime("Create_Date").toLocalTime();
            LocalDateTime userCreateDate = LocalDateTime.of(createDate, createTime);
            String createdBy = resultSet.getString("Created_By");
            LocalDate updateDate = resultSet.getDate("Last_Update").toLocalDate();
            LocalTime updateTime = resultSet.getTime("Last_Update").toLocalTime();
            LocalDateTime userUpdateDate = LocalDateTime.of(updateDate, updateTime);
            String userUpdatedBy = resultSet.getString("Last_Updated_By");

            user = new Users(username, password);

            //check rows affected
            if(preparedStatement.getUpdateCount() > 0) {
                System.out.println(preparedStatement.getUpdateCount() + " row(s) affected.");
            }   else {
                System.out.println("No change.");
            }

            DatabaseConnection.closeConnection();
            return user;
        }
        DatabaseConnection.closeConnection();
        return null;
    }

    public static void updateUser(Users user) throws SQLException {
        Connection connection = DatabaseConnection.beginConnection();
        String updateStatement = "UPDATE users SET User_Name = ?, Password = ?, Last_Update = ?, Last_Updated_By = ? WHERE USER_ID = ?";

        DatabaseQuery.setPreparedStatement(connection, updateStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        String userName = user.getUserName();
        String password = user.getPassword();
        LocalDate createDate = user.getUserCreateDate().toLocalDate();
        LocalTime createTime = user.getUserCreateDate().toLocalTime();
        LocalDateTime userCreateDate = LocalDateTime.of(createDate, createTime);
        String createdBy = user.getUserCreatedBy();
        LocalDate updateDate = user.getUserUpdatedDate().toLocalDate();
        LocalTime updateTime = user.getUserUpdatedDate().toLocalTime();
        LocalDateTime userUpdateDate = LocalDateTime.of(updateDate, updateTime);
        String updatedBy = user.getUserUpdatedBy();

        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, String.valueOf(userCreateDate));
        preparedStatement.setString(4, createdBy);
        preparedStatement.setString(5, String.valueOf(userUpdateDate));
        preparedStatement.setString(6, updatedBy);

        preparedStatement.execute();

        //check rows affected
        if(preparedStatement.getUpdateCount() > 0) {
            System.out.println(preparedStatement.getUpdateCount() + " row(s) affected.");
        }   else {
            System.out.println("No change.");
        }

        DatabaseConnection.closeConnection();
    }

    public static void deleteUser(int userId) throws SQLException {

        Connection connection = DatabaseConnection.beginConnection();
        String deleteStatement = "DELETE FROM users WHERE User_ID = " + String.valueOf(userId);

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

    public static ObservableList<Users> getAllUsers() throws SQLException {
        ObservableList<Users> allUsers = FXCollections.observableArrayList();
        Connection connection = DatabaseConnection.beginConnection();
        String selectAllStatement = "SELECT * FROM users";

        DatabaseQuery.setPreparedStatement(connection, selectAllStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            int userID = resultSet.getInt("User_ID");
            String username = resultSet.getString("User_Name");
            String password = resultSet.getString("Password");
            LocalDate createDate = resultSet.getDate("Create_Date").toLocalDate();
            LocalTime createTime = resultSet.getTime("Create_Date").toLocalTime();
            LocalDateTime userCreateDate = LocalDateTime.of(createDate, createTime);
            String createdBy = resultSet.getString("Created_By");
            LocalDate updateDate = resultSet.getDate("Last_Update").toLocalDate();
            LocalTime updateTime = resultSet.getTime("Last_Update").toLocalTime();
            LocalDateTime userUpdateDate = LocalDateTime.of(updateDate, updateTime);
            String userUpdatedBy = resultSet.getString("Last_Updated_By");

            Users user = new Users(username, password);
            user.setUserId(userID);

            allUsers.add(user);

        }
        DatabaseConnection.closeConnection();
        return allUsers;
    }
}
