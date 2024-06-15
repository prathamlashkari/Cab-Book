package com.CabBook.cab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CabBook.cab.enums.UserRole;
import com.CabBook.cab.models.Driver;
import com.CabBook.cab.repository.DriverRepository;
import com.CabBook.cab.request.DriversSignupRequest;
import com.CabBook.cab.request.LoginRequest;
import com.CabBook.cab.request.SignupRequest;
import com.CabBook.cab.response.JwtResponse;
import com.CabBook.cab.service.interfacefile.AuthService;
import com.CabBook.cab.service.interfacefile.DriverService;
import com.CabBook.cab.utils.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthService authService;
  @Autowired
  private DriverService driverService;
  @Autowired
  private DriverRepository driverRepository;

  @PostMapping("/user/signup")
  public ResponseEntity<JwtResponse> signUpHandler(@RequestBody SignupRequest req) throws Exception {
    Authentication authentication = authService.signUp(req);
    return JwtUtils.response(authentication, req.getEmail(), "Account created successfully");
  }

  @PostMapping("/login")
  public ResponseEntity<JwtResponse> loginHandler(@RequestBody LoginRequest req) {
    Authentication authentication = authService.login(req);
    return JwtUtils.response(authentication, req.getEmail(), "Login Successfully");
  }

  @PostMapping("/driver/signup")
  public ResponseEntity<JwtResponse> driverSignUpHandler(@RequestBody DriversSignupRequest req) throws Exception {

    Driver driver = driverRepository.findByEmail(req.getEmail());

    JwtResponse response = new JwtResponse();

    if (driver != null) {
      response.setAuthenticated(false);
      response.setErrorDetails("Email Already exists");
      response.setError(true);
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    Driver createdDriver = driverService.registerDriver(req);
    Authentication authentication = new UsernamePasswordAuthenticationToken(createdDriver.getEmail(),
        createdDriver.getPassword());

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = JwtUtils.generateToken(createdDriver.getEmail());
    response.setJwt(jwt);
    response.setAuthenticated(true);
    response.setError(false);
    response.setErrorDetails(null);
    response.setRole(UserRole.DRIVER);
    response.setMessage("Singup Successfully");

    return new ResponseEntity<JwtResponse>(response, HttpStatus.ACCEPTED);
  }

}
