package org.webscada.model.tree;

import java.util.List;

public class NodeParams {
    private String tagType;
    private String name;
    private String type;
    private long id;
    private List<DeviceParams> deviceList;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getTagType() {
        return tagType;
    }

    public String getType() {
        return type;
    }

    public void setTagType(String tagName) {
        this.tagType = tagName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDeviceList(List<DeviceParams> deviceList) {
        this.deviceList = deviceList;
    }

    public List<DeviceParams> getDeviceList() {
        return deviceList;
    }
}
