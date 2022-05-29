package qhc.gallery.demos.restService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RestEmployeeController {

  public static final String url = "RestEmployees";

  private final RestEmployeeRepository repository;

  private final RestEmployeeModelAssembler assembler;

  RestEmployeeController(RestEmployeeRepository repository, RestEmployeeModelAssembler assembler) {

    this.repository = repository;
    this.assembler = assembler;
  }


  @GetMapping("/" + url)
  CollectionModel<EntityModel<RestEmployee>> all() {

    List<EntityModel<RestEmployee>> employees = repository.findAll().stream() //
            .map(assembler::toModel) //
            .collect(Collectors.toList());

    return CollectionModel.of(employees, linkTo(methodOn(RestEmployeeController.class).all()).withSelfRel());
  }

  @PostMapping("/" + url)
  ResponseEntity<?> newEmployee(@RequestBody RestEmployee newEmployee) {

    EntityModel<RestEmployee> entityModel = assembler.toModel(repository.save(newEmployee));

    return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
  }

  @GetMapping("/" + url + "/{id}")
  EntityModel<RestEmployee> one(@PathVariable Long id) {

    RestEmployee employee = repository.findById(id) //
            .orElseThrow(() -> new EmployeeNotFoundException(id));

    return assembler.toModel(employee);
  }


  @PutMapping("/" + url + "/{id}")
  ResponseEntity<?> replaceEmployee(@RequestBody RestEmployee newEmployee, @PathVariable Long id) {

    RestEmployee updatedEmployee = repository.findById(id) //
            .map(employee -> {
              employee.setName(newEmployee.getName());
              employee.setRole(newEmployee.getRole());
              return repository.save(employee);
            }) //
            .orElseGet(() -> {
              newEmployee.setId(id);
              return repository.save(newEmployee);
            });

    EntityModel<RestEmployee> entityModel = assembler.toModel(updatedEmployee);

    return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
  }

  @DeleteMapping("/" + url + "/{id}")
  ResponseEntity<?> deleteEmployee(@PathVariable Long id) {

    repository.deleteById(id);

    return ResponseEntity.noContent().build();
  }
}
