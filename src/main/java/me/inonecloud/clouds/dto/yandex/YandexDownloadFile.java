package me.inonecloud.clouds.dto.yandex;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("Download a file from user's Yandex Disk")
public class YandexDownloadFile {
    @JsonProperty("href")
    @JsonPropertyDescription("The link which can be used to stream content the file")
    private String link;

    @JsonProperty("method")
    @JsonPropertyDescription("A HTTP method for URL request")
    private String method;

    @JsonProperty("templated")
    @JsonPropertyDescription("A tag which shows if URL is templated")
    private Boolean templated;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Boolean getTemplated() {
        return templated;
    }

    public void setTemplated(Boolean templated) {
        this.templated = templated;
    }
}
