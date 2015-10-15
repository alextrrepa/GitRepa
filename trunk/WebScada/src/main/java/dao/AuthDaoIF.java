package dao;

import java.io.Serializable;
import java.util.Set;

public interface AuthDaoIF<T, ID extends Serializable> {
    T getUserByUsername(String login);

    Set<String> getUserRolesByUsername(String username);
}
