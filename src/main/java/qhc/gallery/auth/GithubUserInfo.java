package qhc.gallery.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubUserInfo(String login) implements Serializable {
}
