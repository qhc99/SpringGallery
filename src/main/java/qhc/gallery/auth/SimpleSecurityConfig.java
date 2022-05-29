package qhc.gallery.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SimpleSecurityConfig extends WebSecurityConfigurerAdapter {


  private final GithubUsernameRestAuthenticationProvider authProvider;


  @Autowired
  SimpleSecurityConfig(GithubUsernameRestAuthenticationProvider a) {
    authProvider = a;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .formLogin()
            .loginPage("/login")
            .permitAll()

            .and()
            .logout()
            .permitAll()

            .and()
            .authorizeRequests()
            .anyRequest()
            .authenticated()

            .and()
            .requiresChannel()
            .anyRequest()
            .requiresSecure();

  }
}
