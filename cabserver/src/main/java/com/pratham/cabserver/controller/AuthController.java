package com.pratham.cabserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pratham.cabserver.request.SignupRequest;
import com.pratham.cabserver.response.JwtResponse;
import com.pratham.cabserver.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<JwtResponse> signupHandler(@RequestBody SignupRequest req) {

  }

}
