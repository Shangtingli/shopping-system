package com.springboot.project.onlineShop.controller;

import javax.validation.Valid;

import com.springboot.project.onlineShop.amqp.CustomerMQSender;
import com.springboot.project.onlineShop.model.Authorities;
import com.springboot.project.onlineShop.model.Cart;
import com.springboot.project.onlineShop.model.Customer;
import com.springboot.project.onlineShop.repository.AuthoritiesRepository;
import com.springboot.project.onlineShop.repository.UserRepository;
import com.springboot.project.onlineShop.service.AuthoritiesService;
import com.springboot.project.onlineShop.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	private CustomerMQSender rabbitMQSender;

	@RequestMapping(value = "/customer/registration", method = RequestMethod.GET)
	public ModelAndView getRegistrationForm() {
    	return new ModelAndView("register", "customer", new Customer());
	}

	@RequestMapping(value = "/customer/registration", method = RequestMethod.POST)
	public ModelAndView registerCustomer(@Valid @ModelAttribute(value = "customer") Customer customer,
        	BindingResult result) {
    	ModelAndView modelAndView = new ModelAndView();
    	if (result.hasErrors()) {
        	modelAndView.setViewName("register");
        	return modelAndView;
    	}

    	//Asynchrounously Process the message
		log.info("Sending Customer Message Asynchronously");
		rabbitMQSender.send(customer);

    	modelAndView.addObject("registrationSuccess", "Registered Successfully. Login using username and password. You would receive an email about this.");
    	modelAndView.setViewName("login");

    	log.info("Success in generating view at a responsive speed");
    	return modelAndView;
	}
}

