package Controller;

import Dao.AppointmentImp;
import Dao.CustomerImp;
import Model.Appointments;
import Model.Customers;
import Model.Users;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class mainWindowController {

    @FXML
    private Button addAppointmentButton;

    @FXML
    private Button editAppointmentButton;

    @FXML
    private Button deleteAppointmentButton;

    @FXML
    private Button addCustomerButton;

    @FXML
    private Button editCustomerButton;

    @FXML
    private Button deleteCustomerButton;

    @FXML
    private TableView<Customers> customersTableView;

    @FXML
    private TableColumn<Customers, Integer> custIdColumn;

    @FXML
    private TableColumn<Customers, String> custNameColumn;

    @FXML
    private TableColumn<Customers, String> custAddressColumn;

    @FXML
    private TableColumn<Customers, String> custPostalColumn;

    @FXML
    private TableColumn<Customers, String> custPhoneColumn;

    @FXML
    private TableColumn<Customers, Integer> custDivisionColumn;

    @FXML
    private TableView<Appointments> appointmentsMonthTableView;

    @FXML
    private TableColumn<Appointments, Integer> apptIdMonthColumn;

    @FXML
    private TableColumn<Appointments, String> apptTitleMonthColumn;

    @FXML
    private TableColumn<Appointments, String> apptMonthDescriptionColumn;

    @FXML
    private TableColumn<Appointments, String> apptMonthLocationColumn;

    @FXML
    private TableColumn<Appointments, Integer> apptMonthContactColumn;

    @FXML
    private TableColumn<Appointments, String> apptMonthTypeColumn;

    @FXML
    private TableColumn<Appointments, LocalDateTime> apptMonthStartColumn;

    @FXML
    private TableColumn<Appointments, LocalDateTime> apptMonthEndColumn;

    @FXML
    private TableColumn<Appointments, Integer> apptMonthCustIdColumn;

    @FXML
    private TableView<Appointments> appointmentsWeekTableView;

    @FXML
    private TableColumn<Appointments, Integer> apptWeekIdColumn;

    @FXML
    private TableColumn<Appointments, String> apptWeekTitleColumn;

    @FXML
    private TableColumn<Appointments, String> apptWeekDescColumn;

    @FXML
    private TableColumn<Appointments, String> apptWeekLocationColumn;

    @FXML
    private TableColumn<Appointments, Integer> apptWeekContactColumn;

    @FXML
    private TableColumn<Appointments, String> apptWeekTypeColumn;

    @FXML
    private TableColumn<Appointments, LocalDateTime> apptWeekStartColumn;

    @FXML
    private TableColumn<Appointments, LocalDateTime> apptWeekEndColumn;

    @FXML
    private TableColumn<Appointments, Integer> apptWeekCustIdColumn;

    @FXML
    private Tab allAppointmentsTab;

    @FXML
    private TableView<Appointments> appointmentsAllTableView;

    @FXML
    private TableColumn<Appointments, Integer> apptIdAllColumn;

    @FXML
    private TableColumn<Appointments, String> apptTitleAllColumn;

    @FXML
    private TableColumn<Appointments, String> apptDescriptionAllColumn;

    @FXML
    private TableColumn<Appointments, String> apptLocationAllColumn;

    @FXML
    private TableColumn<Appointments, Integer> apptContactAllColumn;

    @FXML
    private TableColumn<Appointments, String> apptTypeAllColumn;

    @FXML
    private TableColumn<Appointments, LocalDateTime> apptStartAllColumn;

    @FXML
    private TableColumn<Appointments, LocalDateTime> apptEndAllColumn;

    @FXML
    private TableColumn<Appointments, Integer> apptCustIdAllColumn;

    @FXML
    private Tab monthTab;

    @FXML
    private Tab weekTab;

    @FXML
    private Label mainAppointmentLabel;

    @FXML
    private Label upcomingAppointmentLabel;

    private Users loggedInUser;

    private int appointmentID;

    private LocalDateTime appointmentTime;


    /**
     * Method to change scenes to the new appointment page. Passes logged in user's info to next scene.
     * @param event Parameter used to call the scene change from main window to new appointment scene.
     * @throws IOException IOException is thrown for this event.
     * @throws SQLException SQLException is thrown because the initializer calls from database.
     */
    @FXML
    public void newAppointmentButtonPushed(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/addAppointmentForm.fxml"));
        Parent addAppointmentParent = loader.load();
        Scene addAppointmentScene = new Scene(addAppointmentParent);

        addAppointmentFormController controller = loader.getController();
        controller.initialize(loggedInUser);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(addAppointmentScene);
        window.centerOnScreen();
        window.show();
    }

    /**
     * This method is called to change scenes from the main window to the new customer scene.
     * @param event Event is called to help with changing scenes after loading initializer.
     * @throws IOException This method throws an IOException.
     * @throws SQLException This method throws an SQLException.
     */
    @FXML
    public void newCustomerButtonPushed(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/addCustomerForm.fxml"));
        Parent addCustomerParent = loader.load();
        Scene addCustomerScene = new Scene(addCustomerParent);

        addCustomerFormController controller = loader.getController();
        controller.initialize(loggedInUser);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(addCustomerScene);
        window.centerOnScreen();
        window.show();
    }

    /**
     * This method is used to sign the user out of the program.
     * @param event Event is called to change scenes with initializer.
     * @throws IOException This method throws an IOException
     */
    @FXML
    public void signOutButtonPushed(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to sign out?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/loginForm.fxml"));
            Scene scene = new Scene(parent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.centerOnScreen();
            window.show();
        }
    }

    /**
     * This method is used to change scenes to update customer scene.
     * @param event This parameter is used to help change scenes with initializer.
     */
    @FXML
    public void updateCustomerButtonPushed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/updateCustomerForm.fxml"));
            Parent updateCustomerParent = loader.load();
            Scene updateCustomerScene = new Scene(updateCustomerParent);

            updateCustomerFormController controller = loader.getController();
            controller.initUpdateCustomer(customersTableView.getSelectionModel().getSelectedItem(), loggedInUser);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(updateCustomerScene);
            window.centerOnScreen();
            window.show();
        }   catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Please select a customer you would like to update.");
            alert.showAndWait();
        }

    }

    /**
     * This method is used to change scenes from main window to the update appointment window. An alert is shown if nothing is selected.
     * @param updateAppointment Parameter used to help change scenes and call initializer.
     */
    @FXML
    public void updateAppointmentButtonPushed(ActionEvent updateAppointment) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/updateAppointmentForm.fxml"));
            Parent updateAppointmentParent = loader.load();
            Scene updateAppointmentScene = new Scene(updateAppointmentParent);

            updateAppointmentFormController controller = loader.getController();

            if (monthTab.isSelected()) {
                controller.initUpdateAppointment(appointmentsMonthTableView.getSelectionModel().getSelectedItem(), loggedInUser);
            }
            if (weekTab.isSelected()) {
                controller.initUpdateAppointment(appointmentsWeekTableView.getSelectionModel().getSelectedItem(), loggedInUser);
            }
            if (allAppointmentsTab.isSelected()) {
                controller.initUpdateAppointment(appointmentsAllTableView.getSelectionModel().getSelectedItem(), loggedInUser);
            }

            Stage window = (Stage) ((Node) updateAppointment.getSource()).getScene().getWindow();
            window.setScene(updateAppointmentScene);
            window.centerOnScreen();
            window.show();
        }   catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Please select an appointment you would like to update.");
            alert.showAndWait();
        }
    }

    /**
     * This method is used to delete a customer from the database. An alert is shown if nothing is selected. Confirmation is shown before and after. If customer has appointments, alert is shown.
     * @throws SQLException This method throws an SQLException because it uses the database.
     */
    @FXML
    public void deleteCustomerButtonPushed() throws SQLException {
        if (customersTableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delete Warning");
            alert.setContentText("Please select a customer you would like to delete.");
            alert.showAndWait();
            return;
        }
        if (customersTableView.getSelectionModel().getSelectedItem() != null) {
            ArrayList<Appointments> customerAppointments = AppointmentImp.getCustomerAppointments(customersTableView.getSelectionModel().getSelectedItem().getCustomerId());
            if (customerAppointments.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this customer?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    CustomerImp.deleteCustomer(customersTableView.getSelectionModel().getSelectedItem().getCustomerId());
                    customersTableView.setItems(CustomerImp.getAllCustomers());
                    Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
                    confirmationAlert.setTitle("Deletion Successful");
                    confirmationAlert.setContentText("Customer successfully deleted.");
                    confirmationAlert.show();
                }
            }   else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Customer Deletion Error");
                alert.setContentText("All appointments scheduled with this customer must be deleted before the customer can be removed.");
                alert.showAndWait();
            }
        }
    }

    /**
     * This method is used to delete an appointment from the database. Confirmations and alerts are shown.
     * @throws SQLException This method throws an SQLException because it uses the database.
     */
    @FXML
    public void deleteAppointmentButtonPushed() throws SQLException {
        if(appointmentsAllTableView.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this appointment?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                int id = appointmentsAllTableView.getSelectionModel().getSelectedItem().getAppointmentId();
                String type = appointmentsAllTableView.getSelectionModel().getSelectedItem().getAppointmentType();
                AppointmentImp.deleteAppointment(id);
                appointmentsAllTableView.setItems(AppointmentImp.allUserAppointments(loggedInUser));
                appointmentsMonthTableView.setItems(AppointmentImp.getAllUserAppointmentsMonth(loggedInUser));
                appointmentsWeekTableView.setItems(AppointmentImp.getAllUserAppointmentsWeek(loggedInUser));
                Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
                confirmationAlert.setTitle("Deletion Successful");
                confirmationAlert.setContentText(type + " appointment " + String.valueOf(id) + " was successfully deleted");
                confirmationAlert.show();
            }
        }   else if(appointmentsWeekTableView.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this appointment?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                int id = appointmentsWeekTableView.getSelectionModel().getSelectedItem().getAppointmentId();
                String type = appointmentsWeekTableView.getSelectionModel().getSelectedItem().getAppointmentType();
                AppointmentImp.deleteAppointment(id);
                appointmentsWeekTableView.setItems(AppointmentImp.getAllUserAppointmentsWeek(loggedInUser));
                appointmentsAllTableView.setItems(AppointmentImp.allUserAppointments(loggedInUser));
                appointmentsMonthTableView.setItems(AppointmentImp.getAllUserAppointmentsMonth(loggedInUser));
                Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
                confirmationAlert.setTitle("Deletion Successful");
                confirmationAlert.setContentText(type + " appointment " + String.valueOf(id) + " was successfully deleted");
                confirmationAlert.show();
            }
        }   else if(appointmentsMonthTableView.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this appointment?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                int id = appointmentsMonthTableView.getSelectionModel().getSelectedItem().getAppointmentId();
                String type = appointmentsMonthTableView.getSelectionModel().getSelectedItem().getAppointmentType();
                AppointmentImp.deleteAppointment(id);
                appointmentsMonthTableView.setItems(AppointmentImp.getAllUserAppointmentsMonth(loggedInUser));
                appointmentsWeekTableView.setItems(AppointmentImp.getAllUserAppointmentsWeek(loggedInUser));
                appointmentsAllTableView.setItems(AppointmentImp.allUserAppointments(loggedInUser));
                Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
                confirmationAlert.setTitle("Deletion Successful");
                confirmationAlert.setContentText(type + " appointment " + String.valueOf(id) + " was successfully deleted");
                confirmationAlert.show();
            }
        }   else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delete Warning");
            alert.setContentText("Please select an appointment you would like to delete.");
            alert.showAndWait();
        }
    }

    /**
     * This method is used to change scenes from main window to report window.
     * @param event The event is called to help change scenes and call the initializer.
     * @throws IOException This method throws an IOException.
     * @throws SQLException An SQLException is called because the initializer calls on the database.
     */
    @FXML
    public void reportButtonPushed(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/reportsWindow.fxml"));
        Parent reportWindowParent = loader.load();
        Scene reportWindowScene = new Scene(reportWindowParent);

        reportsWindowController controller = loader.getController();
        controller.initialize(loggedInUser);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(reportWindowScene);
        window.centerOnScreen();
        window.show();
    }

    /**
     * This method is the initializer. Other scenes will call on this method when coming back to the main window. A user is passed as parameter.
     * @param user Passed as parameter to track the user who has data to be shown and is logged in.
     * @throws SQLException SQLException is thrown because this method calls on the database.
     */
    public void initMainWindow(Users user) throws SQLException {
        loggedInUser = user;

        ObservableList<Appointments> allAppointments = AppointmentImp.allUserAppointments(user);
        customersTableView.setItems(CustomerImp.getAllCustomers());
        custIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        custAddressColumn.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        custPostalColumn.setCellValueFactory(new PropertyValueFactory<>("customerPostal"));
        custPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        custDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("divisionId"));

        appointmentsMonthTableView.setItems(AppointmentImp.getAllUserAppointmentsMonth(user));
        apptIdMonthColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleMonthColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        apptMonthDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        apptMonthLocationColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        apptMonthContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        apptMonthTypeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        apptMonthStartColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        apptMonthEndColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        apptMonthCustIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        appointmentsWeekTableView.setItems(AppointmentImp.getAllUserAppointmentsWeek(user));
        apptWeekIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptWeekTitleColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        apptWeekDescColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        apptWeekLocationColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        apptWeekContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        apptWeekTypeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        apptWeekStartColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        apptWeekEndColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        apptWeekCustIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        appointmentsAllTableView.setItems(allAppointments);
        apptIdAllColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleAllColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        apptDescriptionAllColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        apptLocationAllColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        apptContactAllColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        apptTypeAllColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        apptStartAllColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        apptEndAllColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        apptCustIdAllColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        addAppointmentButton.setTooltip(new Tooltip("Add Appointment"));
        editAppointmentButton.setTooltip(new Tooltip("Update Appointment"));
        deleteAppointmentButton.setTooltip(new Tooltip("Delete Appointment"));
        addCustomerButton.setTooltip(new Tooltip("Add Customer"));
        editCustomerButton.setTooltip(new Tooltip("Update Customer"));
        deleteCustomerButton.setTooltip(new Tooltip("Delete Customer"));

        mainAppointmentLabel.setText(user.getUserName() + "'s Appointments");

        int count = 0;
        for (int i = 0; i < allAppointments.size(); i++) {
            if (allAppointments.get(i).getAppointmentStart().isAfter(LocalDateTime.now()) && allAppointments.get(i).getAppointmentStart().isBefore(LocalDateTime.now().plusMinutes(15))) {
                appointmentID = allAppointments.get(i).getAppointmentId();
                appointmentTime = allAppointments.get(i).getAppointmentStart();
                count += 1;
            }
        }
        if (count > 0) {
            upcomingAppointmentLabel.setText("Upcoming Meeting! Appointment ID: " + appointmentID + " starts at " + appointmentTime);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Upcoming Meeting");
            alert.setContentText("A meeting is scheduled to start within the next 15 minutes or less. Appointment ID: " + String.valueOf(appointmentID) + " starts at " + String.valueOf(appointmentTime));
            alert.show();
        }   else {
            upcomingAppointmentLabel.setText("No Upcoming Meetings in the next 15 minutes");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment Updates");
            alert.setContentText("You have no meetings starting in the next 15 minutes.");
            alert.show();
        }
    }
}
