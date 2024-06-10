package com.CabBook.cab.service.interfacefile;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import com.CabBook.cab.request.LoginRequest;
import com.CabBook.cab.request.SignupRequest;

public interface AuthService {

  public Authentication signUp(@RequestBody SignupRequest req) throws Exception;

  public Authentication login(@RequestBody LoginRequest req);
}
