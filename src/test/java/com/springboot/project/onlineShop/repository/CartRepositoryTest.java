package com.springboot.project.onlineShop.repository;

import com.springboot.project.onlineShop.model.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Test
    public void TestInsertDefaultConstructor(){
        cartRepository.deleteAll();
        Cart cart = new Cart();
        cartRepository.save(cart);
        List<Cart> carts = (List<Cart>) cartRepository.findAll();
        assert(carts.size()==1);
    }
}