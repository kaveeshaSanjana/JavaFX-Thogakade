package controller.order;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.customer.CustomerController;
import controller.item.ItemController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.CartTM;
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
    public JFXTextField txtItemCount;
    public TableView tblCart;

    public TableColumn colOrderId;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotal;
    public TableColumn colDescId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescId.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

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

    ObservableList<CartTM> cartArray = FXCollections.observableArrayList();
    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        if(Integer.parseInt(txtStock.getText())>Integer.parseInt(txtItemCount.getText())){
            cartArray.add(new CartTM(comboItemId.getValue().toString(),
                    txtDescription.getText(),
                    Integer.parseInt(txtItemCount.getText()),
                    Double.parseDouble(txtUnitPrice.getText()),
                    Integer.parseInt(txtItemCount.getText()) * Double.parseDouble(txtUnitPrice.getText())));
        }
        tblCart.setItems(cartArray);
    }
}
