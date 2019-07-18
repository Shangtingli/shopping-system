package com.springboot.project.onlineShop.service;


import com.springboot.project.onlineShop.model.Cart.Cart;
import com.springboot.project.onlineShop.model.CartItem.CartItem;
import com.springboot.project.onlineShop.model.CartItem.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public void removeCartItem(Long CartItemId) {
        cartItemRepository.deleteById(CartItemId);
    }

    @Override
    public void removeAllCartItems(Cart cart) {
        for (CartItem cartItem: cart.getCartItem()){
            removeCartItem(cartItem.getId());
        }
    }
}
