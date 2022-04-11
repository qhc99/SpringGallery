package org.qhc.sutureBeast;

import org.qhc.sutureBeast.uploadingFiles.StorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
@EnableAsync
public class SutureBeastApplication {
  private final Logger LOGGER = LoggerFactory.getLogger(SutureBeastApplication.class);
  public static void main(String[] args) {
    SpringApplication.run(SutureBeastApplication.class, args);
  }

  @Scheduled(fixedRate = 60000)
  public void schedulePrint() {
    LOGGER.info("Session alive.");
  }

  @Bean
  public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(2);
    executor.setMaxPoolSize(4);
    executor.setQueueCapacity(500);
    executor.initialize();
    return executor;
  }

}
