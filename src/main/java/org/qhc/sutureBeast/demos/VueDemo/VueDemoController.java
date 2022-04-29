package org.qhc.sutureBeast.demos.VueDemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VueDemoController {

  public static final String url = "vue_demo";

  @GetMapping("/" + url)
  public String index(Model model) {
    // this attribute will be available in the view reactDemo.html as a thymeleaf variable
    model.addAttribute("eventName", "FIFA 2018");
    // this just means render reactDemo.html from static/ area
    return "vueDemo";
  }
}
