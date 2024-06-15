package com.CabBook.cab.config;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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

    String jwt = request.getHeader(JwtConstatnts.JWT_HEADER);
    jwt = jwt.substring(7);
    try {
      if (jwt != null) {
        Claims claims = Jwts.parserBuilder().setSigningKey(JwtConstatnts.key).build().parseClaimsJwt(jwt).getBody();
        String email = String.valueOf(claims.get("username"));
        String authorities = (String) claims.get("authorities");
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
        Authentication auth = new UsernamePasswordAuthenticationToken(authorities, null, auths);
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    } catch (Exception e) {
      throw new BadCredentialsException("Invalid token ");
    }
    filterChain.doFilter(request, response);
  }

}
