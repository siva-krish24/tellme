package com.tellme.demo.repositries;

import com.tellme.demo.users.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    User findFirstByName(String name);

    @Query("{address:'?0'}")
    List<User> findCustomByAddress(String address);

    @Query("{address : { $regex: ?0 } }")
    List<User> findCustomByRegExAddress(String domain);
}