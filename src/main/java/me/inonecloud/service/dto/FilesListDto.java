package me.inonecloud.service.dto;


import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.List;

@JsonClassDescription("Cloud storage files")
public class FilesListDto {
    @JsonProperty(value = "items")
    @JsonPropertyDescription("Item: file or folder")
    private List<Item> items;

    public FilesListDto() {
    }

    public FilesListDto(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
