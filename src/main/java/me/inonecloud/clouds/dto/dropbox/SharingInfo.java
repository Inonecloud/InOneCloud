package me.inonecloud.clouds.dto.dropbox;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SharingInfo {
    @JsonProperty("read_only")
    private Boolean readOnly;

    @JsonProperty("parent_shared_folder_id")
    private String parentSharedFolderId;

    @JsonProperty("modified_by")
    private String modifiedBy;

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String getParentSharedFolderId() {
        return parentSharedFolderId;
    }

    public void setParentSharedFolderId(String parentSharedFolderId) {
        this.parentSharedFolderId = parentSharedFolderId;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
