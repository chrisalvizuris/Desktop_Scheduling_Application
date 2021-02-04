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

        launch(args);
    }
}
