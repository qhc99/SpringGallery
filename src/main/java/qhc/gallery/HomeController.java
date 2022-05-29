package qhc.gallery;


import qhc.gallery.demos.VueDemo.VueDemoController;
import qhc.gallery.demos.reactDemo.ReactTableController;
import qhc.gallery.demos.restService.GreetingController;
import qhc.gallery.demos.restService.OrderController;
import qhc.gallery.demos.restService.RestEmployeeController;
import qhc.gallery.demos.uploadingFiles.FileUploadController;
import qhc.gallery.demos.validatingFormInput.ValidateFormController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

  public static final String[] allUrls = new String[]{
          ReactTableController.url,
          RestEmployeeController.url,
          GreetingController.url,
          FileUploadController.url,
          ValidateFormController.url,
          VueDemoController.url,
          OrderController.url
  };

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("app_urls",
            List.of(allUrls));
    return "home";
  }
}
