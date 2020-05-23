package me.inonecloud.clouds.dto.yandex;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("Item of file in Yandex Disk")
public class Item {
    @JsonProperty(value = "name")
    @JsonPropertyDescription("Name of file")
    private String name;

    @JsonProperty(value = "preview")
    @JsonPropertyDescription("Link to preview picture")
    private String preview;

    @JsonProperty(value = "created")
    @JsonPropertyDescription("Date and time of file creation ISO 8601")
    private String created;

    @JsonProperty(value = "modified")
    @JsonPropertyDescription("Date and time of file modification ISO 8601")
    private String modified;

    @JsonProperty(value = "path")
    @JsonPropertyDescription("Full path to file from Yandex Disk")
    private String path;

    @JsonProperty(value = "md5")
    @JsonPropertyDescription("Hash of file. Md5 function")
    private String md5;

    @JsonProperty(value = "mime_type")
    @JsonPropertyDescription("MIME type of file")
    private String mimeType;

    @JsonProperty(value = "size")
    @JsonPropertyDescription("Size of file in bytes")
    private Long size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
