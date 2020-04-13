package me.inonecloud.clouds.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YandexAboutDisk {
    @JsonProperty(value = "unlimited_autoupload_enabled")
    private Boolean unlimitedAutouploadEnable;

    @JsonProperty(value = "max_file_size")
    private Long maxFileSize;

    @JsonProperty(value = "total_space")
    private Long totalSpace;

    @JsonProperty(value = "trash_size")
    private Long trashSize;

    @JsonProperty(value = "is_paid")
    private Boolean isPaid;

    @JsonProperty(value = "used_space")
    private Long usedSpace;

    @JsonProperty(value = "system_folders")
    private SystemFolders systemFolders;

    @JsonProperty(value = "user")
    private YandexUser user;

    @JsonProperty(value = "revision")
    private Long revision;

    public YandexAboutDisk() {
    }

    public Boolean getUnlimitedAutouploadEnable() {
        return unlimitedAutouploadEnable;
    }

    public void setUnlimitedAutouploadEnable(Boolean unlimitedAutouploadEnable) {
        this.unlimitedAutouploadEnable = unlimitedAutouploadEnable;
    }

    public Long getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(Long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public Long getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(Long totalSpace) {
        this.totalSpace = totalSpace;
    }

    public Long getTrashSize() {
        return trashSize;
    }

    public void setTrashSize(Long trashSize) {
        this.trashSize = trashSize;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Long getUsedSpace() {
        return usedSpace;
    }

    public void setUsedSpace(Long usedSpace) {
        this.usedSpace = usedSpace;
    }

    public SystemFolders getSystemFolders() {
        return systemFolders;
    }

    public void setSystemFolders(SystemFolders systemFolders) {
        this.systemFolders = systemFolders;
    }

    public YandexUser getUser() {
        return user;
    }

    public void setUser(YandexUser user) {
        this.user = user;
    }

    public Long getRevision() {
        return revision;
    }

    public void setRevision(Long revision) {
        this.revision = revision;
    }
}
