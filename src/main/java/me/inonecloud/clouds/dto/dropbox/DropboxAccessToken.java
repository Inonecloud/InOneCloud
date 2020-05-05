package me.inonecloud.clouds.dto.dropbox;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("Dropbox OAuth token")
public class DropboxAccessToken {
    @JsonProperty(value = "access_token")
    @JsonPropertyDescription("OAuth token with selected rights")
    private String accessToken;

    @JsonProperty(value = "token_type")
    @JsonPropertyDescription("Type of token. Always is bearer")
    private String tokenType;

    @JsonProperty(value = "account_id")
    @JsonPropertyDescription("Dropbox account id")
    private String accountId;

    @JsonProperty(value = "uid")
    private String uid;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}