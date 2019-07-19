package com.springboot.project.onlineShop.service;

import com.springboot.project.onlineShop.model.Customer;

public interface CustomerService {

    void addCustomer(Customer customer);

    Customer getCustomerByUserName(String userName);
}
