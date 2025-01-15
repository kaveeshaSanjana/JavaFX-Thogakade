package controller.dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashboardController {


    public AnchorPane loadForm;

    public void btnCustomerFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/homepage.fxml");

        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        this.loadForm.getChildren().clear();
        this.loadForm.getChildren().add(load);

    }
}
