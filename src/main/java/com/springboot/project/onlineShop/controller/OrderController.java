package com.springboot.project.onlineShop.controller;
import com.springboot.project.onlineShop.model.Cart.Cart;
import com.springboot.project.onlineShop.model.Customer.Customer;
import com.springboot.project.onlineShop.model.SalesOrder.SalesOrder;
import com.springboot.project.onlineShop.service.CartService;
import com.springboot.project.onlineShop.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private SalesOrderService customerOrderService;

    @RequestMapping("/order/{cartId}")
    public String createOrder(@PathVariable("cartId") Long cartId) {

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
