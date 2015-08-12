package model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "device")
public class DeviceEntity {
    @Expose
    private long id;
    @Expose
    private String name;
    @Expose
    private int slaveid;
    @Expose
    private int startOffset;
    @Expose
    private int counts;
    private NodeEntity nodeEntity;
    @Expose
    private List<TagEntity> tagEntities;
    @Expose
    private RegisterEntity registerEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dev_id", unique = true, nullable = false)
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

    @Column(name = "slaveid", nullable = false)
    public int getSlaveid() {
        return slaveid;
    }

    public void setSlaveid(int slaveid) {
        this.slaveid = slaveid;
    }

    @Column(name = "startoffset", nullable = false)
    public int getStartOffset() {
        return startOffset;
    }

    public void setStartOffset(int startOffset) {
        this.startOffset = startOffset;
    }

    @Column(name = "counts", nullable = false)
    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    @ManyToOne
    @JoinColumn(name = "node_id")
    public NodeEntity getNodeEntity() {
        return nodeEntity;
    }

    public void setNodeEntity(NodeEntity nodeEntity) {
        this.nodeEntity = nodeEntity;
    }

    @OneToMany(mappedBy = "deviceEntity", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Fetch(value = FetchMode.SELECT)
    @OrderBy("name")
    public List<TagEntity> getTagEntities() {
        return tagEntities;
    }

    public void setTagEntities(List<TagEntity> tagEntities) {
        this.tagEntities = tagEntities;
    }

    @ManyToOne
    @JoinColumn(name = "reg_id")
    public RegisterEntity getRegisterEntity() {
        return registerEntity;
    }

    public void setRegisterEntity(RegisterEntity registerEntity) {
        this.registerEntity = registerEntity;
    }

    @Override
    public String toString() {
        return "DeviceEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slaveid=" + slaveid +
                ", startOffset=" + startOffset +
                ", counts=" + counts +
                '}';
    }
}
