package me.inonecloud.clouds.dto.dropbox;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileLockInfo {
    @JsonProperty("is_lockholder")
    private Boolean isLockholder;

    @JsonProperty("lockholder_name")
    private String lockholderName;

    @JsonProperty("created")
    private String create;

    public Boolean getLockholder() {
        return isLockholder;
    }

    public void setLockholder(Boolean lockholder) {
        isLockholder = lockholder;
    }

    public String getLockholderName() {
        return lockholderName;
    }

    public void setLockholderName(String lockholderName) {
        this.lockholderName = lockholderName;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }
}
