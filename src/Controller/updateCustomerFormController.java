package Controller;

import Dao.CountriesImp;
import Dao.CustomerImp;
import Dao.DivisionsImp;
import Model.Countries;
import Model.Customers;
import Model.FirstLevelDivision;
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
import java.time.LocalDateTime;
import java.util.Optional;

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
    public void updateSaveButtonPushed(ActionEvent event) throws IOException, SQLException {
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
            int countryId = country.getCountryID();

            Customers customer = new Customers(updateName, updateAddress, updateZipCode, updatePhone, LocalDateTime.now(), "admin", updateDate, updatedBy);
            customer.setDivisionId(divisionId);
            customer.setCustomerId(customerId);
            customer.setCountryId(countryId);

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
    public void updateCancelButtonPushed(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {

            Parent parent = FXMLLoader.load(getClass().getResource("/View/mainWindow.fxml"));
            Scene scene = new Scene(parent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();
        }

    }

    public void newCountrySelected(ActionEvent countrySelected) throws IOException, SQLException {
        if(!(countryComboBox.getSelectionModel().isEmpty())) {
            Countries country = countryComboBox.getSelectionModel().getSelectedItem();
            int countryId = country.getCountryID();
            ObservableList<FirstLevelDivision> getAllCities = DivisionsImp.getAllDivisions();
            ObservableList<FirstLevelDivision> relatedCities = FXCollections.observableArrayList();
            for(int i = 0; i < getAllCities.size(); i++) {
                if(getAllCities.get(i).getDivisionCountryId() == countryId) {
                    relatedCities.add(getAllCities.get(i));
                }
            }
            divisionsComboBox.setDisable(false);
            divisionsComboBox.setItems(relatedCities);
        }
    }

    public void initUpdateCustomer(Customers customer) throws SQLException {
        countryComboBox.setItems(CountriesImp.getAllCountries());
        ObservableList<FirstLevelDivision> allDivisions = DivisionsImp.getAllDivisions();
        divisionsComboBox.setItems(DivisionsImp.getAllDivisions());

        updatedCustomer = customer;
        customerIdTextField.setText(String.valueOf(customer.getCustomerId()));
        customerNameTextField.setText(customer.getCustomerName());
        customerAddressTextField.setText(customer.getCustomerAddress());
        customerPostalCodeTextField.setText(customer.getCustomerPostal());
        customerPhoneTextField.setText(customer.getCustomerPhone());
        FirstLevelDivision city = DivisionsImp.getDivision(customer.getDivisionId());
        countryComboBox.setValue(CountriesImp.getCountry(city.getDivisionCountryId()));

        ObservableList<FirstLevelDivision> relatedCities = FXCollections.observableArrayList();
        for(int i = 0; i < allDivisions.size(); i++) {
            if(allDivisions.get(i).getDivisionCountryId() == city.getDivisionCountryId()) {
                relatedCities.add(allDivisions.get(i));
            }
        }
        divisionsComboBox.setItems(relatedCities);
        divisionsComboBox.setValue(DivisionsImp.getDivision(customer.getDivisionId()));

    }

}
