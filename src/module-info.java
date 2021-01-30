module Desktop.Scheduling.Application {

    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;

    opens View;
    opens Main;
    opens Controller;
}