package com.springboot.project.onlineShop.model.CustomerBuilder;

import com.springboot.project.onlineShop.model.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;

public class CustomerBuilderFromMessage implements CustomerBuilder {

    private Message message;

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public Customer build() {
        Customer customer = new Customer();
        User user = new User();
        ShippingAddress shippingAddress = new ShippingAddress();
        BillingAddress billingAddress = new BillingAddress();
        try {
            JSONObject obj = new JSONObject(new String(message.getBody()));
            customer.setCustomerPhone(obj.getString("customerPhone"));
            customer.setFirstName(obj.getString("firstName"));
            customer.setFirstName(obj.getString("lastName"));


            //Shipping Address
            JSONObject sa_obj = obj.getJSONObject("shippingAddress");
            shippingAddress.setAddress(sa_obj.getString("address"));
            shippingAddress.setCity(sa_obj.getString("city"));
            shippingAddress.setCountry(sa_obj.getString("country"));
            shippingAddress.setState(sa_obj.getString("state"));
            shippingAddress.setZipcode(sa_obj.getString("zipcode"));
            shippingAddress.setCustomer(customer);
            customer.setShippingAddress(shippingAddress);

            //Blilling Address
            JSONObject ba_obj = obj.getJSONObject("billingAddress");
            billingAddress.setAddress(ba_obj.getString("address"));
            billingAddress.setCity(ba_obj.getString("city"));
            billingAddress.setCountry(ba_obj.getString("country"));
            billingAddress.setState(ba_obj.getString("state"));
            billingAddress.setZipcode(ba_obj.getString("zipcode"));
            billingAddress.setCustomer(customer);
            customer.setBillingAddress(billingAddress);

            //User
            JSONObject user_obj = obj.getJSONObject("user");
            user.setEmailId(user_obj.getString("emailId"));
            user.setPassword(user_obj.getString("password"));
            user.setEnabled(true);
            user.setCustomer(customer);
            customer.setUser(user);

            //Cart
            customer.setCart(new Cart());

            return customer;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
