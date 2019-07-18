package com.springboot.project.onlineShop.controller;

import java.util.List;

import com.springboot.project.onlineShop.model.Cart.Cart;
import com.springboot.project.onlineShop.model.CartItem.CartItem;
import com.springboot.project.onlineShop.model.Customer.Customer;
import com.springboot.project.onlineShop.model.Product.Product;
import com.springboot.project.onlineShop.service.CartItemService;
import com.springboot.project.onlineShop.service.CartService;
import com.springboot.project.onlineShop.service.CustomerService;
import com.springboot.project.onlineShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
public class CartItemController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/cart/add/{productId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addCartItem(@PathVariable(value = "productId") Long productId) {
   	 Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
   	 String username = loggedInUser.getName();
   	 Customer customer = customerService.getCustomerByUserName(username);

   	 Cart cart = customer.getCart();
   	 List<CartItem> cartItems = cart.getCartItem();
   	 Product product = productService.getProductById(productId);
   	 
   	 for (int i = 0; i < cartItems.size(); i++) {
   		 CartItem cartItem = cartItems.get(i);
   		 if (product.getId() == (cartItem.getProduct().getId())) {
   			 cartItem.setQuantity(cartItem.getQuantity() + 1);
   			 cartItem.setPrice(cartItem.getQuantity() * cartItem.getProduct().getProductPrice());
   			 cartItemService.addCartItem(cartItem);
   			 return;
   		 }
   	 }
   	 
   	 CartItem cartItem = new CartItem();
   	 cartItem.setQuantity(1);
   	 cartItem.setProduct(product);
   	 System.out.println(product);
   	 cartItem.setPrice(product.getProductPrice());
   	 cartItem.setCart(cart);
   	 cartItemService.addCartItem(cartItem);
    }

    @RequestMapping("/cart/removeCartItem/{cartItemId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeCartItem(@PathVariable(value = "cartItemId") Long cartItemId) {
   	 cartItemService.removeCartItem(cartItemId);
    }

    @RequestMapping("/cart/removeAllItems/{cartId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeAllCartItems(@PathVariable(value = "cartId") Long cartId) {
   	 Cart cart = cartService.getCartById(cartId);
   	 cartItemService.removeAllCartItems(cart);
    }
}
