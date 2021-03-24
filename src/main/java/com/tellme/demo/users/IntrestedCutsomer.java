package com.tellme.demo.users;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "intrested")
public class IntrestedCutsomer extends UserCustomerEntity {

    public IntrestedCutsomer(User user, Customer customer) {
        super(user, customer);
    }


}
