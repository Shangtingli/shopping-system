package com.springboot.project.onlineShop.amqp;

import com.springboot.project.onlineShop.model.*;
import com.springboot.project.onlineShop.service.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMQReceiver {
    private static final Logger log = LoggerFactory.getLogger(ProductMQReceiver.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private LogWriter logWriter;

    @RabbitListener(queues = "#{queue2.name}")
    public String receiveMessage(final Message message) throws JSONException {
//        log.info("Received Message {}", message.toString());
        //Well...
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JSONArray obj = new JSONArray(new String(message.getBody()));
        Long customerId = Long.valueOf(obj.getString(0));
        Long productId = Long.valueOf(obj.getString(1));
        Customer customer = customerService.getCustomerById(customerId);
        Cart cart = customer.getCart();
        List<CartItem> cartItems = cart.getCartItem();
        Product product = productService.getProductById(productId);
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem cartItem = cartItems.get(i);
            if (product.getId() == (cartItem.getProduct().getId())) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItem.setPrice(cartItem.getQuantity() * cartItem.getProduct().getProductPrice());
                cartItemService.addCartItem(cartItem);
                logWriter.insert("Request Completed For Customer " + customerId);
                return "redirect:/cart/getCartById";
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);
        cartItem.setProduct(product);
        cartItem.setPrice(product.getProductPrice());
        cartItem.setCart(cart);
        cartItemService.addCartItem(cartItem);
        logWriter.insert("Request Completed For Customer " + customerId);
        return "redirect:/cart/getCartById";
    }
}
