package me.inonecloud.clouds.dto.google;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("The user's storage quota limits and usage")
public class StorageQuota {
    @JsonProperty(value = "limit")
    @JsonPropertyDescription("The usage limit, if applicable")
    private String limit;

    @JsonProperty(value = "usage")
    @JsonPropertyDescription("The total usage across all services")
    private String usage;

    @JsonProperty(value = "usageInDrive")
    @JsonPropertyDescription("The usage by all files in Google Drive")
    private String usageInDrive;

    @JsonProperty(value = "usageInDriveTrash")
    @JsonPropertyDescription("The usage by trashed files in Google Drive")
    private String usageInDriveTrash;

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getUsageInDrive() {
        return usageInDrive;
    }

    public void setUsageInDrive(String usageInDrive) {
        this.usageInDrive = usageInDrive;
    }

    public String getUsageInDriveTrash() {
        return usageInDriveTrash;
    }

    public void setUsageInDriveTrash(String usageInDriveTrash) {
        this.usageInDriveTrash = usageInDriveTrash;
    }
}
