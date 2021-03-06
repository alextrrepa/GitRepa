package dao;

import auth.entities.RoleEntity;
import auth.entities.UrlFilterEntity;
import auth.entities.UserEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface AuthDaoIF<T, ID extends Serializable> extends CommonOperationsDaoIF<T, ID> {
    UserEntity getUserByUsername(String login);
    Set<String> getPermissionsByUsername(String username);
    Set<String> getRolesByUsername(String username);
    List<RoleEntity> getAllRoles();
    List<UrlFilterEntity> getUrlFilters();
}
