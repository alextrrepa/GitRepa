package entities;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "rtuconfig")
public class RtuEntity {
    @Expose
    private long id;
    @Expose
    private String port;
    @Expose
    private int baudrate;
    @Expose
    private int databits;
    @Expose
    private int stopbits;
    @Expose
    private int parity;
    @Expose
    private int timeout;
    @Expose
    private int retries;
    @Expose
    private int period;
    private NodeEntity nodeEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rtu_id", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "port", nullable = false)
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Column(name = "baudrate", nullable = false)
    public int getBaudrate() {
        return baudrate;
    }

    public void setBaudrate(int baudrate) {
        this.baudrate = baudrate;
    }

    @Column(name = "databits", nullable = false)
    public int getDatabits() {
        return databits;
    }

    public void setDatabits(int databits) {
        this.databits = databits;
    }

    @Column(name = "stopbits", nullable = false)
    public int getStopbits() {
        return stopbits;
    }

    public void setStopbits(int stopbits) {
        this.stopbits = stopbits;
    }

    @Column(name = "parity", nullable = false)
    public int getParity() {
        return parity;
    }

    public void setParity(int parity) {
        this.parity = parity;
    }

    @Column(name = "timeout", nullable = false)
    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Column(name = "retries", nullable = false)
    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    @Column(name = "period", nullable = false)
    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @OneToOne
    @JoinColumn(name = "node_id")
    public NodeEntity getNodeEntity() {
        return nodeEntity;
    }

    public void setNodeEntity(NodeEntity nodeEntity) {
        this.nodeEntity = nodeEntity;
    }

    @Override
    public String toString() {
        return "RtuEntity{" +
                "id=" + id +
                ", port='" + port + '\'' +
                ", baudrate=" + baudrate +
                ", databits=" + databits +
                ", stopbits=" + stopbits +
                ", parity=" + parity +
                ", timeout=" + timeout +
                ", retries=" + retries +
                ", period=" + period +
                ", nodeEntity=" + nodeEntity +
                '}';
    }
}
