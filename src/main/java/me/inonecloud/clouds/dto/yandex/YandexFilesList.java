package me.inonecloud.clouds.dto.yandex;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.List;

@JsonClassDescription("Yandex Files List")
public class YandexFilesList {
    @JsonProperty(value = "items")
    @JsonPropertyDescription("List of resource items")
    private List<Item> items;

    @JsonProperty(value = "limit")
    @JsonPropertyDescription("Max number of elements in List Items")
    private Integer limit;

    @JsonProperty("offset")
    @JsonPropertyDescription("Offset of the first file in folder")
    private Integer offset;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
