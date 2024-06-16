package com.pratham.cabserver.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import com.pratham.cabserver.request.LoginRequest;
import com.pratham.cabserver.request.SignupRequest;

public interface AuthService {

  public Authentication signUp(@RequestBody SignupRequest req) throws Exception;

  public Authentication login(@RequestBody LoginRequest req);

}
