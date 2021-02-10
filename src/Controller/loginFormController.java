package Controller;

import Dao.UserImp;
import Model.Users;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class loginFormController {

    @FXML
    private Button signInButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    public void signInButtonPushed(ActionEvent event) throws IOException, SQLException {
        ObservableList<Users> allUsers = UserImp.getAllUsers();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        int count = 0;
        for (int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getUserName().equals(username) && allUsers.get(i).getPassword().equals(password)) {
                count += 1;
                String filename = "login_activity.txt", entry;
                FileWriter fileWriter = new FileWriter(filename, true);

                PrintWriter outputFile = new PrintWriter(fileWriter);

                entry = username + " attempted to log in on " + String.valueOf(LocalDateTime.now()) + ". Login successful.";
                outputFile.println(entry);
                outputFile.close();

                Parent parent = FXMLLoader.load(getClass().getResource("/View/mainWindow.fxml"));
                Scene scene = new Scene(parent);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(scene);
                window.show();
            }
        }
        if (count == 0) {
            String filename = "login_activity.txt", entry;
            FileWriter fileWriter = new FileWriter(filename);

            PrintWriter outputFile = new PrintWriter(fileWriter);

            entry = username + " attempted to log in on " + String.valueOf(LocalDateTime.now()) + ". Login UNSUCCESSFUL.";
            outputFile.println(entry);
            outputFile.close();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Error: The username or password you entered is invalid. Please review and try again.");
            alert.showAndWait();
        }


    }
}
