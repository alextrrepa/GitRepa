package org.webscada.model.tree;

import java.util.List;

public class DeviceParams {
    private String tagType;
    private String name;
    private long id;
    private List<TagParams> tagList;

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<TagParams> getTagList() {
        return tagList;
    }

    public void setTagList(List<TagParams> tagList) {
        this.tagList = tagList;
    }
}
