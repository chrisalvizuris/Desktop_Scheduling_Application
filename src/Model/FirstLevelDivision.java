package Model;

import java.time.LocalDateTime;

public class FirstLevelDivision {
    private int divisionId;
    private String divisionName;
    private LocalDateTime divisionCreateDate;
    private String divisionCreatedBy;
    private LocalDateTime divisionUpdateDate;
    private String divisionUpdateBy;

    public FirstLevelDivision(String divisionName) {
        this.divisionName = divisionName;
        this.divisionCreateDate = LocalDateTime.now();
        this.divisionUpdateDate = null;
        this.divisionUpdateBy = null;
        int countryId = 0;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public LocalDateTime getDivisionCreateDate() {
        return divisionCreateDate;
    }

    public void setDivisionCreateDate(LocalDateTime divisionCreateDate) {
        this.divisionCreateDate = divisionCreateDate;
    }

    public String getDivisionCreatedBy() {
        return divisionCreatedBy;
    }

    public void setDivisionCreatedBy(String divisionCreatedBy) {
        this.divisionCreatedBy = divisionCreatedBy;
    }

    public LocalDateTime getDivisionUpdateDate() {
        return divisionUpdateDate;
    }

    public void setDivisionUpdateDate(LocalDateTime divisionUpdateDate) {
        this.divisionUpdateDate = divisionUpdateDate;
    }

    public String getDivisionUpdateBy() {
        return divisionUpdateBy;
    }

    public void setDivisionUpdateBy(String divisionUpdateBy) {
        this.divisionUpdateBy = divisionUpdateBy;
    }
}
