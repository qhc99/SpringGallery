package org.qhc.sutureBeast;

import org.qhc.sutureBeast.uploadingFiles.StorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
public class SutureBeastApplication {
  private final Logger LOGGER = LoggerFactory.getLogger(SutureBeastApplication.class);
  public static void main(String[] args) {
    SpringApplication.run(SutureBeastApplication.class, args);
  }

  @Scheduled(fixedRate = 60000)
  public void schedulePrint() {
    LOGGER.info("Session alive.");
  }

}
