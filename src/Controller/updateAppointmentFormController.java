package Controller;

import Model.Contacts;
import Model.Customers;
import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
    private void updateSaveButtonPushed(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/mainWindow.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void updateCancelButtonPushed(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/mainWindow.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

}

