package com.springboot.project.onlineShop.model.CustomerBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.project.onlineShop.model.*;

import javax.persistence.*;

public class CustomerBasicBuilder implements CustomerBuilder{
    public String firstName;
    public String lastName;
    public String customerPhone;
    public String customerEmail;
    public String password;
    //Assumes shipping address is same as billing address
    public String address;
    public String city;
    public String state;
    public String zipcode;
    public String country;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setPassword(String password) {
        this.password = password;
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
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setCustomerPhone(customerPhone);
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
        user.setEmailId(customerEmail);
        user.setEnabled(true);
        customer.setUser(user);
        user.setCustomer(customer);

        return customer;
    }


}
