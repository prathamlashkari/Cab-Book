package com.CabBook.cab.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CabBook.cab.enums.UserRole;
import com.CabBook.cab.request.LoginRequest;
import com.CabBook.cab.request.SignupRequest;
import com.CabBook.cab.response.JwtResponse;
import com.CabBook.cab.service.interfacefile.AuthService;
import com.CabBook.cab.utils.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<JwtResponse> signUpHandler(@RequestBody SignupRequest req) throws Exception {
    Authentication authentication = authService.signUp(req);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = JwtUtils.generateToken(req.getEmail());

    JwtResponse res = new JwtResponse();
    res.setJwt(jwt);
    res.setAuthenticated(true);
    res.setError(false);
    res.setErrorDetails(null);
    res.setRole(UserRole.USER);
    res.setMessage("Account created successfully");

    return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
  }

  @PostMapping("/login")
  public ResponseEntity<JwtResponse> loginHandler(@RequestBody LoginRequest req) {
    Authentication authentication = authService.login(req);

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = JwtUtils.generateToken(req.getEmail());

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
