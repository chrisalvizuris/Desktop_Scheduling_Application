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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class loginFormController {

    @FXML
    private Button signInButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label signInLabel;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label loginDescription;

    public void signInButtonPushed(ActionEvent event) throws IOException, SQLException {
        ObservableList<Users> allUsers = UserImp.getAllUsers();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Utilities/Nat", Locale.getDefault());
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

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/View/mainWindow.fxml"));
                Parent mainWindowParent = loader.load();
                Scene mainWindowScene = new Scene(mainWindowParent);

                mainWindowController controller = loader.getController();
                controller.initMainWindow(allUsers.get(i));
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(mainWindowScene);
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
            Locale fr = new Locale("fr");
            if(Locale.getDefault().equals(fr)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(resourceBundle.getString("Error"));
                alert.setContentText("Le nom d'utilisateur ou le mot de passe que vous avez entré n'est pas valide. Veuillez vérifier et réessayer.");
                alert.showAndWait();
            }   else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setContentText("Error: The username or password you entered is invalid. Please review and try again.");
                alert.showAndWait();
            }
        }


    }

    public void initialize() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Utilities/Nat", Locale.getDefault());
        Locale fr = new Locale("fr");
        if(Locale.getDefault().equals(fr)) {
            usernameTextField.setPromptText("Nom d'utilisateur");
            passwordTextField.setPromptText(resourceBundle.getString("Password"));
            signInLabel.setText(resourceBundle.getString("Login"));
            welcomeLabel.setText(resourceBundle.getString("LoginWelcome"));
            loginDescription.setText(resourceBundle.getString("LoginDescription"));
            signInButton.setText(resourceBundle.getString("Login"));

        }
    }
}
