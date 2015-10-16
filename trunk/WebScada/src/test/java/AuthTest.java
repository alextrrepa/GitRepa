import auth.entities.PermissionEntity;
import dao.AuthDaoIF;
import dao.AuthItemHibernateDao;
import org.junit.Test;

import java.util.Set;

public class AuthTest {
    @Test
    public void testAuth() {
        AuthDaoIF<PermissionEntity, Long> dao = new AuthItemHibernateDao<>(PermissionEntity.class);
        Set<String> s = dao.getPermissionsByUsername("admin");
        for (String s1 : s) {
            System.out.println(s1);
        }
    }
}
