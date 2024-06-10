package com.CabBook.cab.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.CabBook.cab.models.License;

public interface LicenseRepository extends MongoRepository<License, String> {

}
