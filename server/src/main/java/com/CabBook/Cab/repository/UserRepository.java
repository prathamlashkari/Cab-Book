package com.CabBook.cab.repository;

import java.util.List;

import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.CabBook.cab.models.Ride;
import com.CabBook.cab.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  public User findByEmail(String email);

  @Query("select r form ride where r.status = COMPLETED and r.userid=:userId")
  public List<Ride> getCompleteRide(@Param("userId") String userId);
}
