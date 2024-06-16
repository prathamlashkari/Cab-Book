package com.pratham.cabserver.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pratham.cabserver.models.Ride;
import com.pratham.cabserver.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  public User findByEmail(String email);

  @Query("{ 'status': 'COMPLETED', 'driverId': :driverId }")
  public List<Ride> getCompletedRides(@Param("driverId") String driverId);
}
