package com.CabBook.Cab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.CabBook.Cab.models.Driver;
import com.CabBook.Cab.models.User;
import com.CabBook.Cab.repository.DriverRepository;
import com.CabBook.Cab.repository.UserRepository;

@Service
public class UserDetailSerivceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private DriverRepository driverRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    List<GrantedAuthority> authorities = new ArrayList<>();
    User user = userRepository.findByEmail(email);
    if (user != null) {
      return new org.springframework.security.core.userdetails.User(user.getFullName(), user.getPassword(),
          authorities);
    }
    Driver driver = driverRepository.findByEmail(email);
    if (driver != null) {
      return new org.springframework.security.core.userdetails.User(driver.getFullName(), driver.getPassword(),
          authorities);
    }
    throw new UsernameNotFoundException("User not found");
  }
}
