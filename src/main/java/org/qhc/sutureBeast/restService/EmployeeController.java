package org.qhc.sutureBeast.restService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class EmployeeController {

  private final RestEmployeeRepository repository;

  EmployeeController(RestEmployeeRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/employees")
  List<RestEmployee> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/employees")
  RestEmployee newEmployee(@RequestBody RestEmployee newEmployee) {
    return repository.save(newEmployee);
  }

  // Single item

  @GetMapping("/employees/{id}")
  RestEmployee one(@PathVariable Long id) {

    return repository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException(id));
  }

  @PutMapping("/employees/{id}")
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

  @DeleteMapping("/employees/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
