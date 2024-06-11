package com.CabBook.cab.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.CabBook.cab.models.Vehicle;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {

}
