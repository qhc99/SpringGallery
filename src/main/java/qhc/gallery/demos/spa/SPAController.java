package qhc.gallery.demos.spa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SPAController {
  public static final String url = "SPA";

  @GetMapping(value = "/" + url + "/**")
  public String gamePage() {
    return "SPA";
  }
}
