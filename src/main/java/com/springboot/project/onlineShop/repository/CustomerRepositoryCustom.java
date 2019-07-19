package com.springboot.project.onlineShop.repository;

import com.springboot.project.onlineShop.model.Customer;

public interface CustomerRepositoryCustom {
    Customer getCustomerByUserName(String userName);
}
