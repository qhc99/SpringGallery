package org.qhc.sutureBeast.actuator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.web.server.LocalServerPort;
import static org.assertj.core.api.BDDAssertions.then;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port=0"})
public class ActuatorTest {

  @Value("${local.management.port}")
  private int mgt;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  public void shouldReturn200WhenSendingRequestToManagementEndpoint() {
    @SuppressWarnings("rawtypes")
    ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
            "http://localhost:" + this.mgt + "/actuator", Map.class);
    then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

}
