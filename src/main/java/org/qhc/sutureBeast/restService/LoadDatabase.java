package org.qhc.sutureBeast.restService;

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
      log.info("Preloading " + repository.save(new RestEmployee("Bilbo Baggins", "burglar")));
      log.info("Preloading " + repository.save(new RestEmployee("Frodo Baggins", "thief")));
    };
  }
}
