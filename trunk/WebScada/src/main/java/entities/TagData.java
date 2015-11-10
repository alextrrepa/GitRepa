package entities;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tag")
public class TagData {
    private long id;
    @Expose
    private String columnName;
    @Expose
    private List<HourEntity> hourEntities;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "columnname")
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tagEntity", cascade = CascadeType.DETACH)
    @Fetch(FetchMode.SELECT)
    public List<HourEntity> getHourEntities() {
        return hourEntities;
    }

    public void setHourEntities(List<HourEntity> hourEntities) {
        this.hourEntities = hourEntities;
    }
}