package me.inonecloud.service.dto;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Information about cloud available storage")
public class CloudsInfoDto {
    @JsonProperty(value = "yandex_disk_info")
    @JsonPropertyDescription("Info about Yandex Disk")
    private CloudInfoDto yandexDiskInfo;

    @JsonProperty(value = "dropbox_info")
    @JsonPropertyDescription("Info about DropBox")
    private CloudInfoDto dropboxInfo;

    @JsonProperty(value = "google_drive_info")
    @JsonPropertyDescription("Info about Google Drive")
    private CloudInfoDto googeDriveInfo;

    public CloudsInfoDto() {
    }

    public CloudInfoDto getYandexDiskInfo() {
        return yandexDiskInfo;
    }

    public void setYandexDiskInfo(CloudInfoDto yandexDiskInfo) {
        this.yandexDiskInfo = yandexDiskInfo;
    }

    public CloudInfoDto getDropboxInfo() {
        return dropboxInfo;
    }

    public void setDropboxInfo(CloudInfoDto dropboxInfo) {
        this.dropboxInfo = dropboxInfo;
    }

    public CloudInfoDto getGoogeDriveInfo() {
        return googeDriveInfo;
    }

    public void setGoogeDriveInfo(CloudInfoDto googeDriveInfo) {
        this.googeDriveInfo = googeDriveInfo;
    }
}
