package com.CabBook.cab.service.interfacefile;

import java.util.List;

import com.CabBook.cab.exceptions.UserException;
import com.CabBook.cab.models.Ride;
import com.CabBook.cab.models.User;

public interface UserService {

  public User createUser(User user) throws UserException;

  public User getReqUserProfile(String token) throws UserException;

  public User findUserById(String userId) throws UserException;

  public User findUserByEmail(String email) throws UserException;

  public User findUserByToken(String token) throws UserException;

  public List<Ride> completeRide(String userId) throws UserException;
}
