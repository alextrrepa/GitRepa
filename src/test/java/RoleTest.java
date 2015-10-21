import auth.entities.UserEntity;
import dao.AuthDaoIF;
import dao.AuthItemHibernateDao;
import org.junit.Test;

import java.util.Set;

public class RoleTest {
    @Test
    public void testRole() {
        AuthDaoIF roleDao = new AuthItemHibernateDao(UserEntity.class);
        Set<String> perms = roleDao.getPermissionsByUsername("zak");
        for (String p : perms) {
            System.out.println(p);
        }
    }
}
