package qhc.gallery.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubUserInfo(String login) {
}
