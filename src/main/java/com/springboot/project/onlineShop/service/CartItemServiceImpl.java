package com.springboot.project.onlineShop.service;


import com.springboot.project.onlineShop.model.Cart;
import com.springboot.project.onlineShop.model.CartItem;
import com.springboot.project.onlineShop.model.Product;
import com.springboot.project.onlineShop.repository.CartItemRepository;
import com.springboot.project.onlineShop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PreRemove;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addCartItem(CartItem cartItem) {
        Product product = cartItem.getProduct();
        product.setUnitStock(product.getUnitStock()-1);
        productRepository.save(product);
        cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public void removeCartItem(Long CartItemId) {
        CartItem cartItem = null;
        int quantity = 0;
        Product product = null;
        Optional op = cartItemRepository.findById(CartItemId);
        if (op.isPresent()){
            cartItem = (CartItem) op.get();
            quantity = cartItem.getQuantity();
        }
        if (cartItem != null){
            product = cartItem.getProduct();
        }
        product.setUnitStock(product.getUnitStock()+quantity);
        cartItemRepository.deleteById(CartItemId);
        productRepository.save(product);
    }

    @Override
    public void removeAllCartItems(Cart cart) {
        for (CartItem cartItem: cart.getCartItem()){
            removeCartItem(cartItem.getId());
        }
    }
}
