package Dao;

import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;

public class AppointmentImp {

    public static void addAppointment(Appointments appointment) throws SQLException {

        Connection connection = DatabaseConnection.beginConnection();
        String insertStatement = "INSERT INTO appointments(Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        DatabaseQuery.setPreparedStatement(connection, insertStatement);

    }

    public static Appointments getAppointment(int appointmentId) {

        return null;
    }

    public static void updateAppointment(Appointments appointment) {

    }

    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        return allAppointments;
    }

    public static void deleteAppointment(int appointmentId) {

    }
}
