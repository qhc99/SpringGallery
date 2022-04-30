package org.qhc.sutureBeast.demos.reactDemo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * The name of api depends on the name of this class!!
 */
@Data
@Entity // <1>
public class ReactEmployee {

  private
  @Id
  @GeneratedValue
  Long id; // <2>
  private String firstName;
  private String lastName;
  private String description;


  public ReactEmployee(String firstName, String lastName, String description) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.description = description;
  }

  public ReactEmployee() {

  }
}
