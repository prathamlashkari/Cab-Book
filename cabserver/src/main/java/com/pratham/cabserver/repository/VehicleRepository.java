package com.pratham.cabserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pratham.cabserver.models.Vehicle;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {

}
