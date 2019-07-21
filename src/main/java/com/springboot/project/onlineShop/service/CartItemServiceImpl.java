package com.springboot.project.onlineShop.service;


import com.springboot.project.onlineShop.model.Cart;
import com.springboot.project.onlineShop.model.CartItem;
import com.springboot.project.onlineShop.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PreRemove;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public void removeCartItem(Long CartItemId) {

        cartItemRepository.deleteById(new Long(CartItemId));
        int size =((List<CartItem>) cartItemRepository.findAll()).size();
    }

    @Override
    public void removeAllCartItems(Cart cart) {
        for (CartItem cartItem: cart.getCartItem()){
            removeCartItem(cartItem.getId());
        }
    }
}
