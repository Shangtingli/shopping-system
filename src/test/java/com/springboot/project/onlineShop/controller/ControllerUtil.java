package com.springboot.project.onlineShop.controller;

import com.springboot.project.onlineShop.model.Customer;
import com.springboot.project.onlineShop.model.CustomerBuilder.CustomerBasicBuilder;
import org.springframework.beans.factory.annotation.Value;

public class ControllerUtil {
    public static final String FIRST_NAME = "Shangting";
    public static final String LAST_NAME = "Li";
    public static final String PHONE = "2174024361";

    public static final String EMAIL = "shangtingli@test.com";

    public static final String PASSWORD= "1234";

    //Assumes shipping address is same as billing address
    public static final String ADDRESS= "2595 South Hoover Street, Apt 111";
    public static final String CITY = "Los Angeles";
    public static final String STATE = "California";
    public static final String ZIPCODE = "90007";
    public static final String COUNTRY = "United States";

    public static final String PRODUCT_NAME = "Love";
    public static final String PRODUCT_PRODUCER = "God";
    public static final int UNIT_STOCK = 100;
    public static final String CATEGORY = "UNVALUED";

    public static Customer getCustomer(){
        CustomerBasicBuilder builder = new CustomerBasicBuilder();
        builder.setFirstName(FIRST_NAME);
        builder.setLastName(LAST_NAME);
        builder.setAddress(ADDRESS);
        builder.setCity(CITY);
        builder.setCountry(COUNTRY);
        builder.setCustomerEmail(EMAIL);
        builder.setCustomerPhone(PHONE);
        builder.setPassword(PASSWORD);
        builder.setState(STATE);
        builder.setZipcode(ZIPCODE);
        return builder.build();
    }
}
