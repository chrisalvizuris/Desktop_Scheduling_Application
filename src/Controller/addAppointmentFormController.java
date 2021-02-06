package Controller;

import Dao.AppointmentImp;
import Dao.ContactsImp;
import Dao.CustomerImp;
import Dao.UserImp;
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
    private Spinner<?> startTimeSpinner;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Spinner<?> endTimeSpinner;

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
    private void saveAppointmentButtonPushed(ActionEvent event) throws IOException {
        String appointmentTitle = appointmentTitleTextField.getText();
        String appointmentDescription = appointmentDescriptionTextField.getText();
        String appointmentLocation = appointmentLocationTextField.getText();

        Parent parent = FXMLLoader.load(getClass().getResource("/View/mainWindow.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void initialize() throws SQLException {
        customerIdComboBox.setItems(CustomerImp.getAllCustomers());
        userIdComboBox.setItems(UserImp.getAllUsers());
        typeComboBox.setItems(AppointmentImp.appointmentTypes());
        contactComboBox.setItems(ContactsImp.getAllContacts());
    }
}
