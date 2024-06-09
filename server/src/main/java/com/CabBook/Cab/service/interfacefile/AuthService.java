package com.CabBook.cab.service.interfacefile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.CabBook.cab.request.LoginRequest;
import com.CabBook.cab.request.SignupRequest;
import com.CabBook.cab.response.JwtResponse;

public interface AuthService {

  public ResponseEntity<JwtResponse> signUp(@RequestBody SignupRequest req) throws Exception;

  public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest req);
}
