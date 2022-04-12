package org.qhc.sutureBeast;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
public class HomeController {
  @GetMapping("/")
  public String home() {
    return "<h1>Welcome<h1/>";
  }
}
