package org.qhc.sutureBeast.demos.restService;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Data
class RestEmployee {

  private @Id
  @GeneratedValue Long id;
  private String firstName;
  private String lastName;
  private String role;

  RestEmployee(String firstName, String lastName, String role) {

    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
  }

  public RestEmployee() {

  }

  public String getName() {
    return this.firstName + " " + this.lastName;
  }

  public void setName(String name) {
    String[] parts = name.split(" ");
    this.firstName = parts[0];
    this.lastName = parts[1];
  }
}
