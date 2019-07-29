package com.springboot.project.onlineShop.service;

import com.springboot.project.onlineShop.model.Customer;

import java.util.List;

public interface CustomerService {

    void addCustomer(Customer customer);

    Customer getCustomerByUserName(String userName);

    void removeCustomerByUserName(String userName);

    Customer getCustomerById(Long id);

    //TODO: Just For Testing Purposes
    List<Customer> getAllCustomers();

    void removeAll();
}
