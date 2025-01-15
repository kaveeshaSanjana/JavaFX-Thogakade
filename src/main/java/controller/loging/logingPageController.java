package controller.loging;

import db_Connection.DB_Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class logingPageController {

    public AnchorPane loadPage;
    public PasswordField txtPassword;
    public TextField txtUsername;

    public void btnLoginOnAction(ActionEvent actionEvent)  {
        try {
            if(logingController.loging(new User(txtUsername.getText(),"",txtPassword.getText(),""))){
                URL resource = this.getClass().getResource("/view/homepage.fxml");
                assert resource != null;
                Parent load = FXMLLoader.load(resource);

                this.loadPage.getChildren().clear();
                this.loadPage.getChildren().add(load);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
