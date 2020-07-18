package me.inonecloud.service.dto;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("Password change")
public class PasswordChangeDto {

    @JsonProperty("current_password")
    @JsonPropertyDescription("Current user's password")
    private String currentPassword;

    @JsonProperty("new_password")
    @JsonPropertyDescription("New user's password")
    private String newPassword;

    public PasswordChangeDto() {
    }

    public PasswordChangeDto(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
