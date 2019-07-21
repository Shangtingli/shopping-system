package com.springboot.project.onlineShop.service;

import com.springboot.project.onlineShop.model.Cart;
import com.springboot.project.onlineShop.model.CartItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTest {
    @Autowired
    private CartService cartService;

    @Test
    public void sayHello(){
        System.out.println("Hello");
    }
//    @Test
//    public void shouldGetNotNull(){
//        Cart cart = cartService.getCartById(new Long(1));
//        for (CartItem cartItem : cart.getCartItem()){
//            System.out.println(cartItem.getId());
//        }
//        assert(cart != null);
//    }
}