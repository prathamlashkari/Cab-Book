package com.CabBook.cab.service.classfile;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.CabBook.cab.repository.DriverRepository;
import com.CabBook.cab.utils.JwtUtils;

@Service
public class DriverServiceImpl {

    private DriverRepository driverRepository;
    private Calculaters distanceCalculater;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;
    private private Vehi
}
