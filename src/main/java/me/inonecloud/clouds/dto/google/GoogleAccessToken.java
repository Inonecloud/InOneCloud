package me.inonecloud.clouds.dto.google;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("Google OAuth token")
public class GoogleAccessToken {
    @JsonProperty(value = "access_token")
    @JsonPropertyDescription("The token that your application sends to authorize a Google API request")
    private String accessToken;

    @JsonProperty(value = "expires_in")
    @JsonPropertyDescription("The remaining lifetime of the access token in seconds.")
    private Long expiredIn;

    @JsonProperty(value = "token_type")
    @JsonPropertyDescription("The type of token returned. At this time, this field's value is always set to Bearer")
    private String tokenType;

    @JsonProperty(value = "scope")
    @JsonPropertyDescription("The scopes of access granted by the access_token expressed as a list of space-delimited, case-sensitive strings")
    private String scope;

    @JsonProperty(value = "refresh_token")
    @JsonPropertyDescription("A token that you can use to obtain a new access token")
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiredIn() {
        return expiredIn;
    }

    public void setExpiredIn(Long expiredIn) {
        this.expiredIn = expiredIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
