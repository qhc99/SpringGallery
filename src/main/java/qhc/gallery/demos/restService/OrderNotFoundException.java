package qhc.gallery.demos.restService;

public class OrderNotFoundException extends RuntimeException{
  public OrderNotFoundException(Long id){
    super("Could not find order " + id);
  }
}
