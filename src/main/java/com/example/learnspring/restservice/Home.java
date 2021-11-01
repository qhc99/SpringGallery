package com.example.learnspring.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
  @GetMapping("/")
  public String showHome(){
    return "<h1>Dummy Home page.<h1/>";
  }
}
