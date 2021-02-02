package Model;

import java.time.LocalDateTime;

public class Customers {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerPostal;
    private String customerPhone;
    private LocalDateTime customerCreateDate;
    private String customerCreatedBy;
    private LocalDateTime customerUpdateDate;
    private String customerUpdateBy;

    public Customers(String customerName, String customerAddress, String customerPostal, String customerPhone) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostal = customerPostal;
        this.customerPhone = customerPhone;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPostal() {
        return customerPostal;
    }

    public void setCustomerPostal(String customerPostal) {
        this.customerPostal = customerPostal;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public LocalDateTime getCustomerCreateDate() {
        return customerCreateDate;
    }

    public void setCustomerCreateDate(LocalDateTime customerCreateDate) {
        this.customerCreateDate = customerCreateDate;
    }

    public String getCustomerCreatedBy() {
        return customerCreatedBy;
    }

    public void setCustomerCreatedBy(String customerCreatedBy) {
        this.customerCreatedBy = customerCreatedBy;
    }

    public LocalDateTime getCustomerUpdateDate() {
        return customerUpdateDate;
    }

    public void setCustomerUpdateDate(LocalDateTime customerUpdateDate) {
        this.customerUpdateDate = customerUpdateDate;
    }

    public String getCustomerUpdateBy() {
        return customerUpdateBy;
    }

    public void setCustomerUpdateBy(String customerUpdateBy) {
        this.customerUpdateBy = customerUpdateBy;
    }
}