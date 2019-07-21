package com.springboot.project.onlineShop.service;

import com.springboot.project.onlineShop.model.Cart;
import com.springboot.project.onlineShop.model.CartItem;
import org.springframework.transaction.annotation.Transactional;

public interface CartItemService {
    void addCartItem(CartItem cartItem);

    void removeCartItem(Long CartItemId);

    void removeAllCartItems(Cart cart);
}

