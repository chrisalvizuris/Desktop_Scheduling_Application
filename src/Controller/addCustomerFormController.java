package Controller;

import Dao.CountriesImp;
import Dao.CustomerImp;
import Dao.DivisionsImp;
import Model.Countries;
import Model.Customers;
import Model.FirstLevelDivision;
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
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class addCustomerFormController {

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
    private Label customerDescriptionLabel;

    @FXML
    private Label addCustomerHeadlineLabel;

    private String userName;

    private Users loggedUser;

    @FXML
    private void cancelCustomerButtonPushed(ActionEvent event) throws IOException, SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/mainWindow.fxml"));
            Parent mainWindowParent = loader.load();
            Scene mainWindowScene = new Scene(mainWindowParent);

            mainWindowController controller = loader.getController();
            controller.initMainWindow(loggedUser);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(mainWindowScene);
            window.centerOnScreen();
            window.show();
        }
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
            String createdBy = loggedUser.getUserName();

            LocalDateTime updateDate = LocalDateTime.now();
            String updatedBy = null;

            Customers customer = new Customers(customerName, customerAddress, customerPostal, customerPhone, createDate, createdBy, updateDate, updatedBy);
            customer.setDivisionId(city.getDivisionId());
            customer.setCountryId(country.getCountryID());

            CustomerImp.addCustomer(customer);


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/mainWindow.fxml"));
            Parent mainWindowParent = loader.load();
            Scene mainWindowScene = new Scene(mainWindowParent);

            mainWindowController controller = loader.getController();
            controller.initMainWindow(loggedUser);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(mainWindowScene);
            window.centerOnScreen();
            window.show();
        }   catch (Exception e) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("Utilities/Nat", Locale.getDefault());
            if(Locale.getDefault().getLanguage().equals("fr")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(resourceBundle.getString("Error"));
                alert.setContentText(resourceBundle.getString("invalidDataWarning"));
                alert.showAndWait();
            }

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Error: The data you entered is invalid. Please review and try again.");
            alert.showAndWait();
        }
    }

    public void countryBoxSelected(ActionEvent countrySelected) throws IOException, SQLException {
        if (!(countryComboBox.getSelectionModel().isEmpty())) {
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

    public void initialize(Users user) throws SQLException {
        loggedUser = user;
        countryComboBox.setItems(CountriesImp.getAllCountries());
        divisionsComboBox.setItems(DivisionsImp.getAllDivisions());
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Utilities/Nat", Locale.getDefault());
        if(Locale.getDefault().equals("fr")) {
            customerDescriptionLabel.setText(resourceBundle.getString("addCustomerLabel"));
            addCustomerHeadlineLabel.setText(resourceBundle.getString("addCustomer"));
            customerIdTextField.setPromptText(resourceBundle.getString("custIdGen"));
            customerNameTextField.setPromptText(resourceBundle.getString("Name"));
            customerAddressTextField.setPromptText(resourceBundle.getString("Address"));
            customerPhoneTextField.setPromptText(resourceBundle.getString("Phone"));
            customerPostalCodeTextField.setPromptText(resourceBundle.getString("Postal"));
            countryComboBox.setPromptText(resourceBundle.getString("Country"));
            divisionsComboBox.setPromptText(resourceBundle.getString("City"));
            customerSaveButton.setText(resourceBundle.getString("Save"));
            customerCancelButton.setText(resourceBundle.getString("Cancel"));
        }
    }

}
