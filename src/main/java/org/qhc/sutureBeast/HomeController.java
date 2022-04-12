package org.qhc.sutureBeast;

import org.qhc.sutureBeast.reactTable.Employee;
import org.qhc.sutureBeast.reactTable.ReactTableController;
import org.qhc.sutureBeast.restService.EmployeeController;
import org.qhc.sutureBeast.restService.Greeting;
import org.qhc.sutureBeast.restService.GreetingController;
import org.qhc.sutureBeast.uploadingFiles.FileUploadController;
import org.qhc.sutureBeast.validatingFormInput.ValidateFormController;
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
                    EmployeeController.url,
                    GreetingController.url,
                    FileUploadController.url,
                    ValidateFormController.url
            }));
    return "home";
  }
}
