package org.qhc.sutureBeast.restService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class EmployeeController {

  public static final String url = "employees";

  private final RestEmployeeRepository repository;

  EmployeeController(RestEmployeeRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/" + url)
  List<RestEmployee> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/" + url)
  RestEmployee newEmployee(@RequestBody RestEmployee newEmployee) {
    return repository.save(newEmployee);
  }

  // Single item

  @GetMapping("/" + url + "/{id}")
  RestEmployee one(@PathVariable Long id) {

    return repository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException(id));
  }

  @PutMapping("/" + url + "/{id}")
  RestEmployee replaceEmployee(@RequestBody RestEmployee newEmployee, @PathVariable Long id) {

    return repository.findById(id)
            .map(employee -> {
              employee.setName(newEmployee.getName());
              employee.setRole(newEmployee.getRole());
              return repository.save(employee);
            })
            .orElseGet(() -> {
              newEmployee.setId(id);
              return repository.save(newEmployee);
            });
  }

  @DeleteMapping("/" + url + "/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
