package me.inonecloud.clouds.dto.google;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription
public class GoogleSpaceInfo {
    @JsonProperty(value = "storageQuota")
    @JsonPropertyDescription("The user's storage quota limits and usage. All fields are measured in bytes")
    private StorageQuota storageQuota;

    @JsonProperty(value = "user")
    @JsonPropertyDescription("The authenticated user")
    private DriveUser user;

    public StorageQuota getStorageQuota() {
        return storageQuota;
    }

    public void setStorageQuota(StorageQuota storageQuota) {
        this.storageQuota = storageQuota;
    }

    public DriveUser getUser() {
        return user;
    }

    public void setUser(DriveUser user) {
        this.user = user;
    }
}
