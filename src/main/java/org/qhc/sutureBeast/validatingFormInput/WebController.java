package org.qhc.sutureBeast.validatingFormInput;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Controller
public class WebController implements WebMvcConfigurer {

  public static final String url = "validation";

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/validRes").setViewName("validRes");
  }

  @GetMapping("/" + url)
  public String showForm(PersonForm personForm) {
    return "validateForm";
  }

  @PostMapping("/" + url)
  public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "validateForm";
    }

    return "redirect:/validRes";
  }
}
