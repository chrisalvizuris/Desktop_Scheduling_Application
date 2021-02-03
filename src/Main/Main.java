package Main;

import Dao.CustomerImp;
import Dao.DatabaseConnection;
import Model.Customers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/loginForm.fxml"));
        primaryStage.setTitle("ScheduleMe");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        Connection conn = DatabaseConnection.beginConnection();
        Customers customers = new Customers("Chris", "111 Test street", "91911", "6198889999");
        customers.setDivisionId(666);
        customers.setCustomerCreateDate(LocalDateTime.now());
        customers.setCustomerCreatedBy("Christian");
        customers.setCustomerUpdateDate(LocalDateTime.now());
        customers.setCustomerUpdateBy(null);
        CustomerImp.addCustomer(customers);


        launch(args);
        DatabaseConnection.closeConnection();
    }
}
