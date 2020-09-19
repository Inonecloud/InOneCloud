package me.inonecloud.clouds.dto.dropbox;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("Download a file from a user's Dropbox")
public class DownloadFileDropboxRs {
    @JsonProperty("metadata")
    @JsonPropertyDescription("Metadata of the file")
    private DropboxMetadata metadata;

    @JsonProperty("link")
    @JsonPropertyDescription("The temporary link which can be used to stream content the file")
    private String link;

    public DropboxMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(DropboxMetadata metadata) {
        this.metadata = metadata;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
