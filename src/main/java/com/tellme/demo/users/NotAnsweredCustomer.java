package com.tellme.demo.users;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notanswered")
public class NotAnsweredCustomer extends UserCustomerEntity {
    public NotAnsweredCustomer(User user, Customer customer) {
        super(user, customer);
    }
}
