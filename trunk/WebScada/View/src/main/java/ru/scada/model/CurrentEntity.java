package ru.scada.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "currentdata")
public class CurrentEntity {
    private long c_id;
    private long tag_id;
    private Date datetime;
    private float value;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id", unique = true, nullable = false)
    public long getId() {
        return c_id;
    }

    public void setId(long c_id) {
        this.c_id = c_id;
    }

    @Column(name = "tag_id")
    public long getTag_id() {
        return tag_id;
    }

    public void setTag_id(long tag_id) {
        this.tag_id = tag_id;
    }

    @Column(name = "dtime")
    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @PrePersist
    protected void onInsert(){
        datetime = new Date();
    }

    @Column(name = "value")
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
