import auth.entities.ResourceEntity;
import auth.entities.RoleEntity;
import auth.entities.UrlFilterEntity;
import dao.AuthDaoIF;
import dao.AuthItemHibernateDao;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class RoleTest {
    @Test
    public void testRole() {
        AuthDaoIF<UrlFilterEntity, Long> urlDao = new AuthItemHibernateDao<>(UrlFilterEntity.class);
        List<UrlFilterEntity> urls = urlDao.getUrlFilters();
        for (UrlFilterEntity u : urls) {
            System.out.println(u.getName() + " " + u.getUrl());
            Set<RoleEntity> roles = u.getRoles();
            for (RoleEntity r : roles) {
                System.out.println(r.getRole());
                Set<ResourceEntity> resources = r.getResources();
                for (ResourceEntity res : resources) {
                    System.out.println(res.getPermission());
                }
            }
        }
    }
}
