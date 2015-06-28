package org.webscada.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tag")
public class TagEntity implements Serializable{
    private long id;
    private String name;
    private int realOffset;
    private DatatypeEntity datatypeEntity;
    private DeviceEntity deviceEntity;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "realoffset")
    public int getRealOffset() {
        return realOffset;
    }

    public void setRealOffset(int realOffset) {
        this.realOffset = realOffset;
    }

    @ManyToOne
    @JoinColumn(name = "dtype_id")
    public DatatypeEntity getDatatypeEntity() {
        return datatypeEntity;
    }

    public void setDatatypeEntity(DatatypeEntity datatypeEntity) {
        this.datatypeEntity = datatypeEntity;
    }

    @ManyToOne
    @JoinColumn(name = "dev_id")
    public DeviceEntity getDeviceEntity() {
        return deviceEntity;
    }

    public void setDeviceEntity(DeviceEntity deviceEntity) {
        this.deviceEntity = deviceEntity;
    }
}