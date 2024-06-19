package com.pratham.cabserver.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratham.cabserver.exceptions.UserException;
import com.pratham.cabserver.models.Ride;
import com.pratham.cabserver.models.User;
import com.pratham.cabserver.repository.UserRepository;
import com.pratham.cabserver.service.UserService;
import com.pratham.cabserver.utils.JwtUtils;

@Service
public class UserSerivceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private JwtUtils jwtUtils;

  @Override
  public User getReqUserProfile(String token) throws UserException {
    String email = jwtUtils.getEmailFromJwt(token);
    User user = userRepository.findByEmail(email);

    if (user == null) {
      throw new UserException("User not found");
    }
    return user;
  }

  @Override
  public User findUserById(String userId) throws UserException {
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isEmpty()) {
      throw new UserException("User not found");
    }
    return userOptional.get();
  }

  @Override
  public User findUserByEmail(String email) throws UserException {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      throw new UserException("User not found");
    }
    return user;
  }

  @Override
  public User findUserByToken(String token) throws UserException {
    String email = jwtUtils.getEmailFromJwt(token);
    User user = findUserByEmail(email);
    return user;
  }

  @Override
  public List<Ride> completeRide(String userId) throws UserException {
    List<Ride> completeRides = userRepository.getCompletedRides(userId);
    return completeRides;
  }

}
