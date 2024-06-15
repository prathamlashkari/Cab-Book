package com.CabBook.cab.service.classfile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.CabBook.cab.models.Driver;
import com.CabBook.cab.models.User;
import com.CabBook.cab.repository.DriverRepository;
import com.CabBook.cab.repository.UserRepository;

@Service
public class CustomUserDetails implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private DriverRepository driverRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    List<GrantedAuthority> authorities = new ArrayList();
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