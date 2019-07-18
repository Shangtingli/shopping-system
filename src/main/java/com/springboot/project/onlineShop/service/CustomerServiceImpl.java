package com.springboot.project.onlineShop.service;

import com.springboot.project.onlineShop.model.Customer.Customer;
import com.springboot.project.onlineShop.model.Customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerByUserName(String userName) {
        return customerRepository.getCustomerByUserName(userName);
    }
}
