package dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface AuthDaoIF<T, ID extends Serializable> {
    T getUserByUsername(String login);
    Set<String> getPermissionsByUsername(String username);
    List<T> getAllRoles();
}
