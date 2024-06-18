package com.pratham.cabserver.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pratham.cabserver.models.Driver;
import com.pratham.cabserver.models.User;
import com.pratham.cabserver.repository.DriverRepository;
import com.pratham.cabserver.repository.UserRepository;

@Service
public class CustomUserServiceImpl implements UserDetailsService {

  @Autowired
  private DriverRepository driverRepository;
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    List<GrantedAuthority> authorities = new ArrayList<>();
    User user = userRepository.findByEmail(email);
    if (user != null) {
      return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
    Driver driver = driverRepository.findByEmail(email);
  }

}
