package ru.scada.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hourdata")
public class HourEntity {
    private int h_id;
    private int tag_id;
    private Date dtime;
    private double value;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "h_id")
    public int getH_id() {
        return h_id;
    }

    public void setH_id(int h_id) {
        this.h_id = h_id;
    }

    @Column(name = "tag_id")
    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    @Column(name = "dtime")
    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }

    public Date getDtime() {
        return dtime;
    }

    @Column(name = "value")
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
