package com.tellme.demo.users;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "freshlead")
public class FreshLead extends UserCustomerEntity {

    public FreshLead(User user, Customer customer) {
        super(user, customer);
    }
}
