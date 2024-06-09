package com.CabBook.cab.service.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.CabBook.cab.enums.UserRole;
import com.CabBook.cab.exceptions.UserException;
import com.CabBook.cab.models.User;
import com.CabBook.cab.repository.DriverRepository;
import com.CabBook.cab.request.LoginRequest;
import com.CabBook.cab.request.SignupRequest;
import com.CabBook.cab.response.JwtResponse;
import com.CabBook.cab.service.interfacefile.AuthService;
import com.CabBook.cab.utils.JwtUtils;

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
  public ResponseEntity<JwtResponse> signUp(SignupRequest req) throws Exception {
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
    Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
        savedUser.getPassword());
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = JwtUtils.generateToken(email);

    JwtResponse res = new JwtResponse();
    res.setJwt(jwt);
    res.setAuthenticated(true);
    res.setError(false);
    res.setErrorDetails(null);
    res.setRole(UserRole.USER);
    res.setMessage("Account created successfully");

    return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
  }

  @Override
  public ResponseEntity<JwtResponse> login(LoginRequest req) {
    String email = req.getEmail();
    String passwrod = req.getPassword();
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
        email, passwrod);
    Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = JwtUtils.generateToken(email);

    JwtResponse res = new JwtResponse();
    res.setJwt(jwt);
    res.setAuthenticated(true);
    res.setError(false);
    res.setErrorDetails(null);
    res.setRole(UserRole.USER);
    res.setMessage("Login successfully");

    return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
  }

}
