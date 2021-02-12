package Controller;

import Dao.AppointmentImp;
import Dao.CustomerImp;
import Model.Appointments;
import Model.Customers;
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
import java.time.LocalTime;

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
    private Button signOutButton;

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
    private Tab monthTab;

    @FXML
    private Tab weekTab;

    @FXML
    private void newAppointmentButtonPushed(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/addAppointmentForm.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void newCustomerButtonPushed(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/addCustomerForm.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void signOutButtonPushed(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/loginForm.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void updateCustomerButtonPushed(ActionEvent event) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/updateCustomerForm.fxml"));
        Parent updateCustomerParent = loader.load();
        Scene updateCustomerScene = new Scene(updateCustomerParent);

        updateCustomerFormController controller = loader.getController();
        controller.initUpdateCustomer(customersTableView.getSelectionModel().getSelectedItem());

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(updateCustomerScene);
        window.show();

    }

    @FXML
    private void updateAppointmentButtonPushed(ActionEvent updateAppointment) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/updateAppointmentForm.fxml"));
        Parent updateAppointmentParent = loader.load();
        Scene updateAppointmentScene = new Scene(updateAppointmentParent);

        updateAppointmentFormController controller = loader.getController();

        if(monthTab.isSelected()) {
            controller.initUpdateAppointment(appointmentsMonthTableView.getSelectionModel().getSelectedItem());
        }
        if(weekTab.isSelected()) {
            controller.initUpdateAppointment(appointmentsWeekTableView.getSelectionModel().getSelectedItem());
        }

        Stage window = (Stage) ((Node) updateAppointment.getSource()).getScene().getWindow();
        window.setScene(updateAppointmentScene);
        window.show();
    }

    public void initialize() throws SQLException {
        customersTableView.setItems(CustomerImp.getAllCustomers());
        custIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        custAddressColumn.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        custPostalColumn.setCellValueFactory(new PropertyValueFactory<>("customerPostal"));
        custPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        custDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("divisionId"));

        appointmentsWeekTableView.setItems(AppointmentImp.getAllAppointmentsThisWeek());
        apptWeekIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptWeekTitleColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        apptWeekDescColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        apptWeekLocationColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        apptWeekContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        apptWeekTypeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        apptWeekStartColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        apptWeekEndColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        apptWeekCustIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        appointmentsMonthTableView.setItems(AppointmentImp.getAllAppointmentsThisMonth());
        apptIdMonthColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleMonthColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        apptMonthDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        apptMonthLocationColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        apptMonthContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        apptMonthTypeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        apptMonthStartColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        apptMonthEndColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        apptMonthCustIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        ObservableList<Appointments> allAppointments = AppointmentImp.getAllAppointments();
        int apptID = 0;
        LocalDateTime apptTime = null;
        int count = 0;
        for (int i = 0; i < allAppointments.size(); i++) {
            if (allAppointments.get(i).getAppointmentStart().isAfter(LocalDateTime.now()) && allAppointments.get(i).getAppointmentStart().isBefore(LocalDateTime.now().plusMinutes(15))) {
                apptID = allAppointments.get(i).getAppointmentId();
                apptTime = allAppointments.get(i).getAppointmentStart();
                count += 1;
            }
        }
        if (count > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning: Upcoming Meeting");
            alert.setContentText("A meeting is scheduled to start within the next 15 minutes or less. Appointment ID: " + String.valueOf(apptID) + " starts at " + String.valueOf(apptTime));
            alert.show();
        }   else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment Updates");
            alert.setContentText("You have no meetings starting in the next 15 minutes.");
            alert.show();
        }
    }
}
