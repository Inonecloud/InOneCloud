package me.inonecloud.clouds.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Disk {
    @JsonProperty(value = "unlimited_autoupload_enabled")
    private Boolean unlimitedAutouploadEnable;

    @JsonProperty(value = "max_file_size")
    private Integer maxFileSize;

    @JsonProperty(value = "total_space")
    private Integer totalSpace;

    @JsonProperty(value = "trash_size")
    private Integer trashSize;

    @JsonProperty(value = "is_paid")
    private Boolean isPaid;

    @JsonProperty(value = "used_space")
    private Integer usedSpace;

    @JsonProperty(value = "system_folders")
    private SystemFolders systemFolders;

    @JsonProperty(value = "user")
    private YandexUser user;

    @JsonProperty(value = "revision")
    private Integer revision;
}
