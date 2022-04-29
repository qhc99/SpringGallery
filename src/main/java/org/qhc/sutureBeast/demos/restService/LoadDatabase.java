package org.qhc.sutureBeast.demos.restService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(RestEmployeeRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new RestEmployee("Bilbo", "Baggins", "burglar")));
      log.info("Preloading " + repository.save(new RestEmployee("Frodo", "Baggins", "thief")));
    };
  }

  @Bean
  CommandLineRunner initDatabase(RestEmployeeRepository employeeRepository, OrderRepository orderRepository) {

    return args -> {
      employeeRepository.save(new RestEmployee("Bilbo", "Baggins", "burglar"));
      employeeRepository.save(new RestEmployee("Frodo", "Baggins", "thief"));

      employeeRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));


      orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
      orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

      orderRepository.findAll().forEach(order -> {
        log.info("Preloaded " + order);
      });

    };
  }
}
