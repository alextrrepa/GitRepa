import auth.entities.RoleEntity;
import auth.entities.UrlFilterEntity;
import auth.entities.UserEntity;
import dao.AuthDaoIF;
import dao.AuthItemHibernateDao;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class RoleTest {
    @Test
    public void testRole() {
        AuthDaoIF<UserEntity, Long> userDao = new AuthItemHibernateDao<>(UserEntity.class);
        UserEntity user = userDao.getUserByUsername("mas");
        System.out.println("Username:::" + user.getUsername());
        Set<RoleEntity> roles = user.getRoles();
        for (RoleEntity r : roles) {
            System.out.println("Role:::" + r.getRole());
            List<UrlFilterEntity> urls = r.getUrlFilters();
            for (UrlFilterEntity u : urls) {
                System.out.println("Url:::" + u.getUrl());
            }
        }
    }
}
