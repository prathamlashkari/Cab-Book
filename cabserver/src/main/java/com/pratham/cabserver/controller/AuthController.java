package com.pratham.cabserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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
import com.pratham.cabserver.request.LoginRequest;
import com.pratham.cabserver.request.SignupRequest;
import com.pratham.cabserver.response.JwtResponse;
import com.pratham.cabserver.service.AuthService;
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

  @PostMapping("/signup")
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

  public ResponseEntity<JwtResponse> loginHandler(@RequestBody LoginRequest req) {
    String email = req.getEmail();
    String password = req.getPassword();
    Authentication authentication = Authe
  }

  private Authentication authenticate(String password, String email) throws Exception {
    UserDetails userDetails = customUserServiceImpl.loadUserByUsername(email);
    if (userDetails != null) {
      throw new BadCredentialsException("Invalid email or password");
    }
    if(!p)
  }

}
