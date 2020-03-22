package me.inonecloud.service.dto;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Information about Cloud Storage")
public class CloudInfoDto {

    @JsonProperty(value = "total_space")
    @JsonPropertyDescription("All storage space in bytes")
    private Long totalSpace;

    @JsonProperty(value = "used_space")
    @JsonPropertyDescription("Used storage space in bytes")
    private Long usedSpace;

    @JsonProperty(value = "trash_size")
    @JsonPropertyDescription("In trash tin in bytes")
    private Long trashSize;

    @JsonProperty(value = "paid")
    @JsonPropertyDescription("Tariff")
    private Boolean isPaid;

    @JsonProperty("username")
    @JsonPropertyDescription("storage account")
    private String username;

    public CloudInfoDto() {
    }

    public Long getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(Long totalSpace) {
        this.totalSpace = totalSpace;
    }

    public Long getUsedSpace() {
        return usedSpace;
    }

    public void setUsedSpace(Long usedSpace) {
        this.usedSpace = usedSpace;
    }

    public Long getTrashSize() {
        return trashSize;
    }

    public void setTrashSize(Long trashSize) {
        this.trashSize = trashSize;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
