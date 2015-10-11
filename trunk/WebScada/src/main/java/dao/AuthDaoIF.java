package dao;

import java.io.Serializable;

public interface AuthDaoIF<T, ID extends Serializable> {
    T getUserByUsername(String login);
}
