package qhc.gallery.demos.consumingRest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


@Component
public class DogApiTask {

  public static final Logger LOGGER = LoggerFactory.getLogger(DogApiTask.class);

  RestTemplate restT = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(1000)).build();

  CommandLineRunner runner = args -> {
    DogBreeds quote = null;
    try {
      LOGGER.info("----------Consuming Dog Breeds API----------");
      quote = restT.getForObject(
              "https://dog.ceo/api/breeds/list/all", DogBreeds.class);
    } catch (ResourceAccessException e) {
      LOGGER.warn(e.getMessage());
    }
    if (quote != null) {
      LOGGER.info(quote.toString());
    }
    else {
      LOGGER.info("quote is null.");
    }
  };


  @Bean
  public CommandLineRunner runQuote() {
    return runner;
  }
}
