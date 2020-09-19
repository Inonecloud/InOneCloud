package me.inonecloud.clouds.dto.google;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("Download a file from user's Google Drive")
public class DownloadFileGoogle {
    @JsonProperty("webContentLink")
    @JsonPropertyDescription("A link to downloadFile")
    private String webContentLink;

    public String getWebContentLink() {
        return webContentLink;
    }

    public void setWebContentLink(String webContentLink) {
        this.webContentLink = webContentLink;
    }
}
