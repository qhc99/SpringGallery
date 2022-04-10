package org.qhc.sutureBeast.reactApp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component // <1>
public class DatabaseLoader { // <2>

  private final Logger LOGGER = LoggerFactory.getLogger(DatabaseLoader.class);
  private final ReactEmployeeRepository repository;

  @Autowired // <3>
  public DatabaseLoader(ReactEmployeeRepository repository) {
    this.repository = repository;
  }

  @Bean
  public CommandLineRunner addEntry() { // <4>
    return args->{
      LOGGER.info("----------------react entry added---------------.");
      this.repository.save(new ReactEmployee("Frodo", "Baggins", "ring bearer"));
    };
  }
}