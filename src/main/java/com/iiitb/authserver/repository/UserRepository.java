package com.iiitb.authserver.repository;

import com.iiitb.authserver.model.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;



//@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserName(String username);

    Optional<User> findByCollegeEmail(String email);

    Boolean existsByUserName(String username);

    Boolean existsByCollegeEmail(String email);
}