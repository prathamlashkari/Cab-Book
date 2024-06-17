package com.pratham.cabserver.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pratham.cabserver.request.LoginRequest;
import com.pratham.cabserver.request.SignupRequest;
import com.pratham.cabserver.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

  @Override
  public Authentication signUp(SignupRequest req) throws Exception {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'signUp'");
  }

  @Override
  public Authentication login(LoginRequest req) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'login'");
  }

}
