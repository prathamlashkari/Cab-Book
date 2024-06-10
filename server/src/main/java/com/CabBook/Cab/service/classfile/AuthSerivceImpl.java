package com.CabBook.cab.service.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.CabBook.cab.enums.UserRole;
import com.CabBook.cab.exceptions.UserException;
import com.CabBook.cab.models.User;
import com.CabBook.cab.repository.DriverRepository;
import com.CabBook.cab.request.LoginRequest;
import com.CabBook.cab.request.SignupRequest;
import com.CabBook.cab.service.interfacefile.AuthService;

public class AuthSerivceImpl implements AuthService {

  @Autowired
  private com.CabBook.cab.repository.UserRepository userRepository;
  @SuppressWarnings("unused")
  @Autowired
  private DriverRepository driverRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public Authentication signUp(SignupRequest req) throws Exception {
    String email = req.getEmail();
    String name = req.getName();
    String password = req.getPassword();
    String mobile = req.getMobile();

    User user = userRepository.findByEmail(email);
    if (user != null) {
      throw new UserException("User Already Exists With this email");
    }
    String encodedPassword = passwordEncoder.encode(password);
    User createdUser = new User();
    createdUser.setFullName(name);
    createdUser.setEmail(email);
    createdUser.setPassword(encodedPassword);
    createdUser.setMobile(mobile);
    createdUser.setRole(UserRole.USER);

    User savedUser = userRepository.save(createdUser);
    return new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
        savedUser.getPassword());
  }

  @Override
  public Authentication login(LoginRequest req) {
    String email = req.getEmail();
    String passwrod = req.getPassword();
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
        email, passwrod);
    return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
  }

}
