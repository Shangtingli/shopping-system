package com.springboot.project.onlineShop.controller;

import javax.validation.Valid;

import com.springboot.project.onlineShop.model.Authorities;
import com.springboot.project.onlineShop.model.Cart;
import com.springboot.project.onlineShop.model.Customer;
import com.springboot.project.onlineShop.repository.AuthoritiesRepository;
import com.springboot.project.onlineShop.repository.UserRepository;
import com.springboot.project.onlineShop.service.AuthoritiesService;
import com.springboot.project.onlineShop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RegistrationController {
    
	@Autowired
	private CustomerService customerService;

	@Autowired
	private AuthoritiesService authoritiesService;

	@RequestMapping(value = "/customer/registration", method = RequestMethod.GET)
	public ModelAndView getRegistrationForm() {
    	return new ModelAndView("register", "customer", new Customer());
	}

	@RequestMapping(value = "/customer/registration", method = RequestMethod.POST)
	public ModelAndView registerCustomer(@Valid @ModelAttribute(value = "customer") Customer customer,
        	BindingResult result) {
    	ModelAndView modelAndView = new ModelAndView();

//		customer.setId(dummy);
//		customer.getBillingAddress().setId(dummy);
//		customer.getShippingAddress().setId(dummy);
//		customer.getUser().setId(dummy);
//		customer.setCart(new Cart());
//		customer.getCart().setId(dummy);
//		System.out.println(customer);
    	if (result.hasErrors()) {
        	modelAndView.setViewName("register");
        	return modelAndView;
    	}

//		System.out.println(customer.getId());

		//TODO: Why JDBCAuthentication Does Not Work
    	customerService.addCustomer(customer);
		authoritiesService.addAuthorities(new Authorities(null,customer.getUser().getEmailId(),"ROLE_USER"));
    	modelAndView.addObject("registrationSuccess", "Registered Successfully. Login using username and password");
    	modelAndView.setViewName("login");
    	return modelAndView;
	}
}

