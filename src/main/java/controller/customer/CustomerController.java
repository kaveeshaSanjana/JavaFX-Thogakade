package controller.customer;

import db_Connection.DB_Connection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerService{
    private static CustomerController instance;


    private CustomerController(){

    }

    public static CustomerController getInstance() {
        //return instance
        return instance==null?instance =new CustomerController ():instance;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> cusotmerList = new ArrayList<>();
        try {
            ResultSet rst = DB_Connection.getDbConnection().getConnection().createStatement().executeQuery("SELECT * FROM customer");
            while (rst.next()){
                cusotmerList.add (new Customer(rst.getString(1),
                                              rst.getString(2),
                                               rst.getString(3),
                                             rst.getDouble(4)));
            }
            return cusotmerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        try {
            PreparedStatement stmt = DB_Connection.getDbConnection().getConnection().prepareStatement("UPDATE customer SET name = ?,address = ? , salary = ? WHERE id = ? ");
            stmt.setObject(1,customer.getName());
            stmt.setObject(2,customer.getAddress());
            stmt.setObject(3,customer.getSalary());
            stmt.setObject(4,customer.getId());
            return stmt.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCustomer(String customerId) {
        try {
            PreparedStatement stmt = DB_Connection.getDbConnection().getConnection().prepareStatement("DELETE FROM customer WHERE id = ?");
            stmt.setObject(1,customerId);
            return stmt.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveCustomer(Customer customer) {
        try {
            PreparedStatement stmt = DB_Connection.getDbConnection().getConnection().prepareStatement("INSERT INTO customer VALUES(?,?,?,?)");
            stmt.setObject(1,customer.getId());
            stmt.setObject(2,customer.getName());
            stmt.setObject(3,customer.getAddress());
            stmt.setObject(4,customer.getSalary());
            return stmt.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer searchCustomer(String customerId) {
        try {
            PreparedStatement stmt = DB_Connection.getDbConnection().getConnection().prepareStatement("SELECT * FROM customer WHERE id = ?");
            stmt.setObject(1,customerId);
            ResultSet rst = stmt.executeQuery();
            if(rst.next()){
                return new Customer(rst.getString(1),
                                    rst.getString(2),
                                    rst.getString(3),
                                    rst.getDouble(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
