import auth.entities.UserRoleEntity;
import dao.AuthDaoIF;
import dao.AuthItemHibernateDao;
import org.junit.Test;

import java.util.Set;

public class AuthTest {
    @Test
    public void testAuth() {
        AuthDaoIF<UserRoleEntity, Long> dao = new AuthItemHibernateDao<>(UserRoleEntity.class);
        Set<String> s = dao.getUserRolesByUsername("admin");
        for (String s1 : s) {
            System.out.println(s1);
        }
    }
}
