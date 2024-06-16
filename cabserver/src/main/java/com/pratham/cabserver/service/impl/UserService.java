package com.pratham.cabserver.service.impl;

import java.util.List;

import com.pratham.cabserver.exceptions.UserException;
import com.pratham.cabserver.models.Ride;
import com.pratham.cabserver.models.User;

public interface UserService {

  public User getReqUserProfile(String token) throws UserException;

  public User findUserById(String userId) throws UserException;

  public User findUserByEmail(String email) throws UserException;

  public User findUserByToken(String token) throws UserException;

  public List<Ride> completeRide(String userId) throws UserException;
}
