package com.pratham.cabserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pratham.cabserver.enums.UserRole;
import com.pratham.cabserver.exceptions.UserException;
import com.pratham.cabserver.models.User;
import com.pratham.cabserver.repository.UserRepository;
import com.pratham.cabserver.request.LoginRequest;
import com.pratham.cabserver.request.SignupRequest;
import com.pratham.cabserver.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public Authentication signUp(SignupRequest req) throws Exception {
    String email = req.getEmail();
    String fullname = req.getName();
    String mobile = req.getMobile();
    String password = req.getPassword();

    User user = userRepository.findByEmail(email);
    if (user != null) {
      throw new UserException("User already exists witht this email");
    }
    User newUser = new User();
    newUser.setEmail(email);
    newUser.setPassword(passwordEncoder.encode(password));
    newUser.setFullName(fullname);
    newUser.setMobile(mobile);
    newUser.setRole(UserRole.USER);
    User createdUser = userRepository.save(newUser);
    return new UsernamePasswordAuthenticationToken(createdUser.getEmail(),
        createdUser.getPassword());
  }

  @Override
  public Authentication login(LoginRequest req) {
    throw new UnsupportedOperationException("Unimplemented method 'login'");
  }

}
