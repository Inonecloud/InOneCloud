package me.inonecloud.clouds.dto.dropbox;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("Request to download a file from user's Dropbox")
public class DownloadFileDropboxRq {
    @JsonProperty("path")
    @JsonPropertyDescription("A path to downloadable file")
    private String path;

    public DownloadFileDropboxRq() {
    }

    public DownloadFileDropboxRq(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
