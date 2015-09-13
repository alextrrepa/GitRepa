package ru.scada.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tag")
public class TagEntity implements Serializable {
    private long id;
    private String name;
    private int realOffset;
    private String columnName;
    private String description;
    private List<CurrentEntity> currentEntities;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", unique = true, nullable = false)
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

    @Column(name = "realoffset")
    public int getRealOffset() {
        return realOffset;
    }

    public void setRealOffset(int realOffset) {
        this.realOffset = realOffset;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tagEntity", cascade = CascadeType.DETACH)
    @Fetch(FetchMode.SELECT)
    public List<CurrentEntity> getCurrentEntities() {
        return currentEntities;
    }

    public void setCurrentEntities(List<CurrentEntity> currentEntities) {
        this.currentEntities = currentEntities;
    }

    @Column(name = "columnname")
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
