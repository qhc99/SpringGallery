package qhc.gallery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  public static final String url = "login";

  @GetMapping("/"+url)
  public String home() {
    return "login";
  }
}
