package org.qhc.sutureBeast.gallery.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubUserInfo {
  String login;

  public GithubUserInfo(){

  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  @Override
  public String toString() {
    return "GithubUserInfo{" +
            "login='" + login + '\'' +
            '}';
  }
}
