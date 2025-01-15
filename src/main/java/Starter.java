import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Starter extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) {


        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/order/placeOrder.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

    }
}
