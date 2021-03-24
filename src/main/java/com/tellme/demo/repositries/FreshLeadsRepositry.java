package com.tellme.demo.repositries;

import com.tellme.demo.users.FreshLead;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FreshLeadsRepositry extends MongoRepository<FreshLead, String> {
}
