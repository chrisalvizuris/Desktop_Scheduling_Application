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
import java.time.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.TimeZone;

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
            window.centerOnScreen();
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

            if(appointmentEnd.isBefore(appointmentStart)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Scheduling Error");
                alert.setContentText("Please make sure your appointment ends after it starts.");
                alert.showAndWait();
                return;
            }
            if(appointmentStart.isBefore(LocalDateTime.now())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Scheduling Error");
                alert.setContentText("We can not schedule an appointment that takes place in the past.");
                alert.showAndWait();
                return;
            }

            Appointments appointment = new Appointments(appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customerId);
            appointment.setContactId(contactID);
            appointment.setUserId(userId);
            appointment.setAppointmentCreateDate(LocalDateTime.now());
            appointment.setAppointmentCreatedBy(loggedInPerson.getUserName());
            appointment.setAppointmentUpdateDate(LocalDateTime.now());

            //create a list to eventually compare customer_ids for overlapping and create alert
            ArrayList<Appointments> allCustomerAppointments = AppointmentImp.getCustomerAppointments(customerId);
            for (int i = 0; i < allCustomerAppointments.size(); i++) {
                if((appointmentStart.isAfter(allCustomerAppointments.get(i).getAppointmentStart().minusMinutes(1)) && appointmentStart.isBefore(allCustomerAppointments.get(i).getAppointmentEnd())) ||
                        (appointmentEnd.isAfter(allCustomerAppointments.get(i).getAppointmentStart().plusMinutes(1))) && appointmentEnd.isBefore(allCustomerAppointments.get(i).getAppointmentEnd().minusMinutes(1))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Overlapping Appointment Warning");
                    alert.setContentText("This customer has an overlapping appointment. Please schedule another time.");
                    alert.showAndWait();
                    return;
                }
            }

            ZoneId newYorkZoneId = ZoneId.of("America/New_York");
            ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

            ZonedDateTime localZDT = ZonedDateTime.of(startDate, startTime, localZoneId);
            ZonedDateTime localToNewYorkZDT = localZDT.withZoneSameInstant(newYorkZoneId);

            if(localToNewYorkZDT.toLocalTime().isBefore(LocalTime.of(8,0)) || localToNewYorkZDT.toLocalTime().isAfter(LocalTime.of(21,59))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Appointment Time Not Accepted Warning");
                alert.setContentText("Please schedule an appointment during business hours, which is between 8:00am and 22:00pm EST.");
                alert.showAndWait();
            }   else {

                AppointmentImp.addAppointment(appointment);

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/View/mainWindow.fxml"));
                Parent mainWindowParent = loader.load();
                Scene mainWindowScene = new Scene(mainWindowParent);

                mainWindowController controller = loader.getController();
                controller.initMainWindow(loggedInPerson);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(mainWindowScene);
                window.centerOnScreen();
                window.show();
            }
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
