package Controller;

import Dao.AppointmentImp;
import Dao.ContactsImp;
import Model.Appointments;
import Model.Contacts;
import Model.Users;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;

public class reportsWindowController {


    @FXML
    private Tab typeTab;

    @FXML
    private TableView<String> typeTableView;

    @FXML
    private TableColumn<String, String> resultsColumn;

    @FXML
    private Tab contactTab;

    @FXML
    private TableView<Appointments> contactReportTableView;

    @FXML
    private TableColumn<Appointments, String> contactNameColumn;

    @FXML
    private TableColumn<Appointments, Integer> apptIdColumn;

    @FXML
    private TableColumn<Appointments, String> apptTitleColumn;

    @FXML
    private TableColumn<Appointments, String> apptDescColumn;

    @FXML
    private TableColumn<Appointments, String> contactApptTypeColumn;

    @FXML
    private TableColumn<Appointments, LocalDateTime> apptStartColumn;

    @FXML
    private TableColumn<Appointments, LocalDateTime> apptEndColumn;

    @FXML
    private TableColumn<Appointments, Integer> custIdColumn;

    @FXML
    private Tab locationTab;

    @FXML
    private Button returnHomeButton;

    private Users loggedUser;

    @FXML
    private void returnButtonPressed(ActionEvent event) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/mainWindow.fxml"));
        Parent mainWindowParent = loader.load();
        Scene mainWindowScene = new Scene(mainWindowParent);

