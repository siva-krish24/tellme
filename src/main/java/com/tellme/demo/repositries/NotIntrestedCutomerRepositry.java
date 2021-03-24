package com.tellme.demo.repositries;

import com.tellme.demo.users.NotIntrestedCustomer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotIntrestedCutomerRepositry extends MongoRepository<NotIntrestedCustomer,String> {
}
