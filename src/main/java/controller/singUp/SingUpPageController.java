package controller.singUp;

import com.jfoenix.controls.JFXTextField;
import controller.loging.logingController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

public class SingUpPageController {


    public JFXTextField txtName;
    public JFXTextField txtPassword;
    public JFXTextField txtEmail;
    public JFXTextField txtComfermPassword;

    public void btnSubmitOnAction(ActionEvent actionEvent) {
        if(SingUpController.checkPasswordComferemd(new User(txtName.getText(),txtEmail.getText(),txtPassword.getText(),txtComfermPassword.getText()))){
            try {
                if(SingUpController.addUser(new User(txtName.getText(),txtEmail.getText(),txtPassword.getText(),txtComfermPassword.getText()))){
                    Stage stage = new Stage();
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/loging/loging_form.fxml"))));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
