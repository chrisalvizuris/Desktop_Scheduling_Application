package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

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
    private ComboBox<?> contactComboBox;

    @FXML
    private ComboBox<?> typeComboBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private Spinner<?> startTimeSpinner;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Spinner<?> endTimeSpinner;

    @FXML
    private ComboBox<?> customerIdComboBox;

    @FXML
    private ComboBox<?> userIdComboBox;

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
        Parent parent = FXMLLoader.load(getClass().getResource("/View/mainWindow.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
}
