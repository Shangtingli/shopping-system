package com.springboot.project.onlineShop.controller;

import javax.validation.Valid;

import com.springboot.project.onlineShop.model.Customer.Customer;
import com.springboot.project.onlineShop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "/customer/registration", method = RequestMethod.GET)
	public ModelAndView getRegistrationForm() {
    	return new ModelAndView("register", "customer", new Customer());
    	/*ModelAndView(viewName, modelName, modelObject)*/
	}

	@RequestMapping(value = "/customer/registration", method = RequestMethod.POST)
	public ModelAndView registerCustomer(@Valid @ModelAttribute(value = "customer") Customer customer,
        	BindingResult result) {
    	ModelAndView modelAndView = new ModelAndView();
   	 
    	if (result.hasErrors()) {
        	modelAndView.setViewName("register");
        	return modelAndView;
    	}
    	customerService.addCustomer(customer);
   	 
    	modelAndView.addObject("registrationSuccess", "Registered Successfully. Login using username and password");
    	modelAndView.setViewName("login");
    	return modelAndView;
	}
}

