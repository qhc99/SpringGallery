package org.qhc.sutureBeast.demos.relationalDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DBTask {

  public static final Logger LOGGER = LoggerFactory.getLogger(DBTask.class);

  final JdbcTemplate jdbcTemplate;

  @Autowired
  public DBTask(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  @SuppressWarnings("SqlNoDataSourceInspection")
  @Bean
  public CommandLineRunner runDB() {
    return args -> {
      LOGGER.info("-------Creating tables-------");

      jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
      jdbcTemplate.execute("CREATE TABLE customers(" +
              "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

      List<Object[]> splitUpNames = Stream.of("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")
              .map(name -> name.split(" "))
              .collect(Collectors.toList());

      splitUpNames.forEach(name -> LOGGER.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

      jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

      LOGGER.info("Querying for customer records where first_name = 'Josh':");
      jdbcTemplate.query(
              "SELECT id, first_name, last_name FROM customers WHERE first_name = ?",
              (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")),
              new Object[]{"Josh"}
      ).forEach(customer -> LOGGER.info(customer.toString()));
    };
  }
}
