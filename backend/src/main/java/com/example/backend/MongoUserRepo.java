package com.example.backend;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository


public interface MongoUserRepo extends MongoRepository <MongoUser,String> {
    Optional<MongoUser> findMongoUserByUsername (String username);

        //public final MongoUserDetailService mongouserdeatiolservice;
}
