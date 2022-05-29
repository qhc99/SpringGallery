package qhc.gallery.demos.tic_toc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/"+TicTocController.url)
public class TicTocController {
  public static final String url = "tic-toc";

  @GetMapping
  public String gamePage(){
    return "tic-toc-game";
  }
}
