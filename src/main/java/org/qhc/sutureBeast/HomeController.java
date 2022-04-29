package org.qhc.sutureBeast;


import org.qhc.sutureBeast.demos.VueDemo.VueDemoController;
import org.qhc.sutureBeast.demos.reactDemo.ReactTableController;
import org.qhc.sutureBeast.demos.restService.RestEmployeeController;
import org.qhc.sutureBeast.demos.restService.GreetingController;
import org.qhc.sutureBeast.demos.restService.OrderController;
import org.qhc.sutureBeast.demos.uploadingFiles.FileUploadController;
import org.qhc.sutureBeast.demos.validatingFormInput.ValidateFormController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("app_urls",
            List.of(new String[]{
                    ReactTableController.url,
                    RestEmployeeController.url,
                    GreetingController.url,
                    FileUploadController.url,
                    ValidateFormController.url,
                    VueDemoController.url,
                    OrderController.url
            }));
    return "home";
  }
}
