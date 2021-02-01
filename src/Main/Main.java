package Main;

import Utils.DatabaseConnection;
import Utils.DatabaseQuery;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
        DatabaseQuery.setStatement(conn); // create statement object
        Statement statement = DatabaseQuery.getStatement(); //get statement reference
        launch(args);
        DatabaseConnection.closeConnection();
    }
}
