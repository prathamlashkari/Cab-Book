package com.pratham.cabserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pratham.cabserver.models.License;

@Repository
public interface LicenseRepository extends MongoRepository<License, String> {

}
