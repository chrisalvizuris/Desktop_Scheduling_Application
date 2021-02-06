package Controller;

import Dao.CountriesImp;
import Dao.CustomerImp;
import Dao.DivisionsImp;
import Model.Countries;
import Model.Customers;
import Model.FirstLevelDivision;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class addCustomerFormController {

    @FXML
    private AnchorPane divisionComboBox;

    @FXML
    private TextField customerIdTextField;

    @FXML
    private TextField customerNameTextField;

    @FXML
    private TextField customerAddressTextField;

    @FXML
    private TextField customerPostalCodeTextField;

    @FXML
    private TextField customerPhoneTextField;

    @FXML
    private ComboBox<Countries> countryComboBox;

    @FXML
    private ComboBox<FirstLevelDivision> divisionsComboBox;

    @FXML
    private Button customerSaveButton;

    @FXML
    private Button customerCancelButton;

    @FXML
    private void cancelCustomerButtonPushed(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/mainWindow.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void saveCustomerButtonPushed(ActionEvent event) throws IOException, SQLException {
        try {
            String customerName = customerNameTextField.getText();
            String customerAddress = customerAddressTextField.getText();
            String customerPostal = customerPostalCodeTextField.getText();
            String customerPhone = customerPhoneTextField.getText();
            Countries country = countryComboBox.getSelectionModel().getSelectedItem();
            FirstLevelDivision city = divisionsComboBox.getSelectionModel().getSelectedItem();


            LocalDateTime createDate = LocalDateTime.now();
            String createdBy = "admin";
            //TODO: figure a way to automate createdBy

            LocalDateTime updateDate = LocalDateTime.now();
            String updatedBy = null;

            Customers customer = new Customers(customerName, customerAddress, customerPostal, customerPhone, createDate, createdBy, updateDate, updatedBy);
            customer.setDivisionId(city.getDivisionId());
            customer.setCountryId(country.getCountryID());

            CustomerImp.addCustomer(customer);


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
        countryComboBox.setItems(CountriesImp.getAllCountries());
        divisionsComboBox.setItems(DivisionsImp.getAllDivisions());
    }

}
