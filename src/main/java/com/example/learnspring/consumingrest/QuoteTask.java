package com.example.learnspring.consumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
public class QuoteTask {

  public static final Logger LOGGER = LoggerFactory.getLogger(QuoteTask.class);

  RestTemplate restT = new RestTemplateBuilder().build();

  CommandLineRunner runner = args -> {
    Quote quote = null;
    try {
      quote = restT.getForObject(
              "https://quoters.apps.pcfone.io/api/random", Quote.class);
    }
    catch (ResourceAccessException e){
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
  public CommandLineRunner runQuote(RestTemplate restTemplate) {
    return runner;
  }


  @Scheduled(fixedRate = 10000)
  public void scheduledQuotes() {
    try {
      runner.run((String[]) null);
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
    }
  }
}
