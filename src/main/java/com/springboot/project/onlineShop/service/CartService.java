package com.springboot.project.onlineShop.service;

import com.springboot.project.onlineShop.model.Cart.Cart;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

public interface CartService {
    Cart getCartById(Long CartId);

    Cart validate(Long cartId) throws IOException;

    void update(Cart cart);
}
