package org.qhc.sutureBeast.reactApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReactController {
  @RequestMapping(value = "/react")
  public String showApp(){
    return "index";
  }
}
