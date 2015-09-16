package ru.scada.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hourdata")
public class HourEntity {
    private long h_id;
    private Date dtime;
    private double value;
    private TagEntity tagEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "h_id")
    public long getH_id() {
        return h_id;
    }

    public void setH_id(long h_id) {
        this.h_id = h_id;
    }

    public Date getDtime() {
        return dtime;
    }

    @Column(name = "dtime")
    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }

    @Column(name = "value")
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @ManyToOne
    @JoinColumn(name = "tag_id")
    public TagEntity getTagEntity() {
        return tagEntity;
    }

    public void setTagEntity(TagEntity tagEntity) {
        this.tagEntity = tagEntity;
    }
}
