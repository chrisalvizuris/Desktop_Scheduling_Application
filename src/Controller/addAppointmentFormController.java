package Controller;

import Dao.AppointmentImp;
import Dao.ContactsImp;
import Dao.CustomerImp;
import Dao.UserImp;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;

public class addAppointmentFormController {

    @FXML
    private TextField appointmentIdTextField;

    @FXML
    private TextField appointmentTitleTextField;

    @FXML
    private TextField appointmentDescriptionTextField;

    @FXML
    private TextField appointmentLocationTextField;

    @FXML
    private ComboBox<Contacts> contactComboBox;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<String> startTimeComboBox;

    @FXML
    private ComboBox<String> endTimeComboBox;

    @FXML
    private ComboBox<Customers> customerIdComboBox;

    @FXML
    private ComboBox<Users> userIdComboBox;

    @FXML
    private Button appointmentSaveButton;

    @FXML
    private Button appointmentCancelButton;

    @FXML
    private void cancelAppointmentButtonPushed(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/mainWindow.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void saveAppointmentButtonPushed(ActionEvent event) throws IOException, SQLException {
        try {
            String appointmentTitle = appointmentTitleTextField.getText();
            String appointmentDescription = appointmentDescriptionTextField.getText();
            String appointmentLocation = appointmentLocationTextField.getText();
            int contactId = contactComboBox.getSelectionModel().getSelectedItem().getContactId();
            String appointmentType = typeComboBox.getSelectionModel().getSelectedItem();
            LocalDate startDate = startDatePicker.getValue();
            LocalTime startTime = LocalTime.parse(startTimeComboBox.getSelectionModel().getSelectedItem());
            LocalDateTime appointmentStart = LocalDateTime.of(startDate, startTime);
            LocalDate endDate = endDatePicker.getValue();
            LocalTime endTime = LocalTime.parse(endTimeComboBox.getSelectionModel().getSelectedItem());
            LocalDateTime appointmentEnd = LocalDateTime.of(endDate, endTime);
            int customerId = customerIdComboBox.getSelectionModel().getSelectedItem().getCustomerId();
            int userId = userIdComboBox.getSelectionModel().getSelectedItem().getUserId();

            Appointments appointment = new Appointments(appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customerId);
            appointment.setContactId(contactId);
            appointment.setUserId(userId);
            appointment.setAppointmentCreateDate(LocalDateTime.now());
            appointment.setAppointmentCreatedBy("admin");
            //TODO: find a way to automate this createdby
            appointment.setAppointmentUpdateDate(LocalDateTime.now());

            AppointmentImp.addAppointment(appointment);

            Parent parent = FXMLLoader.load(getClass().getResource("/View/mainWindow.fxml"));
            Scene scene = new Scene(parent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();
        }   catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Error: The data you entered is invalid. Please review and try again.");
            alert.showAndWait();
        }
    }

    public void initialize() throws SQLException {
        customerIdComboBox.setItems(CustomerImp.getAllCustomers());
        userIdComboBox.setItems(UserImp.getAllUsers());
        typeComboBox.setItems(AppointmentImp.appointmentTypes());
        contactComboBox.setItems(ContactsImp.getAllContacts());
        startTimeComboBox.setItems(AppointmentImp.getStartTimes());
        endTimeComboBox.setItems(AppointmentImp.getEndTimes());

    }
}
