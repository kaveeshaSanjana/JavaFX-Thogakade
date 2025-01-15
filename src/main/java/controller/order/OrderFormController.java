package controller.order;

import com.jfoenix.controls.JFXComboBox;
import controller.customer.CustomerController;
import controller.item.ItemController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import model.Customer;
import model.Item;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {
    public Label lblDate;
    public Label lblTime;
    public TextField txtDescription;
    public TextField txtStock;
    public JFXComboBox comboItemId;
    public JFXComboBox comboCustomer;
    public TextField txtAddress;
    public TextField txtSalary;
    public TextField txtUnitPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadDateAndTime();
        loadCustomerList();
        loadItemIds();
    }



    private void loadDateAndTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(simpleDateFormat.format(date));

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    LocalTime now = LocalTime.now();
                    lblTime.setText(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void loadCustomerList(){

        for (Customer customer : CustomerController.getInstance().getAll()) {
            comboCustomer.getItems().add(customer.getId());
        }

    }


    public void setCustomerValues(String customerId){
        Customer customer = CustomerController.getInstance().searchCustomer(customerId);
        txtAddress.setText(customer.getAddress());
        txtSalary.setText(String.valueOf(customer.getSalary()));
    }

    public void cmbCustomeraOnAction(ActionEvent actionEvent) {
        setCustomerValues(comboCustomer.getSelectionModel().getSelectedItem().toString());
    }

    public void loadItemIds(){
        comboItemId.setItems(ItemController.getItemController().getAll());
    }

    public void cmbItemOnAction(ActionEvent actionEvent) {
        try {
            Item item = ItemController.getItemController().searchItem(comboItemId.getSelectionModel().getSelectedItem().toString());
            txtDescription.setText(item.getDescription());
            txtUnitPrice.setText(item.getUnitPrice().toString());
            txtStock.setText(""+item.getQty());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
