package org.qhc.suturebeast.uploadingfiles;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UploadTask {
  @Bean
  CommandLineRunner storageDemoInit(StorageService storageService) {
    return (args) -> {
      storageService.deleteAll();
      storageService.init();
    };
  }
}
