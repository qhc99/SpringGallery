package org.qhc.suturebeast;

import org.qhc.suturebeast.uploadingfiles.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
public class SutureBeastApplication {
  public static void main(String[] args) {
    SpringApplication.run(SutureBeastApplication.class, args);
  }


}
