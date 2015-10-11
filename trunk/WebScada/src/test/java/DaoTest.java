import auth.entities.User;
import dao.AuthDaoIF;
import dao.AuthItemHibernateDao;
import org.junit.Test;

public class DaoTest {
    @Test
    public void testD() {
        AuthDaoIF<User, Long> authDao = new AuthItemHibernateDao<>(User.class);
        User user = authDao.getUserByUsername("admin");
        System.out.println(user.getUsername());
    }
}
