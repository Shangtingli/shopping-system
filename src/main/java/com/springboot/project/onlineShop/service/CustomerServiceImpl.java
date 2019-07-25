package com.springboot.project.onlineShop.service;

import com.springboot.project.onlineShop.model.Customer;
import com.springboot.project.onlineShop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public void removeCustomerByUserName(String userName){
        Customer customer = customerRepository.getCustomerByUserName(userName);

        customerRepository.deleteById(customer.getId());
        return;
    }
    @Override
    public Customer getCustomerById(Long id) {
        Optional optional = customerRepository.findById(id);
        return optional.isPresent() ? (Customer)optional.get(): null;
    }
}
