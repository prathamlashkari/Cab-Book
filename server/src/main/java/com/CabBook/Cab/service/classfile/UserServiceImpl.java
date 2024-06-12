package com.CabBook.cab.service.classfile;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CabBook.cab.exceptions.UserException;
import com.CabBook.cab.models.Ride;
import com.CabBook.cab.models.User;
import com.CabBook.cab.repository.UserRepository;
import com.CabBook.cab.service.interfacefile.UserService;
import com.CabBook.cab.utils.JwtUtils;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public User createUser(User user) throws UserException {
    throw new UnsupportedOperationException("Unimplemented method 'createUser'");
  }

  @Override
  public User getReqUserProfile(String token) throws UserException {
    Optional<String> email = JwtUtils.getEmailFromToken(token);
    User user = userRepository.findByEmail(email.get());

    if (user == null) {
      throw new UserException("User not found");
    }
    return user;
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
