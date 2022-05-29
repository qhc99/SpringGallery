package qhc.gallery.auth;

import qhc.gallery.HomeController;
import qhc.gallery.demos.consumingRest.DogApiTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Arrays;

@Service
public class GithubRestAuthenticationProvider implements AuthenticationProvider {

  public static final Logger LOGGER = LoggerFactory.getLogger(DogApiTask.class);

  RestTemplate restT = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(500)).build();

  @Override
  public Authentication authenticate(Authentication authentication)
          throws AuthenticationException {

    String name = authentication.getName();
    GithubUserInfo github_user_info;
    try {
      github_user_info = restT.getForObject("https://api.github.com/users/" + name, GithubUserInfo.class);
    } catch (ResourceAccessException e) {
      return null;
    }

    if (github_user_info != null && github_user_info.login() != null && github_user_info.login().equals(name)) {
      LOGGER.info("------------------auth success-------------------.");
      return new RememberMeAuthenticationToken(github_user_info.login(), github_user_info,
              Arrays.stream(HomeController.allUrls).map((s) -> (GrantedAuthority) () -> s).toList());
    }
    else {
      return null;
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}