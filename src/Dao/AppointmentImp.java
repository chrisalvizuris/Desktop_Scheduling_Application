package Dao;

import Model.Appointments;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;

public class AppointmentImp {

    public static void addAppointment(Appointments appointment) throws SQLException {

        Connection connection = DatabaseConnection.beginConnection();
        String insertStatement = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        DatabaseQuery.setPreparedStatement(connection, insertStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        String apptTitle = appointment.getAppointmentTitle();
        String apptDescription = appointment.getAppointmentDescription();
        String apptLocation = appointment.getAppointmentLocation();
        String apptType = appointment.getAppointmentType();
        LocalDate apptDateStart = appointment.getAppointmentStart().toLocalDate();
        LocalTime apptTimeStart = appointment.getAppointmentStart().toLocalTime();
        LocalDateTime apptStartTime = LocalDateTime.of(apptDateStart, apptTimeStart);
        Timestamp startTimestamp = Timestamp.valueOf(apptStartTime);
        LocalDate apptDateEnd = appointment.getAppointmentEnd().toLocalDate();
        LocalTime apptTimeEnd = appointment.getAppointmentEnd().toLocalTime();
        LocalDateTime apptEndTime = LocalDateTime.of(apptDateEnd, apptTimeEnd);
        Timestamp endTimestamp = Timestamp.valueOf(apptEndTime);
        LocalDate createDate = appointment.getAppointmentCreateDate().toLocalDate();
        LocalTime createTime = appointment.getAppointmentCreateDate().toLocalTime();
        LocalDateTime apptCreateDate = LocalDateTime.of(createDate, createTime);
        Timestamp createTimestamp = Timestamp.valueOf(apptCreateDate);
        String apptCreatedBy = appointment.getAppointmentCreatedBy();
        LocalDate updateDate = appointment.getAppointmentUpdateDate().toLocalDate();
        LocalTime updateTime = appointment.getAppointmentUpdateDate().toLocalTime();
        LocalDateTime apptUpdateDate = LocalDateTime.of(updateDate, updateTime);
        Timestamp updateTimestamp = Timestamp.valueOf(apptUpdateDate);
        String apptUpdateBy = appointment.getAppointmentUpdatedBy();
        int apptCustomerId = appointment.getCustomerId();
        int apptUserId = appointment.getUserId();
        int apptContactId = appointment.getContactId();

        preparedStatement.setString(1, apptTitle);
        preparedStatement.setString(2, apptDescription);
        preparedStatement.setString(3, apptLocation);
        preparedStatement.setString(4, apptType);
        preparedStatement.setTimestamp(5, startTimestamp);
        preparedStatement.setTimestamp(6, endTimestamp);
        preparedStatement.setTimestamp(7, createTimestamp);
        preparedStatement.setString(8, apptCreatedBy);
        preparedStatement.setTimestamp(9, updateTimestamp);
        preparedStatement.setString(10, apptUpdateBy);
        preparedStatement.setString(11, String.valueOf(apptCustomerId));
        preparedStatement.setString(12, String.valueOf(apptUserId));
        preparedStatement.setString(13, String.valueOf(apptContactId));

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

            appointment = new Appointments(apptTitle, apptDescription, apptLocation, apptType, apptStartDate, apptEndDate, apptCustomerId);
            appointment.setAppointmentId(apptId);
            appointment.setAppointmentCreateDate(apptCreateDate);
            appointment.setAppointmentCreatedBy(apptCreatedBy);
            appointment.setAppointmentUpdateDate(apptUpdateDate);
            appointment.setAppointmentUpdatedBy(apptUpdatedBy);
            appointment.setUserId(apptUserId);
            appointment.setContactId(apptContactId);

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
        LocalDate apptDateStart = appointment.getAppointmentStart().toLocalDate();
        LocalTime apptTimeStart = appointment.getAppointmentStart().toLocalTime();
        LocalDateTime apptStartTime = LocalDateTime.of(apptDateStart, apptTimeStart);
        Timestamp startTS = Timestamp.valueOf(apptStartTime);
        LocalDate apptDateEnd = appointment.getAppointmentEnd().toLocalDate();
        LocalTime apptTimeEnd = appointment.getAppointmentEnd().toLocalTime();
        LocalDateTime apptEndTime = LocalDateTime.of(apptDateEnd, apptTimeEnd);
        Timestamp endTS = Timestamp.valueOf(apptEndTime);
        LocalDate updateDate = appointment.getAppointmentUpdateDate().toLocalDate();
        LocalTime updateTime = appointment.getAppointmentUpdateDate().toLocalTime();
        LocalDateTime apptUpdateDate = LocalDateTime.of(updateDate, updateTime);
        Timestamp updateTS = Timestamp.valueOf(apptUpdateDate);
        String apptUpdateBy = appointment.getAppointmentUpdatedBy();
        int apptCustomerId = appointment.getCustomerId();
        int apptUserId = appointment.getUserId();
        int apptContactId = appointment.getContactId();

        preparedStatement.setString(1, apptTitle);
        preparedStatement.setString(2, apptDescription);
        preparedStatement.setString(3, apptLocation);
        preparedStatement.setString(4, apptType);
        preparedStatement.setTimestamp(5, startTS);
        preparedStatement.setTimestamp(6, endTS);
        preparedStatement.setTimestamp(7, updateTS);
        preparedStatement.setString(8, apptUpdateBy);
        preparedStatement.setString(9, String.valueOf(apptCustomerId));
        preparedStatement.setString(10, String.valueOf(apptUserId));
        preparedStatement.setString(11, String.valueOf(apptContactId));
        preparedStatement.setString(12, String.valueOf(apptId));

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

            Appointments appointment = new Appointments(apptTitle, apptDescription, apptLocation, apptType, apptStartDate, apptEndDate, apptCustomerId);
            appointment.setAppointmentId(apptId);
            appointment.setAppointmentCreateDate(apptCreateDate);
            appointment.setAppointmentCreatedBy(apptCreatedBy);
            appointment.setAppointmentUpdateDate(apptUpdateDate);
            appointment.setAppointmentUpdatedBy(apptUpdatedBy);
            appointment.setUserId(apptUserId);
            appointment.setContactId(apptContactId);

            allAppointments.add(appointment);
        }
        DatabaseConnection.closeConnection();
        return allAppointments;
    }

