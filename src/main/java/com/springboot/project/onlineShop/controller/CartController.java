package com.springboot.project.onlineShop.controller;

import com.springboot.project.onlineShop.model.Cart;
import com.springboot.project.onlineShop.model.Customer;
import com.springboot.project.onlineShop.service.CartService;
import com.springboot.project.onlineShop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CartController {
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private CartService cartService;
    
    @RequestMapping(value = "/cart/getCartById", method = RequestMethod.GET)
    public ModelAndView getCartId(){
   	 Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
   	 String username = loggedInUser.getName();
   	 Customer customer = customerService.getCustomerByUserName(username);
        System.out.println("==================================");
        System.out.println(customer.getCart());
   	 ModelAndView modelAndView = new ModelAndView("cart");
   	 modelAndView.addObject("cartId", customer.getCart().getId());
   	 return modelAndView;
    }

    //Testing Purposes
    @RequestMapping(value = "/cart/getCartByUserName/{username}", method = RequestMethod.GET)
    public ModelAndView getCartBySpecificId(@PathVariable(value="username") String username){
        Customer customer = customerService.getCustomerByUserName(username);
        System.out.println("==================================");
        System.out.println(customer.getCart());
        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("cartId", customer.getCart().getId());
        return modelAndView;
    }

    @RequestMapping("/cart/getCart/{cartId}")
    public @ResponseBody
    Cart getCartItems(@PathVariable(value="cartId")Long cartId){
   	 return cartService.getCartById(cartId);
    }
}
