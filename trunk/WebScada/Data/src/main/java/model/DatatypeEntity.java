package model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "datatype")
public class DatatypeEntity {
    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private int value;
    private List<TagEntity> tagEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dtype_id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "value", nullable = false)
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @OneToMany(mappedBy = "datatypeEntity")
    public List<TagEntity> getTagEntity() {
        return tagEntity;
    }

    public void setTagEntity(List<TagEntity> tagEntity) {
        this.tagEntity = tagEntity;
    }
}
