package Dao;

import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppointmentImp {

    public static void addAppointment(Appointments appointment) throws SQLException {

        Connection connection = DatabaseConnection.beginConnection();
        String insertStatement = "INSERT INTO appointments(Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        DatabaseQuery.setPreparedStatement(connection, insertStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        int apptId = appointment.getAppointmentId();
        String apptTitle = appointment.getAppointmentTitle();
        String apptDescription = appointment.getAppointmentDescription();
        String apptLocation = appointment.getAppointmentLocation();
        String apptType = appointment.getAppointmentType();
        LocalDate apptDateStart = appointment.getAppointmentCreateDate().toLocalDate();
        LocalTime apptTimeStart = appointment.getAppointmentCreateDate().toLocalTime();
        LocalDateTime apptStartTime = LocalDateTime.of(apptDateStart, apptTimeStart);
        LocalDate apptDateEnd = appointment.getAppointmentEnd().toLocalDate();
        LocalTime apptTimeEnd = appointment.getAppointmentEnd().toLocalTime();
        LocalDateTime apptEndTime = LocalDateTime.of(apptDateEnd, apptTimeEnd);
        LocalDate createDate = appointment.getAppointmentCreateDate().toLocalDate();
        LocalTime createTime = appointment.getAppointmentCreateDate().toLocalTime();
        LocalDateTime apptCreateDate = LocalDateTime.of(createDate, createTime);
        String apptCreatedBy = appointment.getAppointmentCreatedBy();
        LocalDate updateDate = appointment.getAppointmentUpdateDate().toLocalDate();
        LocalTime updateTime = appointment.getAppointmentUpdateDate().toLocalTime();
        LocalDateTime apptUpdateDate = LocalDateTime.of(updateDate, updateTime);
        String apptUpdateBy = appointment.getAppointmentUpdatedBy();
        int apptCustomerId = appointment.getCustomerId();
        int apptUserId = appointment.getUserId();
        int apptContactId = appointment.getContactId();

        preparedStatement.setString(1, String.valueOf(apptId));
        preparedStatement.setString(2, apptTitle);
        preparedStatement.setString(3, apptDescription);
        preparedStatement.setString(4, apptLocation);
        preparedStatement.setString(5, apptType);
        preparedStatement.setString(6, String.valueOf(apptStartTime));
        preparedStatement.setString(7, String.valueOf(apptEndTime));
        preparedStatement.setString(8, String.valueOf(apptCreateDate));
        preparedStatement.setString(9, apptCreatedBy);
        preparedStatement.setString(10, String.valueOf(apptUpdateDate));
        preparedStatement.setString(11, apptUpdateBy);
        preparedStatement.setString(12, String.valueOf(apptCustomerId));
        preparedStatement.setString(13, String.valueOf(apptUserId));
        preparedStatement.setString(14, String.valueOf(apptContactId));

        preparedStatement.execute();

        //check rows affected
        if(preparedStatement.getUpdateCount() > 0) {
            System.out.println(preparedStatement.getUpdateCount() + " row(s) affected.");
        }   else {
            System.out.println("No change.");
        }

        DatabaseConnection.closeConnection();

    }

    public static Appointments getAppointment(int appointmentId) throws SQLException {

        Connection connection = DatabaseConnection.beginConnection();
        String selectStatement = "SELECT * FROM appointments WHERE Appointment_ID = " + String.valueOf(appointmentId);

        DatabaseQuery.setPreparedStatement(connection, selectStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        Appointments appointment;

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {

            int apptId = resultSet.getInt("Appointment_ID");
            String apptTitle = resultSet.getString("Title");
            String apptDescription = resultSet.getString("Description");
            String apptLocation = resultSet.getString("Location");
            String apptType = resultSet.getString("Type");
            LocalDate apptStart = resultSet.getDate("Start").toLocalDate();
            LocalTime apptTime = resultSet.getTime("Start").toLocalTime();
            LocalDateTime apptStartDate = LocalDateTime.of(apptStart, apptTime);
            LocalDate apptEnd = resultSet.getDate("End").toLocalDate();
            LocalTime apptEndTime = resultSet.getTime("End").toLocalTime();
            LocalDateTime apptEndDate = LocalDateTime.of(apptEnd, apptEndTime);
            LocalDate createDate = resultSet.getDate("Create_Date").toLocalDate();
            LocalTime createTime = resultSet.getTime("Create_Date").toLocalTime();
            LocalDateTime apptCreateDate = LocalDateTime.of(createDate, createTime);
            String apptCreatedBy = resultSet.getString("Created_By");
            LocalDate updateDate = resultSet.getDate("Last_Update").toLocalDate();
            LocalTime updateTime = resultSet.getTime("Last_Update").toLocalTime();
            LocalDateTime apptUpdateDate = LocalDateTime.of(updateDate, updateTime);
            String apptUpdatedBy = resultSet.getString("Last_Updated_By");
            int apptCustomerId = resultSet.getInt("Customer_ID");
            int apptUserId = resultSet.getInt("User_ID");
            int apptContactId = resultSet.getInt("Contact_ID");

            appointment = new Appointments(apptId, apptTitle, apptDescription, apptLocation, apptType, apptStartDate, apptEndDate, apptCustomerId);

            //check rows affected
            if(preparedStatement.getUpdateCount() > 0) {
                System.out.println(preparedStatement.getUpdateCount() + " row(s) affected.");
            }   else {
                System.out.println("No change.");
            }

            DatabaseConnection.closeConnection();

            return appointment;
        }
        DatabaseConnection.closeConnection();
        return null;
    }

    public static void updateAppointment(Appointments appointment) throws SQLException {

        Connection connection = DatabaseConnection.beginConnection();
        String updateStatement = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";

        DatabaseQuery.setPreparedStatement(connection, updateStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        int apptId = appointment.getAppointmentId();
        String apptTitle = appointment.getAppointmentTitle();
        String apptDescription = appointment.getAppointmentDescription();
        String apptLocation = appointment.getAppointmentLocation();
        String apptType = appointment.getAppointmentType();
        LocalDate apptDateStart = appointment.getAppointmentCreateDate().toLocalDate();
        LocalTime apptTimeStart = appointment.getAppointmentCreateDate().toLocalTime();
        LocalDateTime apptStartTime = LocalDateTime.of(apptDateStart, apptTimeStart);
        LocalDate apptDateEnd = appointment.getAppointmentEnd().toLocalDate();
        LocalTime apptTimeEnd = appointment.getAppointmentEnd().toLocalTime();
        LocalDateTime apptEndTime = LocalDateTime.of(apptDateEnd, apptTimeEnd);
        LocalDate createDate = appointment.getAppointmentCreateDate().toLocalDate();
        LocalTime createTime = appointment.getAppointmentCreateDate().toLocalTime();
        LocalDateTime apptCreateDate = LocalDateTime.of(createDate, createTime);
        String apptCreatedBy = appointment.getAppointmentCreatedBy();
        LocalDate updateDate = appointment.getAppointmentUpdateDate().toLocalDate();
        LocalTime updateTime = appointment.getAppointmentUpdateDate().toLocalTime();
        LocalDateTime apptUpdateDate = LocalDateTime.of(updateDate, updateTime);
        String apptUpdateBy = appointment.getAppointmentUpdatedBy();
        int apptCustomerId = appointment.getCustomerId();
        int apptUserId = appointment.getUserId();
        int apptContactId = appointment.getContactId();

        preparedStatement.setString(1, String.valueOf(apptId));
        preparedStatement.setString(2, apptTitle);
        preparedStatement.setString(3, apptDescription);
        preparedStatement.setString(4, apptLocation);
        preparedStatement.setString(5, apptType);
        preparedStatement.setString(6, String.valueOf(apptStartTime));
        preparedStatement.setString(7, String.valueOf(apptEndTime));
        preparedStatement.setString(8, String.valueOf(apptCreateDate));
        preparedStatement.setString(9, apptCreatedBy);
        preparedStatement.setString(10, String.valueOf(apptUpdateDate));
        preparedStatement.setString(11, apptUpdateBy);
        preparedStatement.setString(12, String.valueOf(apptCustomerId));
        preparedStatement.setString(13, String.valueOf(apptUserId));
        preparedStatement.setString(14, String.valueOf(apptContactId));

        preparedStatement.execute();

        //check rows affected
        if(preparedStatement.getUpdateCount() > 0) {
            System.out.println(preparedStatement.getUpdateCount() + " row(s) affected.");
        }   else {
            System.out.println("No change.");
        }

        DatabaseConnection.closeConnection();

    }

    public static ObservableList<Appointments> getAllAppointments() throws SQLException {
        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
        Connection connection = DatabaseConnection.beginConnection();
        String selectAllAppointments = "SELECT * FROM appointments";

        DatabaseQuery.setPreparedStatement(connection, selectAllAppointments);

        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            int apptId = resultSet.getInt("Appointment_ID");
            String apptTitle = resultSet.getString("Title");
            String apptDescription = resultSet.getString("Description");
            String apptLocation = resultSet.getString("Location");
            String apptType = resultSet.getString("Type");
            LocalDate apptStart = resultSet.getDate("Start").toLocalDate();
            LocalTime apptTime = resultSet.getTime("Start").toLocalTime();
            LocalDateTime apptStartDate = LocalDateTime.of(apptStart, apptTime);
            LocalDate apptEnd = resultSet.getDate("End").toLocalDate();
            LocalTime apptEndTime = resultSet.getTime("End").toLocalTime();
            LocalDateTime apptEndDate = LocalDateTime.of(apptEnd, apptEndTime);
            LocalDate createDate = resultSet.getDate("Create_Date").toLocalDate();
            LocalTime createTime = resultSet.getTime("Create_Date").toLocalTime();
            LocalDateTime apptCreateDate = LocalDateTime.of(createDate, createTime);
            String apptCreatedBy = resultSet.getString("Created_By");
            LocalDate updateDate = resultSet.getDate("Last_Update").toLocalDate();
            LocalTime updateTime = resultSet.getTime("Last_Update").toLocalTime();
            LocalDateTime apptUpdateDate = LocalDateTime.of(updateDate, updateTime);
            String apptUpdatedBy = resultSet.getString("Last_Updated_By");
            int apptCustomerId = resultSet.getInt("Customer_ID");
            int apptUserId = resultSet.getInt("User_ID");
            int apptContactId = resultSet.getInt("Contact_ID");

            Appointments appointment = new Appointments(apptId, apptTitle, apptDescription, apptLocation, apptType, apptStartDate, apptEndDate, apptCustomerId);

            allAppointments.add(appointment);
        }
        DatabaseConnection.closeConnection();
        return allAppointments;
    }

    public static void deleteAppointment(int appointmentId) throws SQLException {

        Connection connection = DatabaseConnection.beginConnection();
        String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID = " + String.valueOf(appointmentId);

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

    public static ObservableList<String> appointmentTypes() {
        ObservableList<String> allTypes = FXCollections.observableArrayList();
        String type1 = "General Meeting";
        String type2 = "Consultation";
        String type3 = "Quarterly Business Review";
        String type4 = "Interview";

        allTypes.add(type1);
        allTypes.add(type2);
        allTypes.add(type3);
        allTypes.add(type4);

        return allTypes;
    }
}
