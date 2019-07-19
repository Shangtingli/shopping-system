package com.springboot.project.onlineShop.service;

import com.springboot.project.onlineShop.model.Customer;
import com.springboot.project.onlineShop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public void addCustomer(Customer customer) {
        System.out.println(customer);
        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerByUserName(String userName) {
        return customerRepository.getCustomerByUserName(userName);
    }
}
