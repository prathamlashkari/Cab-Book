package com.pratham.cabserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratham.cabserver.enums.UserRole;
import com.pratham.cabserver.exceptions.UserException;
import com.pratham.cabserver.models.Driver;
import com.pratham.cabserver.repository.DriverRepository;
import com.pratham.cabserver.request.DriversSignupRequest;
import com.pratham.cabserver.request.LoginRequest;
import com.pratham.cabserver.request.SignupRequest;
import com.pratham.cabserver.response.JwtResponse;
import com.pratham.cabserver.service.AuthService;
import com.pratham.cabserver.service.DriverService;
import com.pratham.cabserver.service.impl.CustomUserServiceImpl;
import com.pratham.cabserver.utils.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthService authService;
  @Autowired
  private JwtUtils jwtUtils;
  @Autowired
  private CustomUserServiceImpl customUserServiceImpl;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private DriverRepository driverRepository;
  @Autowired
  private DriverService driverService;

  @PostMapping("/user/signup")
  public ResponseEntity<JwtResponse> signupHandler(@RequestBody SignupRequest req) throws UserException {

    Authentication authentication = authService.signUp(req);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generaJwtToken(authentication);
    JwtResponse jwtResponse = new JwtResponse();
    jwtResponse.setJwt(jwt);
    jwtResponse.setAuthenticated(true);
    jwtResponse.setErrorDetails(null);
    jwtResponse.setError(false);
    jwtResponse.setMessage("Account created Successfully");
    jwtResponse.setRole(UserRole.USER);

    return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<JwtResponse> loginHandler(@RequestBody LoginRequest req) throws Exception {
    String email = req.getEmail();
    String password = req.getPassword();
    Authentication authentication = authenticate(password, email);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generaJwtToken(authentication);
    JwtResponse jwtResponse = new JwtResponse();
    jwtResponse.setJwt(jwt);
    jwtResponse.setAuthenticated(true);
    jwtResponse.setErrorDetails(null);
    jwtResponse.setError(false);
    jwtResponse.setMessage("Login Successfully");
    jwtResponse.setRole(UserRole.USER);
    return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

  }

  @SuppressWarnings("null")
  private Authentication authenticate(String password, String email) throws Exception {
    UserDetails userDetails = customUserServiceImpl.loadUserByUsername(email);
    if (userDetails == null) {
      throw new BadCredentialsException("Invalid email or password");
    }
    if (!passwordEncoder.matches(password, userDetails.getPassword())) {
      throw new BadCredentialsException("Invalid username or password");
    }
    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
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
    String jwt = jwtUtils.generaJwtToken(authentication);
    response.setJwt(jwt);
    response.setAuthenticated(true);
    response.setError(false);
    response.setErrorDetails(null);
    response.setRole(UserRole.DRIVER);
    response.setMessage("Singup Successfully");

    return new ResponseEntity<JwtResponse>(response, HttpStatus.ACCEPTED);
  }

}
