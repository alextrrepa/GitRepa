package entities;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "node")
public class NodeEntity {
    @Expose
    private long id;
    @Expose
    private String name;
    @Expose
    private String type;
    @Expose
    private RtuEntity rtuEntity;
    @Expose
    private List<DeviceEntity> deviceEntity;
    @Expose
    private TcpEntity tcpEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "node_id", unique = true, nullable = false)
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

    @Column(name = "type", nullable = true)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "nodeEntity", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    public RtuEntity getRtuEntity() {
        return rtuEntity;
    }

    public void setRtuEntity(RtuEntity rtuEntity) {
        this.rtuEntity = rtuEntity;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "nodeEntity", cascade = CascadeType.DETACH)
    @Fetch(FetchMode.SELECT)
    public List<DeviceEntity> getDeviceEntity() {
        return deviceEntity;
    }

    public void setDeviceEntity(List<DeviceEntity> deviceEntity) {
        this.deviceEntity = deviceEntity;
    }

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "nodeEntity", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    public TcpEntity getTcpEntity() {
        return tcpEntity;
    }

    public void setTcpEntity(TcpEntity tcpEntity) {
        this.tcpEntity = tcpEntity;
    }
}
