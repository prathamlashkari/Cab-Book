package com.CabBook.cab.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.CabBook.cab.models.Driver;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String> {
  public Driver findByEmail(String email);
}
