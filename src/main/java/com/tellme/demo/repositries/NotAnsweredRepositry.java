package com.tellme.demo.repositries;

import com.tellme.demo.users.NotAnsweredCustomer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotAnsweredRepositry extends MongoRepository<NotAnsweredCustomer,String> {
}
