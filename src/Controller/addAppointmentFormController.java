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
import java.util.Optional;

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

    private Users loggedInPerson;

    @FXML
    private void cancelAppointmentButtonPushed(ActionEvent event) throws IOException, SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/mainWindow.fxml"));
            Parent mainWindowParent = loader.load();
            Scene mainWindowScene = new Scene(mainWindowParent);

            mainWindowController controller = loader.getController();
            controller.initMainWindow(loggedInPerson);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(mainWindowScene);
            window.show();
        }
    }

    @FXML
    private void saveAppointmentButtonPushed(ActionEvent event) throws IOException, SQLException {
        try {
            String appointmentTitle = appointmentTitleTextField.getText();
            String appointmentDescription = appointmentDescriptionTextField.getText();
            String appointmentLocation = appointmentLocationTextField.getText();
            int contactID = contactComboBox.getSelectionModel().getSelectedItem().getContactId();
            String appointmentType = typeComboBox.getSelectionModel().getSelectedItem();
            LocalDate startDate = startDatePicker.getValue();
            LocalTime startTime = LocalTime.parse(startTimeComboBox.getSelectionModel().getSelectedItem());
            LocalDateTime appointmentStart = LocalDateTime.of(startDate, startTime);
            LocalDate endDate = endDatePicker.getValue();
            LocalTime endTime = LocalTime.parse(endTimeComboBox.getSelectionModel().getSelectedItem());
            LocalDateTime appointmentEnd = LocalDateTime.of(endDate, endTime);
            int customerId = customerIdComboBox.getSelectionModel().getSelectedItem().getCustomerId();
            int userId = loggedInPerson.getUserId();

            Appointments appointment = new Appointments(appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customerId);
            appointment.setContactId(contactID);
            appointment.setUserId(userId);
            appointment.setAppointmentCreateDate(LocalDateTime.now());
            appointment.setAppointmentCreatedBy(loggedInPerson.getUserName());
            appointment.setAppointmentUpdateDate(LocalDateTime.now());

            AppointmentImp.addAppointment(appointment);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/mainWindow.fxml"));
            Parent mainWindowParent = loader.load();
            Scene mainWindowScene = new Scene(mainWindowParent);

            mainWindowController controller = loader.getController();
            controller.initMainWindow(loggedInPerson);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(mainWindowScene);
            window.show();
        }   catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Error: The data you entered is invalid. Please review and try again.");
            alert.showAndWait();
        }
    }

    public void initialize(Users user) throws SQLException {
        loggedInPerson = user;
        customerIdComboBox.setItems(CustomerImp.getAllCustomers());
        userIdComboBox.setItems(UserImp.getAllUsers());
        typeComboBox.setItems(AppointmentImp.appointmentTypes());
        contactComboBox.setItems(ContactsImp.getAllContacts());
        startTimeComboBox.setItems(AppointmentImp.getStartTimes());
        endTimeComboBox.setItems(AppointmentImp.getEndTimes());

    }
}
