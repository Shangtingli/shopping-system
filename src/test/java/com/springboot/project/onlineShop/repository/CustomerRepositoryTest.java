package com.springboot.project.onlineShop.repository;


import com.springboot.project.onlineShop.model.BillingAddress;
import com.springboot.project.onlineShop.model.Customer;
import com.springboot.project.onlineShop.model.ShippingAddress;
import com.springboot.project.onlineShop.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void shouldNotBeNull(){
        Customer customer = new Customer();
        customer.setShippingAddress(new ShippingAddress());
        customer.setBillingAddress(new BillingAddress());
        customer.setLastName("Li");
        customer.setFirstName("Jason");
        User user = new User();
        user.setEmailId("jason.li11@sap.com");
        user.setPassword("1234");
        user.setCustomer(customer);
        customer.setUser(user);
        customerRepository.save(customer);
        Customer customerGet = customerRepository.getCustomerByUserName("jason.li11@sap.com");
        assert(customerGet!= null);
        assert(customerGet.getFirstName().equals("Jason"));
    }
}