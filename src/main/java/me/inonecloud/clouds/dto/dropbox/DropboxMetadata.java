package me.inonecloud.clouds.dto.dropbox;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("Metadata of the file")
public class DropboxMetadata {
    @JsonProperty("name")
    @JsonPropertyDescription("The last component of the path (including extension). This never contains a slash")
    private String name;

    @JsonProperty("id")
    @JsonPropertyDescription("A unique identifier for the file")
    private String id;

    @JsonProperty("client_modified")
    @JsonPropertyDescription("Timestamp(format=\"%Y-%m-%dT%H:%M:%SZ\") For files, this is the modification time set by the desktop client when the file was added to Dropbox")
    private String clientModified;

    @JsonProperty("server_modified")
    @JsonPropertyDescription("Timestamp(format=\"%Y-%m-%dT%H:%M:%SZ\") The last time the file was modified on Dropbox")
    private String serverModified;

    @JsonProperty("rev")
    @JsonPropertyDescription("A unique identifier for the current revision of a file")
    private String rev;

    @JsonProperty("size")
    @JsonPropertyDescription("The file size in bytes")
    private Long size;

    @JsonProperty("sharing_info")
    @JsonPropertyDescription("")
    private SharingInfo sharingInfo;

    @JsonProperty("path_lower")
    @JsonPropertyDescription("The lowercased full path in the user's Dropbox. This always starts with a slash. This field will be null if the file or folder is not mounted")
    private String pathLower;

    @JsonProperty("path_display")
    @JsonPropertyDescription(" The cased path to be used for display purposes only")
    private String pathDisplay;

    @JsonProperty("is_downloadable")
    @JsonPropertyDescription("If true, file can be downloaded directly; else the file must be exported")
    private Boolean isDownloadable;

    @JsonProperty("content_hash")
    @JsonPropertyDescription("A hash of the file content. This field can be used to verify data integrity")
    private String contentHash;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientModified() {
        return clientModified;
    }

    public void setClientModified(String clientModified) {
        this.clientModified = clientModified;
    }

    public String getServerModified() {
        return serverModified;
    }

    public void setServerModified(String serverModified) {
        this.serverModified = serverModified;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public SharingInfo getSharingInfo() {
        return sharingInfo;
    }

    public void setSharingInfo(SharingInfo sharingInfo) {
        this.sharingInfo = sharingInfo;
    }

    public String getPathLower() {
        return pathLower;
    }

    public void setPathLower(String pathLower) {
        this.pathLower = pathLower;
    }

    public String getPathDisplay() {
        return pathDisplay;
    }

    public void setPathDisplay(String pathDisplay) {
        this.pathDisplay = pathDisplay;
    }

    public Boolean getDownloadable() {
        return isDownloadable;
    }

    public void setDownloadable(Boolean downloadable) {
        isDownloadable = downloadable;
    }

    public String getContentHash() {
        return contentHash;
    }

    public void setContentHash(String contentHash) {
        this.contentHash = contentHash;
    }
}
