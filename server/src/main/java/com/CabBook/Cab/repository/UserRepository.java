package com.CabBook.Cab.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.CabBook.Cab.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
