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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class updateCustomerFormController {

    private Customers updatedCustomer;

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
    private Button updateCustomerSaveButton;

    @FXML
    private Button updateCustomerCancelButton;

    @FXML
    private void updateSaveButtonPushed(ActionEvent event) throws IOException, SQLException {
        try {
            int customerId = Integer.parseInt(customerIdTextField.getText());
            String updateName = customerNameTextField.getText();
            String updateAddress = customerAddressTextField.getText();
            String updateZipCode = customerPostalCodeTextField.getText();
            String updatePhone = customerPhoneTextField.getText();
            Countries country = countryComboBox.getSelectionModel().getSelectedItem();
            FirstLevelDivision city = divisionsComboBox.getSelectionModel().getSelectedItem();

            LocalDateTime updateDate = LocalDateTime.now();
            String updatedBy = "admin";
            int divisionId = city.getDivisionId();

            Customers customer = new Customers(updateName, updateAddress, updateZipCode, updatePhone, LocalDateTime.now(), "admin", updateDate, updatedBy);
            customer.setDivisionId(divisionId);
            customer.setCustomerId(customerId);

            CustomerImp.updateCustomer(customer);

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

    public void initUpdateCustomer(Customers customer) throws SQLException {
        countryComboBox.setItems(CountriesImp.getAllCountries());
        divisionsComboBox.setItems(DivisionsImp.getAllDivisions());

        updatedCustomer = customer;
        customerIdTextField.setText(String.valueOf(customer.getCustomerId()));
        customerNameTextField.setText(customer.getCustomerName());
        customerAddressTextField.setText(customer.getCustomerAddress());
        customerPostalCodeTextField.setText(customer.getCustomerPostal());
        customerPhoneTextField.setText(customer.getCustomerPhone());
        countryComboBox.setValue(CountriesImp.getCountry(customer.getCountryId()));
        divisionsComboBox.setValue(DivisionsImp.getDivision(customer.getDivisionId()));

    }

}
