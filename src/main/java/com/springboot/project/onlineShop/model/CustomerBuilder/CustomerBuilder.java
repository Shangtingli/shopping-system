package com.springboot.project.onlineShop.model.CustomerBuilder;

import com.springboot.project.onlineShop.model.Customer;

public interface CustomerBuilder {
    Customer build();
    void setPassword(String password);
    void setFirst_name(String first_name);
    void setLast_name(String last_name);
    void setCustomer_phone(String customer_phone);
    void setCustomer_email(String customer_email);
    void setAddress(String address);
    void setCity(String city);
    void setState(String state);
    void setZipcode(String zipcode);
    void setCountry(String country);
}
