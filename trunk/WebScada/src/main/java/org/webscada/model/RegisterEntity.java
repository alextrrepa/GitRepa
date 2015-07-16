package org.webscada.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "registertype")
public class RegisterEntity {
    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private int value;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reg_id", unique = true, nullable = false)
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
}
