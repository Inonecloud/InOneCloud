package me.inonecloud.clouds.dto.dropbox;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.List;

@JsonClassDescription("The file and (direct) subfolder in the folder.")
public class Entries {
    @JsonProperty(".tag")
    @JsonPropertyDescription("Type of entry")
    private String tag;

    @JsonProperty("name")
    @JsonPropertyDescription("Name of entity")
    private String name;

    @JsonProperty("id")
    @JsonPropertyDescription("Entities' id")
    private String id;

    @JsonProperty("client_modified")
    @JsonPropertyDescription("The last time the file was modified on Dropbox")
    private String clientModified;

    @JsonProperty("server_modified")
    @JsonPropertyDescription("Date of file modification by server")
    private String serverModified;

    @JsonProperty("rev")
    @JsonPropertyDescription("A unique identifier for the current revision of a file")
    private String rev;

    @JsonProperty("size")
    @JsonPropertyDescription("The file size in bytes")
    private String size;

    @JsonProperty("path_lower")
    @JsonPropertyDescription("The lowercased full path in the user's Dropbox")
    private String pathLower;

    @JsonProperty("path_display")
    private String pathDisplay;

    @JsonProperty("sharing_info")
    private SharingInfo sharingInfo;

    @JsonProperty("ia_downloadable")
    @JsonPropertyDescription("Sign of file downloadable")
    private Boolean isDownloadable;

    @JsonProperty("property_groups")
    private List<PropertyGroups> propertyGroups;

    @JsonProperty("has_explicit_shared_member")
    @JsonPropertyDescription("This flag will only be present if include_has_explicit_shared_members is true in list_folder or get_metadata")
    private Boolean hasExplicitSharedMember;

    @JsonProperty("file_lock_info")
    @JsonPropertyDescription("If present, the metadata associated with the file's current lock")
    private FileLockInfo fileLockInfo;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public SharingInfo getSharingInfo() {
        return sharingInfo;
    }

    public void setSharingInfo(SharingInfo sharingInfo) {
        this.sharingInfo = sharingInfo;
    }

    public Boolean getDownloadable() {
        return isDownloadable;
    }

    public void setDownloadable(Boolean downloadable) {
        isDownloadable = downloadable;
    }

    public List<PropertyGroups> getPropertyGroups() {
        return propertyGroups;
    }

    public void setPropertyGroups(List<PropertyGroups> propertyGroups) {
        this.propertyGroups = propertyGroups;
    }

    public Boolean getHasExplicitSharedMember() {
        return hasExplicitSharedMember;
    }

    public void setHasExplicitSharedMember(Boolean hasExplicitSharedMember) {
        this.hasExplicitSharedMember = hasExplicitSharedMember;
    }

    public FileLockInfo getFileLockInfo() {
        return fileLockInfo;
    }

    public void setFileLockInfo(FileLockInfo fileLockInfo) {
        this.fileLockInfo = fileLockInfo;
    }
}
