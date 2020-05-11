package me.inonecloud.clouds.dto.google;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("The Google Drive user")
public class DriveUser {
    @JsonProperty(value = "displayName")
    @JsonPropertyDescription("A plain text displayable name for this user")
    private String displayName;

    @JsonProperty(value = "photoLink")
    @JsonPropertyDescription("A link to the user's profile photo, if available.")
    private String photoLink;

    @JsonProperty(value = "me")
    @JsonPropertyDescription("Whether this user is the requesting user")
    private Boolean me;

    @JsonProperty(value = "permissionId")
    @JsonPropertyDescription("The email address of the user")
    private String permissionId;

    @JsonProperty(value = "emailAddress")
    @JsonPropertyDescription("The email address of the user")
    private String emailAddress;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public Boolean getMe() {
        return me;
    }

    public void setMe(Boolean me) {
        this.me = me;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
