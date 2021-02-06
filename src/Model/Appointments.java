package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class Appointments {
    private int appointmentId;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentType;
    private LocalDateTime appointmentStart;
    private LocalDateTime appointmentEnd;
    private LocalDateTime appointmentCreateDate;
    private String appointmentCreatedBy;
    private LocalDateTime appointmentUpdateDate;
    private String appointmentUpdatedBy;
    private int customerId;
    private int userId;
    private int contactId;

    public Appointments(int appointmentId, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, int customerId) {
        this.appointmentId = appointmentId;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentLocation = appointmentLocation;
        this.appointmentType = appointmentType;
        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;
        this.customerId = customerId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public LocalDateTime getAppointmentStart() {
        return appointmentStart;
    }

    public void setAppointmentStart(LocalDateTime appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    public LocalDateTime getAppointmentEnd() {
        return appointmentEnd;
    }

    public void setAppointmentEnd(LocalDateTime appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    public LocalDateTime getAppointmentCreateDate() {
        return appointmentCreateDate;
    }

    public void setAppointmentCreateDate(LocalDateTime appointmentCreateDate) {
        this.appointmentCreateDate = appointmentCreateDate;
    }

    public String getAppointmentCreatedBy() {
        return appointmentCreatedBy;
    }

    public void setAppointmentCreatedBy(String appointmentCreatedBy) {
        this.appointmentCreatedBy = appointmentCreatedBy;
    }

    public LocalDateTime getAppointmentUpdateDate() {
        return appointmentUpdateDate;
    }

    public void setAppointmentUpdateDate(LocalDateTime appointmentUpdateDate) {
        this.appointmentUpdateDate = appointmentUpdateDate;
    }

    public String getAppointmentUpdatedBy() {
        return appointmentUpdatedBy;
    }

    public void setAppointmentUpdatedBy(String appointmentUpdatedBy) {
        this.appointmentUpdatedBy = appointmentUpdatedBy;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

}
