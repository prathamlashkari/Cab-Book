package com.CabBook.cab.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.CabBook.cab.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  public User findByEmail(String email);
}
