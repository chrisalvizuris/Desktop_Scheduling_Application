package Controller;

import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class reportsWindowController {

    @FXML
    private Button returnHomeButton;

    private Users loggedUser;

    @FXML
    private void returnButtonPressed(ActionEvent event) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/mainWindow.fxml"));
        Parent mainWindowParent = loader.load();
        Scene mainWindowScene = new Scene(mainWindowParent);

        mainWindowController controller = loader.getController();
        controller.initMainWindow(loggedUser);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(mainWindowScene);
        window.show();
    }

    public void initialize(Users user) {
        loggedUser = user;
    }
}
