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
  public User getReqUserProfile(String token) throws UserException {
    String email = JwtUtils.getEmailFromJwt(token);
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
    String email = JwtUtils.getEmailFromJwt(token);
    User user = findUserByEmail(email);
    return user;
  }

  @Override
  public List<Ride> completeRide(String userId) throws UserException {
    List<Ride> completeRides = userRepository.getCompletedRides(userId);
    return completeRides;
  }

}
