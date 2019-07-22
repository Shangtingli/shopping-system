package com.springboot.project.onlineShop.controller;
import com.springboot.project.onlineShop.model.Cart;
import com.springboot.project.onlineShop.model.Customer;
import com.springboot.project.onlineShop.model.SalesOrder;
import com.springboot.project.onlineShop.service.CartService;
import com.springboot.project.onlineShop.service.SalesOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class OrderController {
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private CartService cartService;

    @Autowired
    private SalesOrderService customerOrderService;

    @RequestMapping("/order/{cartId}")
    public String createOrder(@PathVariable("cartId") Long cartId) {
    	log.info("Getting Order of cartId of {}", cartId);
   	 SalesOrder salesOrder = new SalesOrder();
   	 Cart cart = cartService.getCartById(cartId);
   	 salesOrder.setCart(cart);

   	 Customer customer = cart.getCustomer();
   	 salesOrder.setCustomer(customer);
   	 salesOrder.setShippingAddress(customer.getShippingAddress());
   	 salesOrder.setBillingAddress(customer.getBillingAddress());
   	 customerOrderService.addSalesOrder(salesOrder);
   	 return "redirect:/checkout?cartId=" + cartId;
    }
}
