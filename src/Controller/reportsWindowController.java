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
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;

public class reportsWindowController {


    @FXML
    private TableView<String> typeTableView;

    @FXML
    private TableColumn<String, String> resultsColumn;

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
    private Label mondayLabel;

    @FXML
    private Label tuesdayLabel;

    @FXML
    private Label wednesdayLabel;

    @FXML
    private Label thursdayLabel;

    @FXML
    private Label fridayLabel;

    @FXML
    private Label saturdayLabel;

    @FXML
    private Label sundayLabel;

    private Users loggedUser;

    /**
     * This method takes the user back to the main window.
     * @param event Event used to help change scenes.
     * @throws SQLException SQLException is thrown because initializer calls on database.
     * @throws IOException IOException is thrown.
     */
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
        window.centerOnScreen();
        window.show();
    }

    /**
     * This initializer sets up the various reports on all 3 tabs.
     * @param user User is passed to track the user who is logged in.
     * @throws SQLException SQLException is thrown because database is called.
     */
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

        String janReportType1 = "January: " + janGMCount + " " + type1 + " appointments.";
        String janReportType2 = "January: " + janConsultCount + " " + type2 + " appointments.";
        String janReportType3 = "January: " + janQBRCount + " " + type3 + " appointments.";
        String janReportType4 = "January: " + janInterCount + " " + type4 + " appointments.";
        String febReportType1 = "February: " + febGMCount + " " + type1 + " appointments.";
        String febReportType2 = "February: " + febConsultCount + " " + type2 + " appointments.";
        String febReportType3 = "February: " + febQBRCount + " " + type3 + " appointments.";
        String febReportType4 = "February: " + febInterCount + " " + type4 + " appointments.";
        String marReportType1 = "March: " + marGMCount + " " + type1 + " appointments.";
        String marReportType2 = "March: " + marConsultCount + " " + type2 + " appointments.";
        String marReportType3 = "March: " + marQBRCount + " " + type3 + " appointments.";
        String marReportType4 = "March: " + marInterCount + " " + type4 + " appointments.";
        String aprReportType1 = "April: " + aprGMCount + " " + type1 + " appointments.";
        String aprReportType2 = "April: " + aprConsultCount + " " + type2 + " appointments.";
        String aprReportType3 = "April: " + aprQBRCount + " " + type3 + " appointments.";
        String aprReportType4 = "April: " + aprInterCount + " " + type4 + " appointments.";
        String mayReportType1 = "May: " + mayGMCount + " " + type1 + " appointments.";
        String mayReportType2 = "May: " + mayConsultCount + " " + type2 + " appointments.";
        String mayReportType3 = "May: " + mayQBRCount + " " + type3 + " appointments.";
        String mayReportType4 = "May: " + mayInterCount + " " + type4 + " appointments.";
        String juneReportType1 = "June: " + juneGMCount + " " + type1 + " appointments.";
        String juneReportType2 = "June: " + juneConsultCount + " " + type2 + " appointments.";
        String juneReportType3 = "June: " + juneQBRCount + " " + type3 + " appointments.";
        String juneReportType4 = "June: " + juneInterCount + " " + type4 + " appointments.";
        String julyReportType1 = "July: " + julyGMCount + " " + type1 + " appointments.";
        String julyReportType2 = "July: " + julyConsultCount + " " + type2 + " appointments.";
        String julyReportType3 = "July: " + julyQBRCount + " " + type3 + " appointments.";
        String julyReportType4 = "July: " + julyInterCount + " " + type4 + " appointments.";
        String augReportType1 = "August: " + augGMCount + " " + type1 + " appointments.";
        String augReportType2 = "August: " + augConsultCount + " " + type2 + " appointments.";
        String augReportType3 = "August: " + augQBRCount + " " + type3 + " appointments.";
        String augReportType4 = "August: " + augInterCount + " " + type4 + " appointments.";
        String septReportType1 = "September: " + septGMCount + " " + type1 + " appointments.";
        String septReportType2 = "September: " + septConsultCount + " " + type2 + " appointments.";
        String septReportType3 = "September: " + septQBRCount + " " + type3 + " appointments.";
        String septReportType4 = "September: " + septInterCount + " " + type4 + " appointments.";
        String octReportType1 = "October: " + octGMCount + " " + type1 + " appointments.";
        String octReportType2 = "October: " + octConsultCount + " " + type2 + " appointments.";
        String octReportType3 = "October: " + octQBRCount + " " + type3 + " appointments.";
        String octReportType4 = "October: " + octInterCount + " " + type4 + " appointments.";
        String novReportType1 = "November: " + novGMCount + " " + type1 + " appointments.";
        String novReportType2 = "November: " + novConsultCount + " " + type2 + " appointments.";
        String novReportType3 = "November: " + novQBRCount + " " + type3 + " appointments.";
        String novReportType4 = "November: " + novInterCount + " " + type4 + " appointments.";
        String decReportType1 = "December: " + decGMCount + " " + type1 + " appointments.";
        String decReportType2 = "December: " + decConsultCount + " " + type2 + " appointments.";
        String decReportType3 = "December: " + decQBRCount + " " + type3 + " appointments.";
        String decReportType4 = "December: " + decInterCount + " " + type4 + " appointments.";

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

        //start setting up report for weekly count report
        ObservableList<Appointments> appointmentsThisWeek = AppointmentImp.getAllAppointmentsThisWeek();
        int mondayCount = 0;
        int tuesdayCount = 0;
        int wednesdayCount = 0;
        int thursdayCount = 0;
        int fridayCount = 0;
        int saturdayCount = 0;
        int sundayCount = 0;
        DayOfWeek mon = DayOfWeek.MONDAY;
        DayOfWeek tue = DayOfWeek.TUESDAY;
        DayOfWeek wed = DayOfWeek.WEDNESDAY;
        DayOfWeek thur = DayOfWeek.THURSDAY;
        DayOfWeek fri = DayOfWeek.FRIDAY;
        DayOfWeek sat = DayOfWeek.SATURDAY;
        DayOfWeek sun = DayOfWeek.SUNDAY;

        for(int l = 0; l < appointmentsThisWeek.size(); l++) {
            if(appointmentsThisWeek.get(l).getAppointmentStart().toLocalDate().getDayOfWeek().equals(mon)) {
                mondayCount += 1;
            }
            if(appointmentsThisWeek.get(l).getAppointmentStart().toLocalDate().getDayOfWeek().equals(tue)) {
                tuesdayCount += 1;
            }
            if(appointmentsThisWeek.get(l).getAppointmentStart().toLocalDate().getDayOfWeek().equals(wed)) {
                wednesdayCount += 1;
            }
            if(appointmentsThisWeek.get(l).getAppointmentStart().toLocalDate().getDayOfWeek().equals(thur)) {
                thursdayCount += 1;
            }
            if(appointmentsThisWeek.get(l).getAppointmentStart().toLocalDate().getDayOfWeek().equals(fri)) {
                fridayCount += 1;
            }
            if(appointmentsThisWeek.get(l).getAppointmentStart().toLocalDate().getDayOfWeek().equals(sat)) {
                saturdayCount += 1;
            }
            if(appointmentsThisWeek.get(l).getAppointmentStart().toLocalDate().getDayOfWeek().equals(sun)) {
                sundayCount += 1;
            }
        }
        mondayLabel.setText(String.valueOf(mondayCount));
        tuesdayLabel.setText(String.valueOf(tuesdayCount));
        wednesdayLabel.setText(String.valueOf(wednesdayCount));
        thursdayLabel.setText(String.valueOf(thursdayCount));
        fridayLabel.setText(String.valueOf(fridayCount));
        saturdayLabel.setText(String.valueOf(saturdayCount));
        sundayLabel.setText(String.valueOf(sundayCount));

    }
}
