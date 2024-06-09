package com.CabBook.Cab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CabBook.Cab.exceptions.UserException;
import com.CabBook.Cab.models.User;
import com.CabBook.Cab.repository.UserRepository;
import com.CabBook.Cab.request.SignupRequest;
import com.CabBook.Cab.response.JwtResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private UserRepository userRepository;

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

    return null;
  }
}
