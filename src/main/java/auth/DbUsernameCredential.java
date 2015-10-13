package auth;

import java.util.Objects;

public class DbUsernameCredential {
    private String name;
    private Long id;

    public DbUsernameCredential(String name, Long id) {
        if (name == null || id == null) {
            throw new NullPointerException("Name or ID are null");
        }
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DbUsernameCredential)) return false;
        DbUsernameCredential that = (DbUsernameCredential) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getName().hashCode() * 13 + getId().hashCode() * 13;
    }
}
