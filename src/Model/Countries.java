package Model;


import java.time.LocalDateTime;

public class Countries {

    private int countryID;
    private String countryName;
    private LocalDateTime countryCreateDate;
    private String countryCreatedBy;
    private LocalDateTime countryLastUpdate;
    private String countryLastUpdateBy;

    /**
     * This method is the constructor called when creating a country object.
     * @param countryName The name of the country.
     */
    public Countries(String countryName) {
        this.countryName = countryName;
        this.countryCreateDate = LocalDateTime.now();
        this.countryLastUpdate = LocalDateTime.now();
        this.countryLastUpdateBy = null;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public LocalDateTime getCountryCreateDate() {
        return countryCreateDate;
    }

    public void setCountryCreateDate(LocalDateTime countryCreateDate) {
        this.countryCreateDate = countryCreateDate;
    }

    public String getCountryCreatedBy() {
        return countryCreatedBy;
    }

    public void setCountryCreatedBy(String countryCreatedBy) {
        this.countryCreatedBy = countryCreatedBy;
    }

    public LocalDateTime getCountryLastUpdate() {
        return countryLastUpdate;
    }

    public void setCountryLastUpdate(LocalDateTime countryLastUpdate) {
        this.countryLastUpdate = countryLastUpdate;
    }

    public String getCountryLastUpdateBy() {
        return countryLastUpdateBy;
    }

    public void setCountryLastUpdateBy(String countryLastUpdateBy) {
        this.countryLastUpdateBy = countryLastUpdateBy;
    }

    @Override
    public String toString() {
        return countryName;
    }
}
