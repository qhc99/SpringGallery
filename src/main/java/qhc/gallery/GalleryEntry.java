package qhc.gallery;

import qhc.gallery.demos.uploadingFiles.StorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
@EnableWebSecurity
public class GalleryEntry {

  public static final String project_owner_github_name = "qhc99";
  private final Logger LOGGER = LoggerFactory.getLogger(GalleryEntry.class);

  public static void main(String[] args) {
    var app = new SpringApplication(GalleryEntry.class);
    app.run(args);
  }

  @Scheduled(fixedRate = 120000)
  public void schedulePrint() {
    LOGGER.info("Server alive.");
  }

}
