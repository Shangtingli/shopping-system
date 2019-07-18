package com.springboot.project.onlineShop.service;

import com.springboot.project.onlineShop.model.Cart.Cart;
import com.springboot.project.onlineShop.model.Cart.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart getCartById(Long CartId) {
        Optional<Cart> optionalCart = cartRepository.findById(CartId);
        if (optionalCart.isPresent()){
            return optionalCart.get();
        }
        return null;
    }

    @Override
    public Cart validate(Long cartId) throws IOException {
        Cart cart = getCartById(cartId);
        if (cart == null || cart.getCartItem().size() == 0) {
            throw new IOException(cartId + "");
        }
        update(cart);
        return cart;
    }

    @Override
    public void update(Cart cart) {
        cartRepository.save(cart);
    }



}
