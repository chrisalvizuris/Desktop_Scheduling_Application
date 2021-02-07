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

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class updateAppointmentFormController {

    @FXML
    private TextField updateAppointmentIDTextField;

    @FXML
    private TextField updateAppointmentTitleTextField;

    @FXML
    private TextField updateAppointmentDescriptionTextField;

    @FXML
    private TextField updateAppointmentLocationTextField;

    @FXML
    private ComboBox<Contacts> updateContactComboBox;

    @FXML
    private ComboBox<String> updateTypeComboBox;

    @FXML
    private DatePicker updateStartDatePicker;

    @FXML
    private DatePicker updateEndDatePicker;

    @FXML
    private ComboBox<Customers> updateCustomerIdComboBox;

    @FXML
    private ComboBox<Users> updateUserIdComboBox;

    @FXML
    private Button updateSaveAppointmentButton;

    @FXML
    private Button updateCancelAppointmentButton;

    @FXML
    private ComboBox<String> startTimeComboBox;

    @FXML
    private ComboBox<String> endTimeComboBox;

    @FXML
    private void updateSaveButtonPushed(ActionEvent event) throws IOException, SQLException {
        try {
            int id = Integer.parseInt(updateAppointmentIDTextField.getText());
            String newTitle = updateAppointmentTitleTextField.getText();
            String newDescription = updateAppointmentDescriptionTextField.getText();
            String newLocation = updateAppointmentLocationTextField.getText();
            int newContactId = updateContactComboBox.getSelectionModel().getSelectedItem().getContactId();
            String newType = updateTypeComboBox.getSelectionModel().getSelectedItem();
            LocalDate updateStartDay = updateStartDatePicker.getValue();
            LocalTime updateStartTime = LocalTime.parse(startTimeComboBox.getSelectionModel().getSelectedItem());
            LocalDateTime newStart = LocalDateTime.of(updateStartDay, updateStartTime);
            LocalDate updateEndDay = updateEndDatePicker.getValue();
            LocalTime updateEndTime = LocalTime.parse(endTimeComboBox.getSelectionModel().getSelectedItem());
            LocalDateTime newEnd = LocalDateTime.of(updateEndDay, updateEndTime);
            int newCustomerId = updateCustomerIdComboBox.getSelectionModel().getSelectedItem().getCustomerId();
            int newUserId = updateUserIdComboBox.getSelectionModel().getSelectedItem().getUserId();

            Appointments appointment = new Appointments(newTitle, newDescription, newLocation, newType, newStart, newEnd, newCustomerId);
            appointment.setAppointmentId(id);
            appointment.setContactId(newContactId);
            appointment.setUserId(newUserId);
            appointment.setAppointmentUpdateDate(LocalDateTime.now());
            appointment.setAppointmentUpdatedBy("admin");
            //TODO: find a way to automate this

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

    @FXML
    private void updateCancelButtonPushed(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/mainWindow.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void initialize() throws SQLException {
        updateContactComboBox.setItems(ContactsImp.getAllContacts());
        updateCustomerIdComboBox.setItems(CustomerImp.getAllCustomers());
        updateUserIdComboBox.setItems(UserImp.getAllUsers());
        updateTypeComboBox.setItems(AppointmentImp.appointmentTypes());
        startTimeComboBox.setItems(AppointmentImp.getStartTimes());
        endTimeComboBox.setItems(AppointmentImp.getEndTimes());
    }

}

