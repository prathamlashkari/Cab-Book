package com.CabBook.cab.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.CabBook.cab.models.License;

@Repository
public interface LicenseRepository extends MongoRepository<License, String> {

}
