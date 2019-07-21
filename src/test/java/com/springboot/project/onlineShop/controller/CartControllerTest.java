package com.springboot.project.onlineShop.controller;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(controllers = CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CartController cartController;

    @Test
    public void sayHello(){
        System.out.println("Hello");
    }

    @Test
    public void test1() throws Exception {
        mockMvc.perform(get("localhost:8080/cart/getCartByUserName/jason.li11@sap.com"));
    }
}