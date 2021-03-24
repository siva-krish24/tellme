package com.tellme.demo.repositries;


import com.tellme.demo.users.UserCustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FollowUpCustomersRepositry extends MongoRepository<UserCustomerEntity,String> {
}