    public static ObservableList<Appointments> getAllAppointmentsThisWeek() throws SQLException {
        ObservableList<Appointments> allAppointmentsThisWeek = FXCollections.observableArrayList();
        Connection connection = DatabaseConnection.beginConnection();
        String selectThisMonthStatement = "SELECT * FROM appointments WHERE YEARWEEK(Start) = YEARWEEK(CURDATE())";

        DatabaseQuery.setPreparedStatement(connection, selectThisMonthStatement);
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

            Appointments appointment = new Appointments(apptTitle, apptDescription, apptLocation, apptType, apptStartDate, apptEndDate, apptCustomerId);
            appointment.setAppointmentId(apptId);
            appointment.setAppointmentCreateDate(apptCreateDate);
            appointment.setAppointmentCreatedBy(apptCreatedBy);
            appointment.setAppointmentUpdateDate(apptUpdateDate);
            appointment.setAppointmentUpdatedBy(apptUpdatedBy);
            appointment.setUserId(apptUserId);
            appointment.setContactId(apptContactId);

            allAppointmentsThisWeek.add(appointment);

            //sort list by start date
            Comparator<Appointments> comparator = (appointment1, appointment2) -> appointment1.getAppointmentStart().compareTo(appointment2.getAppointmentStart());
            allAppointmentsThisWeek.sort(comparator);
        }
        DatabaseConnection.closeConnection();
        return allAppointmentsThisWeek;
    }

    public static ObservableList<Appointments> getAllAppointmentsThisMonth() throws SQLException {
        ObservableList<Appointments> allAppointmentsThisMonth = FXCollections.observableArrayList();
        Connection connection = DatabaseConnection.beginConnection();
        String selectThisMonthStatement = "SELECT * FROM appointments WHERE MONTH(Start) = MONTH(CURDATE())";

        DatabaseQuery.setPreparedStatement(connection, selectThisMonthStatement);
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

            Appointments appointment = new Appointments(apptTitle, apptDescription, apptLocation, apptType, apptStartDate, apptEndDate, apptCustomerId);
            appointment.setAppointmentId(apptId);
            appointment.setContactId(apptContactId);
            appointment.setUserId(apptUserId);
            appointment.setAppointmentUpdatedBy(apptUpdatedBy);
            appointment.setAppointmentUpdateDate(apptUpdateDate);
            appointment.setAppointmentCreateDate(apptCreateDate);
            appointment.setAppointmentCreatedBy(apptCreatedBy);

            allAppointmentsThisMonth.add(appointment);

            //sort list by start date
            Comparator<Appointments> comparator = (appointment1, appointment2) -> appointment1.getAppointmentStart().compareTo(appointment2.getAppointmentStart());
            allAppointmentsThisMonth.sort(comparator);
        }
        DatabaseConnection.closeConnection();
        return allAppointmentsThisMonth;
    }

    public static ObservableList<Appointments> getAllUserAppointmentsMonth(Users user) throws SQLException {
        ObservableList<Appointments> allUserAppointmentsThisMonth = FXCollections.observableArrayList();
        Connection connection = DatabaseConnection.beginConnection();
        String selectStatement = "SELECT * FROM appointments WHERE MONTH(Start) = MONTH(CURDATE()) AND User_ID = " + String.valueOf(user.getUserId());
        DatabaseQuery.setPreparedStatement(connection, selectStatement);
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
            Timestamp startTS = resultSet.getTimestamp("Start");
            LocalDate apptEnd = resultSet.getDate("End").toLocalDate();
            LocalTime apptEndTime = resultSet.getTime("End").toLocalTime();
            LocalDateTime apptEndDate = LocalDateTime.of(apptEnd, apptEndTime);
            Timestamp endTS = resultSet.getTimestamp("End");
            LocalDate createDate = resultSet.getDate("Create_Date").toLocalDate();
            LocalTime createTime = resultSet.getTime("Create_Date").toLocalTime();
            LocalDateTime apptCreateDate = LocalDateTime.of(createDate, createTime);
            Timestamp createTS = resultSet.getTimestamp("Create_Date");
            String apptCreatedBy = resultSet.getString("Created_By");
            LocalDate updateDate = resultSet.getDate("Last_Update").toLocalDate();
            LocalTime updateTime = resultSet.getTime("Last_Update").toLocalTime();
            LocalDateTime apptUpdateDate = LocalDateTime.of(updateDate, updateTime);
            Timestamp updateTS = resultSet.getTimestamp("Last_Update");
            String apptUpdatedBy = resultSet.getString("Last_Updated_By");
            int apptCustomerId = resultSet.getInt("Customer_ID");
            int apptUserId = resultSet.getInt("User_ID");
            int apptContactId = resultSet.getInt("Contact_ID");

            Appointments appointment = new Appointments(apptTitle, apptDescription, apptLocation, apptType, startTS.toLocalDateTime(), endTS.toLocalDateTime(), apptCustomerId);
            appointment.setAppointmentId(apptId);
            appointment.setContactId(apptContactId);
            appointment.setUserId(apptUserId);
            appointment.setAppointmentUpdatedBy(apptUpdatedBy);
            appointment.setAppointmentUpdateDate(updateTS.toLocalDateTime());
            appointment.setAppointmentCreateDate(createTS.toLocalDateTime());
            appointment.setAppointmentCreatedBy(apptCreatedBy);


            allUserAppointmentsThisMonth.add(appointment);
            //sort list by start date
            Comparator<Appointments> comparator = (appointment1, appointment2) -> appointment1.getAppointmentStart().compareTo(appointment2.getAppointmentStart());
            allUserAppointmentsThisMonth.sort(comparator);
        }
        DatabaseConnection.closeConnection();
        return allUserAppointmentsThisMonth;
    }

    public static ObservableList<Appointments> getAllUserAppointmentsWeek(Users user) throws SQLException {
        ObservableList<Appointments> allUserAppointmentsThisWeek = FXCollections.observableArrayList();
        Connection connection = DatabaseConnection.beginConnection();
        String selectThisMonthStatement = "SELECT * FROM appointments WHERE YEARWEEK(Start) = YEARWEEK(CURDATE()) AND User_ID = " + String.valueOf(user.getUserId());

        DatabaseQuery.setPreparedStatement(connection, selectThisMonthStatement);
        PreparedStatement preparedStatement = DatabaseQuery.getPreparedStatement();

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int apptId = resultSet.getInt("Appointment_ID");
            String apptTitle = resultSet.getString("Title");
            String apptDescription = resultSet.getString("Description");
            String apptLocation = resultSet.getString("Location");
            String apptType = resultSet.getString("Type");
            LocalDate apptStart = resultSet.getDate("Start").toLocalDate();
            LocalTime apptTime = resultSet.getTime("Start").toLocalTime();
            LocalDateTime apptStartDate = LocalDateTime.of(apptStart, apptTime);
            Timestamp startTS = resultSet.getTimestamp("Start");
            LocalDate apptEnd = resultSet.getDate("End").toLocalDate();
            LocalTime apptEndTime = resultSet.getTime("End").toLocalTime();
            LocalDateTime apptEndDate = LocalDateTime.of(apptEnd, apptEndTime);
            Timestamp endTS = resultSet.getTimestamp("End");
            LocalDate createDate = resultSet.getDate("Create_Date").toLocalDate();
            LocalTime createTime = resultSet.getTime("Create_Date").toLocalTime();
            LocalDateTime apptCreateDate = LocalDateTime.of(createDate, createTime);
            Timestamp createTS = resultSet.getTimestamp("Create_Date");
            String apptCreatedBy = resultSet.getString("Created_By");
            LocalDate updateDate = resultSet.getDate("Last_Update").toLocalDate();
            LocalTime updateTime = resultSet.getTime("Last_Update").toLocalTime();
            LocalDateTime apptUpdateDate = LocalDateTime.of(updateDate, updateTime);
            Timestamp updateTS = resultSet.getTimestamp("Last_Update");
            String apptUpdatedBy = resultSet.getString("Last_Updated_By");
            int apptCustomerId = resultSet.getInt("Customer_ID");
            int apptUserId = resultSet.getInt("User_ID");
            int apptContactId = resultSet.getInt("Contact_ID");

            Appointments appointment = new Appointments(apptTitle, apptDescription, apptLocation, apptType, startTS.toLocalDateTime(), endTS.toLocalDateTime(), apptCustomerId);
            appointment.setAppointmentId(apptId);
            appointment.setAppointmentCreateDate(createTS.toLocalDateTime());
            appointment.setAppointmentCreatedBy(apptCreatedBy);
            appointment.setAppointmentUpdateDate(updateTS.toLocalDateTime());
            appointment.setAppointmentUpdatedBy(apptUpdatedBy);
            appointment.setUserId(apptUserId);
            appointment.setContactId(apptContactId);

            allUserAppointmentsThisWeek.add(appointment);

            //sort list by start date
            Comparator<Appointments> comparator = (appointment1, appointment2) -> appointment1.getAppointmentStart().compareTo(appointment2.getAppointmentStart());
            allUserAppointmentsThisWeek.sort(comparator);

        }
        DatabaseConnection.closeConnection();
        return allUserAppointmentsThisWeek;
    }

    public static ObservableList<Appointments> allUserAppointments(Users user) throws SQLException {
        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
        Connection connection = DatabaseConnection.beginConnection();
        String selectAllStatement = "SELECT * FROM appointments WHERE User_ID = " + String.valueOf(user.getUserId());
        DatabaseQuery.setPreparedStatement(connection, selectAllStatement);
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
            Timestamp startTS = resultSet.getTimestamp("Start");
            LocalDate apptEnd = resultSet.getDate("End").toLocalDate();
            LocalTime apptEndTime = resultSet.getTime("End").toLocalTime();
            LocalDateTime apptEndDate = LocalDateTime.of(apptEnd, apptEndTime);
            Timestamp endTS = resultSet.getTimestamp("End");
            LocalDate createDate = resultSet.getDate("Create_Date").toLocalDate();
            LocalTime createTime = resultSet.getTime("Create_Date").toLocalTime();
            LocalDateTime apptCreateDate = LocalDateTime.of(createDate, createTime);
            Timestamp createTS = resultSet.getTimestamp("Create_Date");
            String apptCreatedBy = resultSet.getString("Created_By");
            LocalDate updateDate = resultSet.getDate("Last_Update").toLocalDate();
            LocalTime updateTime = resultSet.getTime("Last_Update").toLocalTime();
            LocalDateTime apptUpdateDate = LocalDateTime.of(updateDate, updateTime);
            Timestamp updateTS = resultSet.getTimestamp("Last_Update");
            String apptUpdatedBy = resultSet.getString("Last_Updated_By");
            int apptCustomerId = resultSet.getInt("Customer_ID");
            int apptUserId = resultSet.getInt("User_ID");
            int apptContactId = resultSet.getInt("Contact_ID");

            Appointments appointment = new Appointments(apptTitle, apptDescription, apptLocation, apptType, startTS.toLocalDateTime(), endTS.toLocalDateTime(), apptCustomerId);
            appointment.setAppointmentId(apptId);
            appointment.setAppointmentCreateDate(createTS.toLocalDateTime());
            appointment.setAppointmentCreatedBy(apptCreatedBy);
            appointment.setAppointmentUpdateDate(updateTS.toLocalDateTime());
            appointment.setAppointmentUpdatedBy(apptUpdatedBy);
            appointment.setUserId(apptUserId);
            appointment.setContactId(apptContactId);

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

    public static ObservableList<String> getStartTimes() {
        ObservableList<String> allStartTimes = FXCollections.observableArrayList();
        String time1 = "05:00:00";
        String time2 = "05:30:00";
        String time3 = "06:00:00";
        String time4 = "06:30:00";
        String time5 = "07:00:00";
        String time6 = "07:30:00";
        String time7 = "08:00:00";
        String time8 = "08:30:00";
        String time9 = "09:00:00";
        String time10 = "09:30:00";
        String time11 = "10:00:00";
        String time12 = "10:30:00";
        String time13 = "11:00:00";
        String time14 = "11:30:00";
        String time15 = "12:00:00";
        String time16 = "12:30:00";
        String time17 = "13:00:00";
        String time18 = "13:30:00";
        String time19 = "14:00:00";
        String time20 = "14:30:00";
        String time21 = "15:00:00";
        String time22 = "15:30:00";
        String time23 = "16:00:00";
        String time24 = "16:30:00";
        String time25 = "17:00:00";
        String time26 = "17:30:00";
        String time27 = "18:00:00";
        String time28 = "18:30:00";
        String time29 = "19:00:00";
        String time30 = "19:30:00";
        String time31 = "20:00:00";
        String time32 = "20:30:00";
        String time33 = "21:00:00";
        String time34 = "21:30:00";
        String time35 = "22:00:00";
        String time36 = "22:30:00";
        String time37 = "23:00:00";
        String time38 = "23:30:00";

        allStartTimes.add(time1);
        allStartTimes.add(time2);
        allStartTimes.add(time3);
        allStartTimes.add(time4);
        allStartTimes.add(time5);
        allStartTimes.add(time6);
        allStartTimes.add(time7);
        allStartTimes.add(time8);
        allStartTimes.add(time9);
        allStartTimes.add(time10);
        allStartTimes.add(time11);
        allStartTimes.add(time12);
        allStartTimes.add(time13);
        allStartTimes.add(time14);
        allStartTimes.add(time15);
        allStartTimes.add(time16);
        allStartTimes.add(time17);
        allStartTimes.add(time18);
        allStartTimes.add(time19);
        allStartTimes.add(time20);
        allStartTimes.add(time21);
        allStartTimes.add(time22);
        allStartTimes.add(time23);
        allStartTimes.add(time24);
        allStartTimes.add(time25);
        allStartTimes.add(time26);
        allStartTimes.add(time27);
        allStartTimes.add(time28);
        allStartTimes.add(time29);
        allStartTimes.add(time30);
        allStartTimes.add(time31);
        allStartTimes.add(time32);
        allStartTimes.add(time33);
        allStartTimes.add(time34);
        allStartTimes.add(time35);
        allStartTimes.add(time36);
        allStartTimes.add(time37);
        allStartTimes.add(time38);

        return allStartTimes;
    }

    public static ObservableList<String> getEndTimes() {
        ObservableList<String> allEndTimes = FXCollections.observableArrayList();

        String time1 = "05:00:00";
        String time2 = "05:30:00";
        String time3 = "06:00:00";
        String time4 = "06:30:00";
        String time5 = "07:00:00";
        String time6 = "07:30:00";
        String time7 = "08:00:00";
        String time8 = "08:30:00";
        String time9 = "09:00:00";
        String time10 = "09:30:00";
        String time11 = "10:00:00";
        String time12 = "10:30:00";
        String time13 = "11:00:00";
        String time14 = "11:30:00";
        String time15 = "12:00:00";
        String time16 = "12:30:00";
        String time17 = "13:00:00";
        String time18 = "13:30:00";
        String time19 = "14:00:00";
        String time20 = "14:30:00";
        String time21 = "15:00:00";
        String time22 = "15:30:00";
        String time23 = "16:00:00";
        String time24 = "16:30:00";
        String time25 = "17:00:00";
        String time26 = "17:30:00";
        String time27 = "18:00:00";
        String time28 = "18:30:00";
        String time29 = "19:00:00";
        String time30 = "19:30:00";
        String time31 = "20:00:00";
        String time32 = "20:30:00";
        String time33 = "21:00:00";
        String time34 = "21:30:00";
        String time35 = "22:00:00";
        String time36 = "22:30:00";
        String time37 = "23:00:00";
        String time38 = "23:30:00";

        allEndTimes.add(time1);
        allEndTimes.add(time2);
        allEndTimes.add(time3);
        allEndTimes.add(time4);
        allEndTimes.add(time5);
        allEndTimes.add(time6);
        allEndTimes.add(time7);
        allEndTimes.add(time8);
        allEndTimes.add(time9);
        allEndTimes.add(time10);
        allEndTimes.add(time11);
        allEndTimes.add(time12);
        allEndTimes.add(time13);
        allEndTimes.add(time14);
        allEndTimes.add(time15);
        allEndTimes.add(time16);
        allEndTimes.add(time17);
        allEndTimes.add(time18);
        allEndTimes.add(time19);
        allEndTimes.add(time20);
        allEndTimes.add(time21);
        allEndTimes.add(time22);
        allEndTimes.add(time23);
        allEndTimes.add(time24);
        allEndTimes.add(time25);
        allEndTimes.add(time26);
        allEndTimes.add(time27);
        allEndTimes.add(time28);
        allEndTimes.add(time29);
        allEndTimes.add(time30);
        allEndTimes.add(time31);
        allEndTimes.add(time32);
        allEndTimes.add(time33);
        allEndTimes.add(time34);
        allEndTimes.add(time35);
        allEndTimes.add(time36);
        allEndTimes.add(time37);
        allEndTimes.add(time38);

        return allEndTimes;

    }
}
