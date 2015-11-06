package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "currentdata")
public class CurrentEntity {
    private long id;
    private Date datetime;
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
    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @PrePersist
    protected void onInsert() {
        datetime = new Date();
    }

    @Column(name = "value")
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
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
