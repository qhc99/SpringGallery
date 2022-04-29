package org.qhc.sutureBeast.demos.restService;

public class OrderNotFoundException extends RuntimeException{
  public OrderNotFoundException(Long id){
    super("Could not find order " + id);
  }
}