        mainWindowController controller = loader.getController();
        controller.initMainWindow(loggedUser);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(mainWindowScene);
        window.show();
    }

    public void initialize(Users user) throws SQLException {
        loggedUser = user;

        //create month objects
        Month jan = Month.JANUARY;
        Month feb = Month.FEBRUARY;
        Month march = Month.MARCH;
        Month april = Month.APRIL;
        Month may = Month.MAY;
        Month june = Month.JUNE;
        Month july = Month.JULY;
        Month aug = Month.AUGUST;
        Month sept = Month.SEPTEMBER;
        Month Oct = Month.OCTOBER;
        Month nov = Month.NOVEMBER;
        Month dec = Month.DECEMBER;

        int janGMCount = 0;
        int febGMCount = 0;
        int marGMCount = 0;
        int aprGMCount = 0;
        int mayGMCount = 0;
        int juneGMCount = 0;
        int julyGMCount = 0;
        int augGMCount = 0;
        int septGMCount = 0;
        int octGMCount = 0;
        int novGMCount = 0;
        int decGMCount = 0;

        int janConsultCount = 0;
        int febConsultCount = 0;
        int marConsultCount = 0;
        int aprConsultCount = 0;
        int mayConsultCount = 0;
        int juneConsultCount = 0;
        int julyConsultCount = 0;
        int augConsultCount = 0;
        int septConsultCount = 0;
        int octConsultCount = 0;
        int novConsultCount = 0;
        int decConsultCount = 0;

        int janQBRCount = 0;
        int febQBRCount = 0;
        int marQBRCount = 0;
        int aprQBRCount = 0;
        int mayQBRCount = 0;
        int juneQBRCount = 0;
        int julyQBRCount = 0;
        int augQBRCount = 0;
        int septQBRCount = 0;
        int octQBRCount = 0;
        int novQBRCount = 0;
        int decQBRCount = 0;

        int janInterCount = 0;
        int febInterCount = 0;
        int marInterCount = 0;
        int aprInterCount = 0;
        int mayInterCount = 0;
        int juneInterCount = 0;
        int julyInterCount = 0;
        int augInterCount = 0;
        int septInterCount = 0;
        int octInterCount = 0;
        int novInterCount = 0;
        int decInterCount = 0;

        String type1 = "General Meeting";
        String type2 = "Consultation";
        String type3 = "Quarterly Business Review";
        String type4 = "Interview";

        ObservableList<Appointments> allAppointments = AppointmentImp.getAllAppointments();
        for(int i = 0; i < allAppointments.size(); i++) {
            if(allAppointments.get(i).getAppointmentStart().getMonth().equals(jan)) {
                if(allAppointments.get(i).getAppointmentType().equals(type1)) {
                    janGMCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type2)) {
                    janConsultCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type3)) {
                    janQBRCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type4)) {
                    janInterCount += 1;
                }
            }
            if(allAppointments.get(i).getAppointmentStart().getMonth().equals(feb)) {
                if(allAppointments.get(i).getAppointmentType().equals(type1)) {
                    febGMCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type2)) {
                    febConsultCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type3)) {
                    febQBRCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type4)) {
                    febInterCount += 1;
                }
            }
            if(allAppointments.get(i).getAppointmentStart().getMonth().equals(march)) {
                if(allAppointments.get(i).getAppointmentType().equals(type1)) {
                    marGMCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type2)) {
                    marConsultCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type3)) {
                    marQBRCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type4)) {
                    marInterCount += 1;
                }
            }
            if(allAppointments.get(i).getAppointmentStart().getMonth().equals(april)) {
                if(allAppointments.get(i).getAppointmentType().equals(type1)) {
                    aprGMCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type2)) {
                    aprConsultCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type3)) {
                    aprQBRCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type4)) {
                    aprInterCount += 1;
                }
            }
            if(allAppointments.get(i).getAppointmentStart().getMonth().equals(may)) {
                if(allAppointments.get(i).getAppointmentType().equals(type1)) {
                    mayGMCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type2)) {
                    mayConsultCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type3)) {
                    mayQBRCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type4)) {
                    mayInterCount += 1;
                }
            }
            if(allAppointments.get(i).getAppointmentStart().getMonth().equals(june)) {
                if(allAppointments.get(i).getAppointmentType().equals(type1)) {
                    juneGMCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type2)) {
                    juneConsultCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type3)) {
                    juneQBRCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type4)) {
                    juneInterCount += 1;
                }
            }
            if(allAppointments.get(i).getAppointmentStart().getMonth().equals(july)) {
                if(allAppointments.get(i).getAppointmentType().equals(type1)) {
                    julyGMCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type2)) {
                    julyConsultCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type3)) {
                    julyQBRCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type4)) {
                    julyInterCount += 1;
                }
            }
            if(allAppointments.get(i).getAppointmentStart().getMonth().equals(aug)) {
                if(allAppointments.get(i).getAppointmentType().equals(type1)) {
                    augGMCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type2)) {
                    augConsultCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type3)) {
                    augQBRCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type4)) {
                    augInterCount += 1;
                }
            }
            if(allAppointments.get(i).getAppointmentStart().getMonth().equals(sept)) {
                if(allAppointments.get(i).getAppointmentType().equals(type1)) {
                    septGMCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type2)) {
                    septConsultCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type3)) {
                    septQBRCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type4)) {
                    septInterCount += 1;
                }
            }
            if(allAppointments.get(i).getAppointmentStart().getMonth().equals(Oct)) {
                if(allAppointments.get(i).getAppointmentType().equals(type1)) {
                    octGMCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type2)) {
                    octConsultCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type3)) {
                    octQBRCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type4)) {
                    octInterCount += 1;
                }
            }
            if(allAppointments.get(i).getAppointmentStart().getMonth().equals(nov)) {
                if(allAppointments.get(i).getAppointmentType().equals(type1)) {
                    novGMCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type2)) {
                    novConsultCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type3)) {
                    novQBRCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type4)) {
                    novInterCount += 1;
                }
            }
            if(allAppointments.get(i).getAppointmentStart().getMonth().equals(dec)) {
                if(allAppointments.get(i).getAppointmentType().equals(type1)) {
                    decGMCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type2)) {
                    decConsultCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type3)) {
                    decQBRCount += 1;
                }
                if(allAppointments.get(i).getAppointmentType().equals(type4)) {
                    decInterCount += 1;
                }
            }
        }

        String janReportType1 = "January: " + String.valueOf(janGMCount) + " " + type1 + " appointments.";
        String janReportType2 = "January: " + String.valueOf(janConsultCount) + " " + type2 + " appointments.";
        String janReportType3 = "January: " + String.valueOf(janQBRCount) + " " + type3 + " appointments.";
        String janReportType4 = "January: " + String.valueOf(janInterCount) + " " + type4 + " appointments.";
        String febReportType1 = "February: " + String.valueOf(febGMCount) + " " + type1 + " appointments.";
        String febReportType2 = "February: " + String.valueOf(febConsultCount) + " " + type2 + " appointments.";
        String febReportType3 = "February: " + String.valueOf(febQBRCount) + " " + type3 + " appointments.";
        String febReportType4 = "February: " + String.valueOf(febInterCount) + " " + type4 + " appointments.";
        String marReportType1 = "March: " + String.valueOf(marGMCount) + " " + type1 + " appointments.";
        String marReportType2 = "March: " + String.valueOf(marConsultCount) + " " + type2 + " appointments.";
        String marReportType3 = "March: " + String.valueOf(marQBRCount) + " " + type3 + " appointments.";
        String marReportType4 = "March: " + String.valueOf(marInterCount) + " " + type4 + " appointments.";
        String aprReportType1 = "April: " + String.valueOf(aprGMCount) + " " + type1 + " appointments.";
        String aprReportType2 = "April: " + String.valueOf(aprConsultCount) + " " + type2 + " appointments.";
        String aprReportType3 = "April: " + String.valueOf(aprQBRCount) + " " + type3 + " appointments.";
        String aprReportType4 = "April: " + String.valueOf(aprInterCount) + " " + type4 + " appointments.";
        String mayReportType1 = "May: " + String.valueOf(mayGMCount) + " " + type1 + " appointments.";
        String mayReportType2 = "May: " + String.valueOf(mayConsultCount) + " " + type2 + " appointments.";
        String mayReportType3 = "May: " + String.valueOf(mayQBRCount) + " " + type3 + " appointments.";
        String mayReportType4 = "May: " + String.valueOf(mayInterCount) + " " + type4 + " appointments.";
        String juneReportType1 = "June: " + String.valueOf(juneGMCount) + " " + type1 + " appointments.";
        String juneReportType2 = "June: " + String.valueOf(juneConsultCount) + " " + type2 + " appointments.";
        String juneReportType3 = "June: " + String.valueOf(juneQBRCount) + " " + type3 + " appointments.";
        String juneReportType4 = "June: " + String.valueOf(juneInterCount) + " " + type4 + " appointments.";
        String julyReportType1 = "July: " + String.valueOf(julyGMCount) + " " + type1 + " appointments.";
        String julyReportType2 = "July: " + String.valueOf(julyConsultCount) + " " + type2 + " appointments.";
        String julyReportType3 = "July: " + String.valueOf(julyQBRCount) + " " + type3 + " appointments.";
        String julyReportType4 = "July: " + String.valueOf(julyInterCount) + " " + type4 + " appointments.";
        String augReportType1 = "August: " + String.valueOf(augGMCount) + " " + type1 + " appointments.";
        String augReportType2 = "August: " + String.valueOf(augConsultCount) + " " + type2 + " appointments.";
        String augReportType3 = "August: " + String.valueOf(augQBRCount) + " " + type3 + " appointments.";
        String augReportType4 = "August: " + String.valueOf(augInterCount) + " " + type4 + " appointments.";
        String septReportType1 = "September: " + String.valueOf(septGMCount) + " " + type1 + " appointments.";
        String septReportType2 = "September: " + String.valueOf(septConsultCount) + " " + type2 + " appointments.";
        String septReportType3 = "September: " + String.valueOf(septQBRCount) + " " + type3 + " appointments.";
        String septReportType4 = "September: " + String.valueOf(septInterCount) + " " + type4 + " appointments.";
        String octReportType1 = "October: " + String.valueOf(octGMCount) + " " + type1 + " appointments.";
        String octReportType2 = "October: " + String.valueOf(octConsultCount) + " " + type2 + " appointments.";
        String octReportType3 = "October: " + String.valueOf(octQBRCount) + " " + type3 + " appointments.";
        String octReportType4 = "October: " + String.valueOf(octInterCount) + " " + type4 + " appointments.";
        String novReportType1 = "November: " + String.valueOf(novGMCount) + " " + type1 + " appointments.";
        String novReportType2 = "November: " + String.valueOf(novConsultCount) + " " + type2 + " appointments.";
        String novReportType3 = "November: " + String.valueOf(novQBRCount) + " " + type3 + " appointments.";
        String novReportType4 = "November: " + String.valueOf(novInterCount) + " " + type4 + " appointments.";
        String decReportType1 = "December: " + String.valueOf(decGMCount) + " " + type1 + " appointments.";
        String decReportType2 = "December: " + String.valueOf(decConsultCount) + " " + type2 + " appointments.";
        String decReportType3 = "December: " + String.valueOf(decQBRCount) + " " + type3 + " appointments.";
        String decReportType4 = "December: " + String.valueOf(decInterCount) + " " + type4 + " appointments.";

        ObservableList<String> appointmentTypeList = FXCollections.observableArrayList();
        appointmentTypeList.add(janReportType1);
        appointmentTypeList.add(janReportType2);
        appointmentTypeList.add(janReportType3);
        appointmentTypeList.add(janReportType4);
        appointmentTypeList.add(febReportType1);
        appointmentTypeList.add(febReportType2);
        appointmentTypeList.add(febReportType3);
        appointmentTypeList.add(febReportType4);
        appointmentTypeList.add(marReportType1);
        appointmentTypeList.add(marReportType2);
        appointmentTypeList.add(marReportType3);
        appointmentTypeList.add(marReportType4);
        appointmentTypeList.add(aprReportType1);
        appointmentTypeList.add(aprReportType2);
        appointmentTypeList.add(aprReportType3);
        appointmentTypeList.add(aprReportType4);
        appointmentTypeList.add(mayReportType1);
        appointmentTypeList.add(mayReportType2);
        appointmentTypeList.add(mayReportType3);
        appointmentTypeList.add(mayReportType4);
        appointmentTypeList.add(juneReportType1);
        appointmentTypeList.add(juneReportType2);
        appointmentTypeList.add(juneReportType3);
        appointmentTypeList.add(juneReportType4);
        appointmentTypeList.add(julyReportType1);
        appointmentTypeList.add(julyReportType2);
        appointmentTypeList.add(julyReportType3);
        appointmentTypeList.add(julyReportType4);
        appointmentTypeList.add(augReportType1);
        appointmentTypeList.add(augReportType2);
        appointmentTypeList.add(augReportType3);
        appointmentTypeList.add(augReportType4);
        appointmentTypeList.add(septReportType1);
        appointmentTypeList.add(septReportType2);
        appointmentTypeList.add(septReportType3);
        appointmentTypeList.add(septReportType4);
        appointmentTypeList.add(octReportType1);
        appointmentTypeList.add(octReportType2);
        appointmentTypeList.add(octReportType3);
        appointmentTypeList.add(octReportType4);
        appointmentTypeList.add(novReportType1);
        appointmentTypeList.add(novReportType2);
        appointmentTypeList.add(novReportType3);
        appointmentTypeList.add(novReportType4);
        appointmentTypeList.add(decReportType1);
        appointmentTypeList.add(decReportType2);
        appointmentTypeList.add(decReportType3);
        appointmentTypeList.add(decReportType4);

        typeTableView.setItems(appointmentTypeList);
        resultsColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));

        //start setting up the contact appointment report
        ObservableList<Contacts> allContacts = ContactsImp.getAllContacts();

        for(int i = 0; i < allContacts.size(); i++) {
            for(int k = 0; k < allAppointments.size(); k++) {
                if(allAppointments.get(k).getContactId() == allContacts.get(i).getContactId()) {
                    allAppointments.get(k).setContactName(allContacts.get(i).getContactName());
                }
            }
        }
        Comparator<Appointments> comparator = (appointment1, appointment2) -> appointment1.getContactName().compareTo(appointment2.getContactName());
        allAppointments.sort(comparator);
        contactReportTableView.setItems(allAppointments);
        contactNameColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        apptIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        apptDescColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        contactApptTypeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        apptStartColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        apptEndColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        custIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));

    }
}
