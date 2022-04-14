package org.qhc.sutureBeast.reactDemo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component // <1>
public class DatabaseLoader { // <2>

  private final Logger LOGGER = LoggerFactory.getLogger(DatabaseLoader.class);
  private final EmployeeRepository repository;

  @Autowired // <3>
  public DatabaseLoader(EmployeeRepository repository) {
    this.repository = repository;
  }

  @Bean
  public CommandLineRunner addEntry() { // <4>
    return args -> this.repository.save(new Employee("Frodo", "Baggins", "ring bearer"));
  }
}