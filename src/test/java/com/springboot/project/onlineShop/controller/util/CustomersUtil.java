package com.springboot.project.onlineShop.controller.util;

import com.springboot.project.onlineShop.model.Customer;
import com.springboot.project.onlineShop.model.CustomerBuilder.CustomerBasicBuilder;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomersUtil {

    private List<Customer> customers;

    private Random random;
    public CustomersUtil(){
        random = new Random();
        customers = new ArrayList<>();
        for (int i =0; i < FIRST_NAMES.length;i++) {
            CustomerBasicBuilder builder = new CustomerBasicBuilder();
            builder.setFirstName(FIRST_NAMES[i]);
            builder.setLastName(LAST_NAMES[i]);
            builder.setAddress(ADDRESSES[i]);
            builder.setCity(CITIES[i]);
            builder.setCountry(COUNTRIES[i]);
            builder.setCustomerEmail(EMAILS[i]);
            builder.setCustomerPhone(PHONES[i]);
            builder.setPassword(PASSWORDS[i]);
            builder.setState(STATES[i]);
            builder.setZipcode(ZIPCODES[i]);
            customers.add(builder.build());
        }
    }

    public Customer getRandomCustomer(){
        int i = random.nextInt(3);
        return customers.get(i);
    }


    public static final String[] FIRST_NAMES = new String[]{"Shangting","Jason","ST"};
    public static final String[] LAST_NAMES = new String[]{"Li","Lee","L"};
    public static final String[] PHONES = new String[]{"2174024361","18923899620","19938483386"};

    public static final String[] EMAILS = new String[]{"shangtingli@test1.com","shangtingli@test2.com","shangtingli@test3.com"};

    public static final String[] PASSWORDS= new String[]{"1234","1234","1234"};

    //Assumes shipping address is same as billing address
    public static final String[] ADDRESSES= new String[]{
            "2595 South Hoover Street, Apt 111",
            "404 E Clark Street, Apt 7",
            "531 E Healey Street, Apt 10"
    };
    public static final String[] CITIES = new String[]{
            "Los Angeles", "Champaign","Champaign"
    };
    public static final String[] STATES = new String[]{
            "California","Illinois","Illinois"
    };
    public static final String[] ZIPCODES = new String[]{
            "90007","61802","61802"
    };
    public static final String[] COUNTRIES = new String[]{
            "United States","United States","United States"
    };

    public static final String PRODUCT_NAME = "Love";
    public static final String PRODUCT_PRODUCER = "God";
    public static final int UNIT_STOCK = 10;
    public static final String CATEGORY = "UNVALUED";

    public List<Customer> getAllCustomers() {
        return customers;
    }
}
