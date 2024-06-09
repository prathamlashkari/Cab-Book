package com.CabBook.Cab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CabBook.Cab.enums.UserRole;
import com.CabBook.Cab.exceptions.UserException;
import com.CabBook.Cab.models.User;
import com.CabBook.Cab.repository.DriverRepository;
import com.CabBook.Cab.repository.UserRepository;
import com.CabBook.Cab.request.SignupRequest;
import com.CabBook.Cab.response.JwtResponse;
import com.CabBook.Cab.utils.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private DriverRepository driverRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/signup")
  public ResponseEntity<JwtResponse> signUpHandler(@RequestBody SignupRequest req) throws Exception {

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

    return null;
  }
}
