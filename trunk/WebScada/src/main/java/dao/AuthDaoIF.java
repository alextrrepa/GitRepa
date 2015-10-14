package dao;

import java.io.Serializable;
import java.util.List;

public interface AuthDaoIF<T, ID extends Serializable> {
    T getUserByUsername(String login);

    List<T> getUserRole(String appName);
}
