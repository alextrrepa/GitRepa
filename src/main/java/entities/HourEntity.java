package entities;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hourdata")
public class HourEntity {
    @Expose
    private long id;
    @Expose
    private Date dtime;
    @Expose
    private double value;
    private TagEntity tagEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getid() {
        return id;
    }

    public void setid(long id) {
        this.id = id;
    }

    @Column(name = "dtime", updatable = false, insertable = false, columnDefinition = "timestamp without time zone")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDtime() {
        return dtime;
    }

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

    @ManyToOne()
    @JoinColumn(name = "tag_id", unique = true)
    public TagEntity getTagEntity() {
        return tagEntity;
    }

    public void setTagEntity(TagEntity tagEntity) {
        this.tagEntity = tagEntity;
    }
}
