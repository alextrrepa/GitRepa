package auth.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "url_filter")
public class UrlFilterEntity {
    private Long id;
    private String name;
    private String url;
    private Set<RoleEntity> roles;
    private Set<ResourceEntity> resources;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "url_roles_resource", joinColumns = {
            @JoinColumn(name = "url_filter_id")
    }, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "url_roles_resource", joinColumns = {
            @JoinColumn(name = "url_filter_id")
    }, inverseJoinColumns = {@JoinColumn(name = "resource_id")})
    public Set<ResourceEntity> getResources() {
        return resources;
    }

    public void setResources(Set<ResourceEntity> resources) {
        this.resources = resources;
    }
}
