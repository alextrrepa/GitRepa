package ru.scada.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "tcpconfig")
public class TcpEntity {
    @Expose
    private int id;
    @Expose
    private String ip;
    @Expose
    private int port;
    @Expose
    private int timeout;
    @Expose
    private int retries;
    @Expose
    private int period;
    private NodeEntity nodeEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tcp_id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "ip", nullable = false)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name = "port", nullable = false)
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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
}
