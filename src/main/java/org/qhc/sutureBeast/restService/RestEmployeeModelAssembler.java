package org.qhc.sutureBeast.restService;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RestEmployeeModelAssembler implements RepresentationModelAssembler<RestEmployee, EntityModel<RestEmployee>> {

  @Override
  public EntityModel<RestEmployee> toModel(RestEmployee employee) {

    return EntityModel.of(employee, //
            linkTo(methodOn(RestEmployeeController.class).one(employee.getId())).withSelfRel(),
            linkTo(methodOn(RestEmployeeController.class).all()).withRel("RestEmployees"));
  }
}