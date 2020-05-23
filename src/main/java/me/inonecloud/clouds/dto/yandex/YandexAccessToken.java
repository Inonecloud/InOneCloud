package me.inonecloud.clouds.dto.yandex;


import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("Yandex OAuth token")
public class YandexAccessToken {

    @JsonProperty(value = "token_type")
    @JsonPropertyDescription("Type of token. Always is bearer")
    private String tokenType;

    @JsonProperty(value = "access_token")
    @JsonPropertyDescription("OAuth token with selected rights")
    private String accessToken;

    @JsonProperty(value = "expires_in")
    @JsonPropertyDescription("Life time of token in seconds")
    private Long expiresIn;

    @JsonProperty(value = "refresh_token")
    @JsonPropertyDescription("Token witch can use for refresh OAuth token")
    private String refreshToken;

    @JsonProperty(value = "error_description")
    @JsonPropertyDescription("Error description")
    private String errorDescription;

    @JsonProperty(value = "error")
    @JsonPropertyDescription("Error code")
    private String error;

    public YandexAccessToken(String tokenType, String accessToken, Long expiresIn, String refreshToken) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
    }

    public YandexAccessToken(String errorDescription, String error) {
        this.errorDescription = errorDescription;
        this.error = error;
    }

    public YandexAccessToken() {
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
