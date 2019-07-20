package com.springboot.project.onlineShop.repository;

import com.springboot.project.onlineShop.model.BillingAddress;
import com.springboot.project.onlineShop.model.Customer;
import com.springboot.project.onlineShop.model.ShippingAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepository cartRepository;

//    @Test
//    public void customerShouldHaveCartAddress(){
//        customerRepository.deleteAll();
//        Customer customer = new Customer();
//        customer.setFirstName("Jason");
//        customer.setLastName("Li");
//        customer.setBillingAddress(new BillingAddress());
//        customer.setShippingAddress(new ShippingAddress());
//
//    }


}