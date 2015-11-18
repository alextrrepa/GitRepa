package entities;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tag")
public class TagEntity implements Serializable {
    @Expose
    private long id;
    @Expose
    private String name;
    @Expose
    private int realOffset;
    @Expose
    private DatatypeEntity datatypeEntity;
    private DeviceEntity deviceEntity;
    @Expose
    private String columnName;
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name")
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

    @Column(name = "columnname")
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
