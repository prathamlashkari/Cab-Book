package com.CabBook.cab.service.classfile;

import java.util.List;

import org.springframework.stereotype.Service;

import com.CabBook.cab.exceptions.UserException;
import com.CabBook.cab.models.Ride;
import com.CabBook.cab.models.User;
import com.CabBook.cab.service.interfacefile.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Override
  public User createUser(User user) throws UserException {
    throw new UnsupportedOperationException("Unimplemented method 'createUser'");
  }

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
