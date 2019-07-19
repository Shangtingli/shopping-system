package com.springboot.project.onlineShop.model;

import com.springboot.project.onlineShop.model.BillingAddress;
import com.springboot.project.onlineShop.model.Cart;
import com.springboot.project.onlineShop.model.Customer;
import com.springboot.project.onlineShop.model.ShippingAddress;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "salesorder")
public class SalesOrder implements Serializable {

//    private static final String serialVersionUID = -6571020025726257848L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToOne
    @JoinColumn(name="cart")
    private Cart cart;

    @OneToOne
    @JoinColumn(name="customer")
    private Customer customer;

    @OneToOne
    @JoinColumn(name="shipping_address_id")
    private ShippingAddress shipping_address;

    @OneToOne
    @JoinColumn(name="billing_address_id")
    private BillingAddress billing_address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
}