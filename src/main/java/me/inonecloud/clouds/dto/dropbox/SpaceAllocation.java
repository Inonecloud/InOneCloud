package me.inonecloud.clouds.dto.dropbox;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonClassDescription("The user's space allocation")
public class SpaceAllocation {
    @JsonProperty(value = "allocated")
    private Long allocated;

    public Long getAllocated() {
        return allocated;
    }

    public void setAllocated(Long allocated) {
        this.allocated = allocated;
    }
}
