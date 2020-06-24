package me.inonecloud.clouds.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleFile {
    @JsonProperty
    private String kind;

    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String mimeType;

    @JsonProperty
    private Boolean starred;

    @JsonProperty
    private Boolean trashed;

    @JsonProperty
    private String webViewLink;

    @JsonProperty
    private String iconLink;

    @JsonProperty
    private String createdTime;

    @JsonProperty
    private String modifiedTime;

    @JsonProperty
    private Boolean shared;

    @JsonProperty
    private String originalFileName;

    @JsonProperty
    private String fullFileExtension;

    @JsonProperty
    private String size;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Boolean getStarred() {
        return starred;
    }

    public void setStarred(Boolean starred) {
        this.starred = starred;
    }

    public Boolean getTrashed() {
        return trashed;
    }

    public void setTrashed(Boolean trashed) {
        this.trashed = trashed;
    }

    public String getWebViewLink() {
        return webViewLink;
    }

    public void setWebViewLink(String webViewLink) {
        this.webViewLink = webViewLink;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getFullFileExtension() {
        return fullFileExtension;
    }

    public void setFullFileExtension(String fullFileExtension) {
        this.fullFileExtension = fullFileExtension;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
