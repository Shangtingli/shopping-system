package com.springboot.project.onlineShop.model.CustomerBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.project.onlineShop.model.*;

import javax.persistence.*;

public class CustomerBasicBuilder implements CustomerBuilder{
    private String first_name;
    private String last_name;
    private String customer_phone;
    private String customer_email;
    private String password;
    //Assumes shipping address is same as billing address
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String country;

    public void setPassword(String password) {
        this.password = password;
    }


    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public Customer build(){
        Customer customer = new Customer();
        customer.setFirstName(first_name);
        customer.setLastName(last_name);
        customer.setCustomerPhone(customer_phone);
        //Address
        BillingAddress billingAddress = new BillingAddress();
        ShippingAddress shippingAddress = new ShippingAddress();
        billingAddress.setAddress(address);
        shippingAddress.setAddress(address);
        billingAddress.setCity(city);
        shippingAddress.setCity(city);
        billingAddress.setCountry(country);
        shippingAddress.setCountry(country);
        billingAddress.setState(state);
        shippingAddress.setState(state);
        billingAddress.setZipcode(zipcode);
        shippingAddress.setZipcode(zipcode);
        customer.setShippingAddress(shippingAddress);
        customer.setBillingAddress(billingAddress);
        billingAddress.setCustomer(customer);
        shippingAddress.setCustomer(customer);

        //Cart
        Cart cart = new Cart();
        customer.setCart(cart);
        cart.setCustomer(customer);
        //User
        User user = new User();
        user.setPassword(password);
        user.setEmailId(customer_email);
        user.setEnabled(true);
        customer.setUser(user);
        user.setCustomer(customer);

        return customer;
    }


}
