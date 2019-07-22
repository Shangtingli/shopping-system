package com.springboot.project.onlineShop.model.CustomerBuilder;

import com.springboot.project.onlineShop.model.Customer;

public class CustomerBuilderFactory {
    private static final String DEFAULT_BUILDER = "Basic";

    public static CustomerBuilder getBuilder(String builder){
        switch(builder){
            case "Basic":
                return new CustomerBasicBuilder();
            case "Message":
                return new CustomerBuilderFromMessage();
                default:
                    throw new IllegalStateException("Invalid Builder: " + builder);
        }
    }

    public static CustomerBuilder getBuilder(){
        return getBuilder(DEFAULT_BUILDER);
    }
}
