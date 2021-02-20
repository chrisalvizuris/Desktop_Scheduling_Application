package Controller;

import Dao.AppointmentImp;
import Dao.ContactsImp;
import Dao.CustomerImp;
import Dao.UserImp;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.TimeZone;

public class updateAppointmentFormController {

    private Appointments updatedAppointment;

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
    private ComboBox<String> startTimeComboBox;

    @FXML
    private ComboBox<String> endTimeComboBox;

    private Users loggedInUser;

    @FXML
    private void updateSaveButtonPushed(ActionEvent event) throws IOException, SQLException {
        try {

            //assign the entered fields to variables when save button is pushed
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

            if(newEnd.isBefore(newStart)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Scheduling Error");
                alert.setContentText("Please make sure your appointment ends after it starts.");
                alert.showAndWait();
                return;
            }
            if(newStart.isBefore(LocalDateTime.now())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Scheduling Error");
                alert.setContentText("We can not schedule an appointment that takes place in the past.");
                alert.showAndWait();
                return;
            }

            //create an appointment object from the information that was passed
            Appointments appointment = new Appointments(newTitle, newDescription, newLocation, newType, newStart, newEnd, newCustomerId);
            appointment.setAppointmentId(id);
            appointment.setContactId(newContactId);
            appointment.setUserId(newUserId);
            appointment.setAppointmentUpdateDate(LocalDateTime.now());
            appointment.setAppointmentUpdatedBy(loggedInUser.getUserName());

            //create a list to eventually compare customer_ids for overlapping and create alert
            ArrayList<Appointments> allCustomerAppointments = AppointmentImp.getCustomerAppointments(newCustomerId);
            for (int i = 0; i < allCustomerAppointments.size(); i++) {
                if((newStart.isAfter(allCustomerAppointments.get(i).getAppointmentStart().minusMinutes(1)) && newStart.isBefore(allCustomerAppointments.get(i).getAppointmentEnd())) ||
                        (newEnd.isAfter(allCustomerAppointments.get(i).getAppointmentStart().plusMinutes(1))) && newEnd.isBefore(allCustomerAppointments.get(i).getAppointmentEnd().minusMinutes(1))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Overlapping Appointment Warning");
                    alert.setContentText("This customer has an overlapping appointment. Please schedule another time.");
                    alert.showAndWait();
                    return;
                }
            }

            //start checking if the scheduled start time falls within the business hours in EST. I used New York for reference
            ZoneId newYorkZoneId = ZoneId.of("America/New_York");
            ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

            ZonedDateTime localZDT = ZonedDateTime.of(updateStartDay, updateStartTime, localZoneId);
            ZonedDateTime localToNewYorkZDT = localZDT.withZoneSameInstant(newYorkZoneId);

            if(localToNewYorkZDT.toLocalTime().isBefore(LocalTime.of(8,0)) || localToNewYorkZDT.toLocalTime().isAfter(LocalTime.of(21,59))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Appointment Time Not Accepted Warning");
                alert.setContentText("Please schedule an appointment during business hours, which is between 8:00am and 22:00pm EST.");
                alert.showAndWait();
            }   else {
                //if everything looks good, add object to database and change scenes.
                AppointmentImp.updateAppointment(appointment);


                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/View/mainWindow.fxml"));
                Parent mainWindowParent = loader.load();
                Scene mainWindowScene = new Scene(mainWindowParent);

                mainWindowController controller = loader.getController();
                controller.initMainWindow(loggedInUser);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(mainWindowScene);
                window.show();
            }
        }   catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Error: The data you entered is invalid. Please review and try again.");
            alert.showAndWait();
        }
    }

    @FXML
    private void updateCancelButtonPushed(ActionEvent event) throws IOException, SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/mainWindow.fxml"));
            Parent mainWindowParent = loader.load();
            Scene mainWindowScene = new Scene(mainWindowParent);

            mainWindowController controller = loader.getController();
            controller.initMainWindow(loggedInUser);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(mainWindowScene);
            window.show();
        }
    }

    public void initUpdateAppointment(Appointments appointmentToUpdate, Users user) throws SQLException {
        loggedInUser = user;
        updateContactComboBox.setItems(ContactsImp.getAllContacts());
        updateCustomerIdComboBox.setItems(CustomerImp.getAllCustomers());
        updateUserIdComboBox.setItems(UserImp.getAllUsers());
        updateTypeComboBox.setItems(AppointmentImp.appointmentTypes());
        startTimeComboBox.setItems(AppointmentImp.getStartTimes());
        endTimeComboBox.setItems(AppointmentImp.getEndTimes());

        updatedAppointment = appointmentToUpdate;
        updateAppointmentIDTextField.setText(String.valueOf(appointmentToUpdate.getAppointmentId()));
        updateAppointmentTitleTextField.setText(appointmentToUpdate.getAppointmentTitle());
        updateAppointmentDescriptionTextField.setText(appointmentToUpdate.getAppointmentDescription());
        updateAppointmentLocationTextField.setText(appointmentToUpdate.getAppointmentLocation());
        updateContactComboBox.setValue(ContactsImp.getContact(appointmentToUpdate.getContactId()));
        updateTypeComboBox.setValue(appointmentToUpdate.getAppointmentType());
        updateStartDatePicker.setValue(appointmentToUpdate.getAppointmentStart().toLocalDate());
        startTimeComboBox.setValue(String.valueOf(appointmentToUpdate.getAppointmentStart().toLocalTime()));
        updateEndDatePicker.setValue(appointmentToUpdate.getAppointmentEnd().toLocalDate());
        endTimeComboBox.setValue(String.valueOf(appointmentToUpdate.getAppointmentEnd().toLocalTime()));
        updateCustomerIdComboBox.setValue(CustomerImp.getCustomer(appointmentToUpdate.getCustomerId()));
        updateUserIdComboBox.setValue(UserImp.getUser(appointmentToUpdate.getUserId()));

    }

}

