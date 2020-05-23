package me.inonecloud.clouds.dto.yandex;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YandexUser {
    @JsonProperty(value = "country")
    private String country;
    @JsonProperty(value = "login")
    private String login;
    @JsonProperty(value = "display_name")
    private String displayName;
    @JsonProperty(value = "uid")
    private String uid;

    public YandexUser() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
