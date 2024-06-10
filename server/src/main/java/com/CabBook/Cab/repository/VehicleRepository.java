package com.CabBook.cab.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.CabBook.cab.models.Vehicle;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {

}
