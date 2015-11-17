package entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tag")
public class TagData {
    private long id;
    private String columnName;
    private List<HourEntity> hourEntities;
    private List<DayDataEntity> dayDataEntities;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tagData", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    public List<HourEntity> getHourEntities() {
        return hourEntities;
    }

    public void setHourEntities(List<HourEntity> hourEntities) {
        this.hourEntities = hourEntities;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tagData", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    public List<DayDataEntity> getDayDataEntities() {
        return dayDataEntities;
    }

    public void setDayDataEntities(List<DayDataEntity> dayDataEntities) {
        this.dayDataEntities = dayDataEntities;
    }
}