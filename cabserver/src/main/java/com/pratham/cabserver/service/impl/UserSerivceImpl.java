package com.pratham.cabserver.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pratham.cabserver.exceptions.UserException;
import com.pratham.cabserver.models.Ride;
import com.pratham.cabserver.models.User;
import com.pratham.cabserver.service.UserService;

@Service
public class UserSerivceImpl implements UserService {

  @Override
  public User getReqUserProfile(String token) throws UserException {
    throw new UnsupportedOperationException("Unimplemented method 'getReqUserProfile'");
  }

  @Override
  public User findUserById(String userId) throws UserException {
    throw new UnsupportedOperationException("Unimplemented method 'findUserById'");
  }

  @Override
  public User findUserByEmail(String email) throws UserException {
    throw new UnsupportedOperationException("Unimplemented method 'findUserByEmail'");
  }

  @Override
  public User findUserByToken(String token) throws UserException {
    throw new UnsupportedOperationException("Unimplemented method 'findUserByToken'");
  }

  @Override
  public List<Ride> completeRide(String userId) throws UserException {
    throw new UnsupportedOperationException("Unimplemented method 'completeRide'");
  }

}
