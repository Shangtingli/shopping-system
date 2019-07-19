package com.springboot.project.onlineShop.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.project.onlineShop.model.BillingAddress;
import com.springboot.project.onlineShop.model.Cart;
import com.springboot.project.onlineShop.model.ShippingAddress;
import com.springboot.project.onlineShop.model.User;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
//
//    private static final String serialVersionUID = 2652327633296064143L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="first_name")
    private String first_name;

    @Column(name="last_name")
    private String last_name;

    @Column(name="customer_phone")
    private String customer_phone;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "shipping_address_id")
    private ShippingAddress shipping_address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "billing_address_id")
    private BillingAddress billing_address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    public String getCustomerPhone() {
        return customer_phone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customer_phone = customerPhone;
    }

    public ShippingAddress getShippingAddress() {
        return shipping_address;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shipping_address = shippingAddress;
    }

    public BillingAddress getBillingAddress() {
        return billing_address;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billing_address = billingAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

//    @Override
//    public String toString(){
//        String id_ = "Id: " + id + "\n";
//        String firstName_ = "firstName: " + firstName + "\n";
//        String lastName_ = "lastName: " + lastName + "\n";
//        String customerPhone_ = "Phone: " + customerPhone + "\n";
//        String addressId_ = "AddressId: " + billingAddress.getId() + "\n";
//        return id_ + firstName_ + lastName_ + customerPhone_ + addressId_;
//    }
}