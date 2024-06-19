package com.pratham.cabserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratham.cabserver.response.MessageResponse;

@RestController
public class HomeController {

  @GetMapping("/")
  public ResponseEntity<MessageResponse> homeHandler() {
    MessageResponse res = new MessageResponse("Wecome to backend");
    return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
  }

}
