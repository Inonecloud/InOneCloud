package me.inonecloud.clouds.dto.dropbox;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PropertyGroups {
    @JsonProperty("template_id")
    private String templateId;

    @JsonProperty("fields")
    private List<Field> fields;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
