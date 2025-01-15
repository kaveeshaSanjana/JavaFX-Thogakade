package controller.customer;

import model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();

    boolean updateCustomer(Customer customer);

    boolean deleteCustomer(String customerId);

    boolean saveCustomer(Customer customer);

    Customer searchCustomer(String customerId);
}
