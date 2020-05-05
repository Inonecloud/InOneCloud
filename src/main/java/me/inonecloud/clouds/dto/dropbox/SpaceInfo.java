package me.inonecloud.clouds.dto.dropbox;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonClassDescription("Dropbox information about a user's space usage and quota")
public class SpaceInfo {
    @JsonProperty(value = "used")
    private Long used;

    @JsonProperty(value = "allocation")
    private SpaceAllocation spaceAllocation;

    public Long getUsed() {
        return used;
    }

    public void setUsed(Long used) {
        this.used = used;
    }

    public SpaceAllocation getSpaceAllocation() {
        return spaceAllocation;
    }

    public void setSpaceAllocation(SpaceAllocation spaceAllocation) {
        this.spaceAllocation = spaceAllocation;
    }
}
