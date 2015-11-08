import dao.CommonOperationsHibernateDao;
import dao.DataItemHibernateDao;
import entities.TagEntity;
import org.junit.Test;

public class RoleTest {
    @Test
    public void testRole() {
        CommonOperationsHibernateDao<TagEntity, Long> tagDao = new DataItemHibernateDao<>(TagEntity.class);

    }
}
