package ru.scada.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "currentdata")
public class CurrentEntity {
    private long id;
    private Date dtime;
    private float value;
    private TagEntity tagEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "dtime")
    public Date getdtime() {
        return dtime;
    }

    public void setdtime(Date dtime) {
        this.dtime = dtime;
    }

    @PrePersist
    protected void onInsert(){
        dtime = new Date();
    }

    @Column(name = "value")
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @ManyToOne
    @JoinColumn(name = "t_id")
    public TagEntity getTagEntity() {
        return tagEntity;
    }

    public void setTagEntity(TagEntity tagEntity) {
        this.tagEntity = tagEntity;
    }
}
