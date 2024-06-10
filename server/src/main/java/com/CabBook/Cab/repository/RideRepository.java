package com.CabBook.cab.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.CabBook.cab.models.Ride;

public interface RideRepository extends MongoRepository<Ride, String> {

}
