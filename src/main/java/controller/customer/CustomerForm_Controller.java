package controller.customer;

import db_Connection.DB_Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerForm_Controller implements Initializable {

    public TextField txtId;
    public TextField txtName;
    public TextField txtSalary;
    public TextField txtAddress;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colSaley;
    public TableColumn colAddress;
    public TableColumn colAddress1;
    public TableView tblCustomer;


    public void btnAddOnClick(ActionEvent actionEvent) {
        if(CustomerController
                .getInstance()
                .saveCustomer
                (new Customer(txtId.getText(),
                            txtName.getText(),
                            txtAddress.getText(),
                            Double.parseDouble(txtSalary.getText()))))
                {
                     System.out.println("addSuccess");
                }
    }

    public void btnUpdateOnClick(ActionEvent actionEvent) {
        if(CustomerController.getInstance().updateCustomer(new Customer(txtId.getText(),
                                                                        txtName.getText(),
                                                                        txtAddress.getText(),
                                                                        Double.parseDouble(txtSalary.getText()))))
        {
            System.out.println("updated Success");
        }
    }

    public void btnDeleteOnClick(ActionEvent actionEvent) {
        //CustomerController.getInstance().updateCustomer(txtId.getText());
    }

    private void loadTable(){
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try {

            PreparedStatement stmt = DB_Connection.getDbConnection().getConnection().prepareStatement("SELECT * FROM Customer");
            ResultSet rst = stmt.executeQuery();
            while (rst.next()){
                customerList.add(new Customer(rst.getString(1),
                                                rst.getString(2),
                                                    rst.getString(3),
                                                        rst.getDouble(4)));
            }
            tblCustomer.setItems(customerList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSaley.setCellValueFactory(new PropertyValueFactory<>("salary"));


        loadTable();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->{
            if(newValue != null){
                setTextToValues((Customer)newValue);
            }
        } );
    }

    private void setTextToValues(Customer customer) {
        txtId.setText(customer.getId());
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtSalary.setText(customer.getSalary().toString());
    }

    public void btnSearchOnClick(ActionEvent actionEvent) {
    }
}
