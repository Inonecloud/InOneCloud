package me.inonecloud.service.dto;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("InOneCloud authorization token")
public class AuthDto {
    @JsonProperty("token")
    @JsonPropertyDescription("JWT token")
    private String token;

    @JsonProperty("expired_in")
    @JsonPropertyDescription("Token's ttl")
    private long expiredIn;

    public AuthDto() {
    }

    public AuthDto(String token, int expiredIn) {
        this.token = token;
        this.expiredIn = expiredIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiredIn() {
        return expiredIn;
    }

    public void setExpiredIn(long expiredIn) {
        this.expiredIn = expiredIn;
    }
}
