package com.CabBook.cab.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.CabBook.cab.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private UserDetailsService userDetailsService;

  @SuppressWarnings("null")
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    Optional<String> jwtToken = getTokenFromRequest(request);

    jwtToken.ifPresent(jwt -> {
      if (JwtUtils.validateToken(jwt)) {
        Optional<String> emailOpt = JwtUtils.getEmailFromToken(jwt);
        if (emailOpt.isPresent()) {
          String email = emailOpt.get();
          UserDetails userDetails = userDetailsService.loadUserByUsername(email);
          System.out.println(userDetails + "userdetials");
          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());

          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
          authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        }
      }
    });

    filterChain.doFilter(request, response);
  }

  private Optional<String> getTokenFromRequest(HttpServletRequest request) {
    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
      return Optional.of(authHeader.split(" ")[1].trim());
    }
    return Optional.empty();
  }
}
