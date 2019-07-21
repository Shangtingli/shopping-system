package com.springboot.project.onlineShop.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


//TODO: Figure out why the cart does not contain information about customers
@Entity
@Table(name = "cart")
public class Cart implements Serializable {

//    private static final String serialVersionUID = 8436097833452420298L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToOne(mappedBy = "cart")
    @JsonIgnore
    private Customer customer;

    //Important: The reason why the cart item cannot be removed is the cascade type. Make it to remove.
    @OneToMany(mappedBy = "cart", cascade = CascadeType.REMOVE,fetch=FetchType.EAGER,orphanRemoval = true)
    private List<CartItem> cart_item;

    @Column(name="total_price")
    private double total_price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getCartItem() {
        return cart_item;
    }

    public void setCartItem(List<CartItem> cartItem) {
        this.cart_item = cartItem;
    }

    public double getTotalPrice() {
        return total_price;
    }

    public void setTotalPrice(double totalPrice) {
        this.total_price = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}