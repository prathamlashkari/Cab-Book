package com.pratham.cabserver.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pratham.cabserver.models.Driver;
import com.pratham.cabserver.models.Ride;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String> {
  public Driver findByEmail(String email);

  @Query("{ 'rides.status': 'REQUESTED', 'rides.driverId': :#{#driverId} }")
  List<Ride> getAllocatedRides(@Param("driverId") String driverId);

  @Query("{ 'rides.status': 'COMPLETED', 'rides.driverId': :#{#driverId} }")
  List<Ride> getCompleteRides(@Param("driverId") String driverId);
}
