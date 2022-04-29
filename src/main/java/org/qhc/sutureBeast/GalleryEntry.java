package org.qhc.sutureBeast;

import org.qhc.sutureBeast.demos.uploadingFiles.StorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.concurrent.Executor;
// TODO add github auth test
// TODO add blog list of web app
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
@EnableWebSecurity
public class GalleryEntry {

  public static final String project_owner = "qhc99";
  private final Logger LOGGER = LoggerFactory.getLogger(GalleryEntry.class);

  public static void main(String[] args) {
    SpringApplication.run(GalleryEntry.class, args);
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
