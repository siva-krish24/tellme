package com.tellme.demo.repositries;

import com.tellme.demo.users.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepositry extends MongoRepository<Customer,String> {

}
