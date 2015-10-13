import auth.entities.AppConfigEntity;
import dao.AuthDaoIF;
import dao.AuthItemHibernateDao;
import org.junit.Test;

import java.util.List;

public class ConfigTest {
    @Test
    public void auTest() {
        AuthDaoIF<AppConfigEntity, Long> authDao = new AuthItemHibernateDao<>(AppConfigEntity.class);
        List<AppConfigEntity> config = authDao.getAppConfig("webscada");
        for (AppConfigEntity entity : config) {
            System.out.println(entity.getAppName() + entity.getLoginModule() + entity.getControlFlag());
        }
    }
}
