package com.example.learnspring;

import com.example.learnspring.consumingrest.Quote;
import com.example.learnspring.messagingredis.Receiver;
import com.example.learnspring.relationaldataaccess.Customer;
import com.example.learnspring.uploadingfiles.StorageProperties;
import com.example.learnspring.uploadingfiles.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
public class SutureBeastApplication{

  private static final Logger LOGGER = LoggerFactory.getLogger(SutureBeastApplication.class);

  public SutureBeastApplication(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

  public static void main(String[] args) {

    ApplicationContext ctx = SpringApplication.run(SutureBeastApplication.class, args);

    StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
    Receiver receiver = ctx.getBean(Receiver.class);

    while (receiver.getCount() == 0) {

      LOGGER.info("Sending message...");
      template.convertAndSend("chat", "Hello from Redis!");
      try{
        //noinspection BusyWait
        Thread.sleep(100L);
      }
      catch(InterruptedException e){
        throw new RuntimeException(e);
      }
    }
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  final JdbcTemplate jdbcTemplate;

  @SuppressWarnings("SqlNoDataSourceInspection")
  @Bean
  public CommandLineRunner runDB(){
    return args -> {
      LOGGER.info("-=Creating tables=-");

      jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
      jdbcTemplate.execute("CREATE TABLE customers(" +
              "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

      // Split up the array of whole names into an array of first/last names
      List<Object[]> splitUpNames = Stream.of("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")
              .map(name -> name.split(" "))
              .collect(Collectors.toList());

      // Use a Java 8 stream to print out each tuple of the list
      splitUpNames.forEach(name -> LOGGER.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

      // Uses JdbcTemplate's batchUpdate operation to bulk load data
      jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

      LOGGER.info("Querying for customer records where first_name = 'Josh':");
      jdbcTemplate.query(
              "SELECT id, first_name, last_name FROM customers WHERE first_name = ?",
              (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")),
              new Object[] { "Josh" }
      ).forEach(customer -> LOGGER.info(customer.toString()));
    };
  }

  @Bean
  public CommandLineRunner runQuote(RestTemplate restTemplate) {
    return args -> {
      Quote quote = restTemplate.getForObject(
              "https://quoters.apps.pcfone.io/api/random", Quote.class);
      if(quote != null){
        LOGGER.info(quote.toString());
      }
      else{
        LOGGER.info("quote is null.");
      }
    };
  }

  RestTemplate restT = new RestTemplateBuilder().build();

  @Scheduled(fixedRate = 10000)
  public void scheduledQuotes(){
    Quote quote = restT.getForObject(
            "https://quoters.apps.pcfone.io/api/random", Quote.class);
    if(quote != null){
      LOGGER.info("scheduled quote: " + quote);
    }
    else{
      LOGGER.info("quote is null.");
    }
  }

  @Bean
  CommandLineRunner init(StorageService storageService) {
    return (args) -> {
      storageService.deleteAll();
      storageService.init();
    };
  }

  @Bean
  RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                          MessageListenerAdapter listenerAdapter) {

    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.addMessageListener(listenerAdapter, new PatternTopic("chat"));

    return container;
  }

  @Bean
  MessageListenerAdapter listenerAdapter(Receiver receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }

  @Bean
  Receiver receiver() {
    return new Receiver();
  }

  @Bean
  StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
    return new StringRedisTemplate(connectionFactory);
  }

}
