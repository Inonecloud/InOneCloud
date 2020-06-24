package me.inonecloud.clouds.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetFilesGoogleDrive {
    @JsonProperty
    private String kind;

    @JsonProperty
    private String nextPageToken;

    @JsonProperty
    private Boolean incompleteSearch;

    @JsonProperty
    private List<GoogleFile> files;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public Boolean getIncompleteSearch() {
        return incompleteSearch;
    }

    public void setIncompleteSearch(Boolean incompleteSearch) {
        this.incompleteSearch = incompleteSearch;
    }

    public List<GoogleFile> getFiles() {
        return files;
    }

    public void setFiles(List<GoogleFile> files) {
        this.files = files;
    }
}
