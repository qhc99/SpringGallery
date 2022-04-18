package org.qhc.sutureBeast.reactDemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReactTableController {
  public static final String url = "react_table";

  @RequestMapping(value = "/" + url)
  public String showApp() {
    return "reactDemo";
  }
}