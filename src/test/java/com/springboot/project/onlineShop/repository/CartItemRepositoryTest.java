package com.springboot.project.onlineShop.repository;

import com.springboot.project.onlineShop.model.CartItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CartItemRepositoryTest {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Test
    public void shouldDelete(){
        CartItem cartItem = new CartItem();
        cartItemRepository.save(cartItem);
        assert(((List<CartItem>)cartItemRepository.findAll()).size() == 1);
        cartItemRepository.deleteById(1L);
        assert(((List<CartItem>)cartItemRepository.findAll()).size() == 0);
    }
}