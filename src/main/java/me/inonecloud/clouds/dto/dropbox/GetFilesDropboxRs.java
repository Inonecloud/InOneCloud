package me.inonecloud.clouds.dto.dropbox;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.List;

@JsonClassDescription("")
public class GetFilesDropboxRs {
    @JsonProperty("entries")
    @JsonPropertyDescription("The files and (direct) subfolders in the folder")
    private List<Entries> entries;

    @JsonProperty("cursor")
    @JsonPropertyDescription("Pass the cursor into list_folder/continue to see what's changed in the folder since your previous query")
    private String cursor;

    @JsonProperty("has_more")
    @JsonPropertyDescription("Pass the cursor to list_folder/continue to retrieve the rest")
    private Boolean hasMore;

    public List<Entries> getEntries() {
        return entries;
    }

    public void setEntries(List<Entries> entries) {
        this.entries = entries;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
