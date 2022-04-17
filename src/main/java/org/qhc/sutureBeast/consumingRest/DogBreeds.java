package org.qhc.sutureBeast.consumingRest;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DogBreeds {

  private Object message;
  private String status;

  public DogBreeds() {
  }

  public Object getMessage() {
    return message;
  }

  public void setMessage(Object message) {
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "DogBreeds{" +
            "message='" + message + '\'' +
            ", status=" + status +
            '}';
  }
}
