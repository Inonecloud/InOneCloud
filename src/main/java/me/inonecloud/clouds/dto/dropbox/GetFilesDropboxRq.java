package me.inonecloud.clouds.dto.dropbox;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("Parameters for contents of folders in Dropbox")
public class GetFilesDropboxRq {
    @JsonProperty("path")
    @JsonPropertyDescription("A unique identifier for the file.")
    private String path;

    @JsonProperty("recursive")
    @JsonPropertyDescription("If true, the list folder operation will be applied recursively to all subfolders and the response will contain contents of all subfolders")
    private Boolean recursive;

    @JsonProperty("include_media_info")
    @Deprecated
    private Boolean includeMediaInfo;

    @JsonProperty("include_deleted")
    private Boolean includeDeleted;

    @JsonProperty("include_has_explicit_shared_members")
    private Boolean includeHasExplicitSharedMembers;

    @JsonProperty("include_non_downloadable_files")
    private Boolean includeNonDownloadableFiles;

    public GetFilesDropboxRq() {
    }

    public GetFilesDropboxRq(String path, Boolean recursive) {
        this.path = path;
        this.recursive = recursive;
        this.includeMediaInfo = false;
        this.includeDeleted = false;
        this.includeHasExplicitSharedMembers = false;
        this.includeNonDownloadableFiles = false;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getRecursive() {
        return recursive;
    }

    public void setRecursive(Boolean recursive) {
        this.recursive = recursive;
    }

    public Boolean getIncludeMediaInfo() {
        return includeMediaInfo;
    }

    public void setIncludeMediaInfo(Boolean includeMediaInfo) {
        this.includeMediaInfo = includeMediaInfo;
    }

    public Boolean getIncludeDeleted() {
        return includeDeleted;
    }

    public void setIncludeDeleted(Boolean includeDeleted) {
        this.includeDeleted = includeDeleted;
    }

    public Boolean getIncludeHasExplicitSharedMembers() {
        return includeHasExplicitSharedMembers;
    }

    public void setIncludeHasExplicitSharedMembers(Boolean includeHasExplicitSharedMembers) {
        this.includeHasExplicitSharedMembers = includeHasExplicitSharedMembers;
    }

    public Boolean getIncludeNonDownloadableFiles() {
        return includeNonDownloadableFiles;
    }

    public void setIncludeNonDownloadableFiles(Boolean includeNonDownloadableFiles) {
        this.includeNonDownloadableFiles = includeNonDownloadableFiles;
    }
}
