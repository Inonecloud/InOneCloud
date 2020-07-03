package me.inonecloud.service.dto;

import com.fasterxml.jackson.annotation.*;
import me.inonecloud.domain.CloudStorage;

import java.util.Date;

@JsonClassDescription("Item")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {
    @JsonProperty(value = "id")
    @JsonPropertyDescription("File id")
    private String id;

    @JsonProperty(value = "type")
    @JsonPropertyDescription("Type of item. File or folder")
    private Type type;

    @JsonProperty(value = "name")
    @JsonPropertyDescription("File name")
    private String name;

    @JsonProperty(value = "path")
    @JsonPropertyDescription("Path to file")
    private String path;

    @JsonProperty(value = "creaded")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonPropertyDescription("Creation date")
    private Date created;

    @JsonProperty(value = "modified")
    @JsonPropertyDescription("Modification date")
    private Date modified;

    @JsonProperty(value = "size")
    @JsonPropertyDescription("File size in bytes")
    private Long size;

    @JsonProperty(value = "source")
    @JsonPropertyDescription("Source of file. Cloud storage name")
    private CloudStorage source;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public CloudStorage getSource() {
        return source;
    }

    public void setSource(CloudStorage source) {
        this.source = source;
    }
}
