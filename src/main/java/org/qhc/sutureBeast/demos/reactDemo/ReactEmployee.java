package org.qhc.sutureBeast.demos.reactDemo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * The name of api depends on the name of this class!!
 */
@Entity // <1>
public class ReactEmployee {

  private
  @Id
  @GeneratedValue
  Long id; // <2>
  private String firstName;
  private String lastName;
  private String description;

  public ReactEmployee() {
  }

  public ReactEmployee(String firstName, String lastName, String description) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ReactEmployee reactEmployee = (ReactEmployee) o;
    return Objects.equals(id, reactEmployee.id) &&
            Objects.equals(firstName, reactEmployee.firstName) &&
            Objects.equals(lastName, reactEmployee.lastName) &&
            Objects.equals(description, reactEmployee.description);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, firstName, lastName, description);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "ReactEmployee{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", description='" + description + '\'' +
            '}';
  }
}
