package auth.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class RoleEntity {
    private Long id;
    private String role;
    private String description;
    private List<UserEntity> users;
    private List<ResourceEntity> resources;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_resource", joinColumns = {
            @JoinColumn(name = "id_role")
    }, inverseJoinColumns = {@JoinColumn(name = "id_resource")
    })
    public List<ResourceEntity> getResources() {
        return resources;
    }

    public void setResources(List<ResourceEntity> resources) {
        this.resources = resources;
    }
}