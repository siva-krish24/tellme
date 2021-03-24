package com.tellme.demo.users;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notintrested")
public class NotIntrestedCustomer extends UserCustomerEntity {
    public NotIntrestedCustomer(User user, Customer customer) {
        super(user, customer);
    }
}
