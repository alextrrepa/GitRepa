import dao.CommonOperationsHibernateDao;
import dao.DataItemHibernateDao;
import entities.HourEntity;
import entities.TagEntity;
import org.junit.Test;

import java.util.List;

public class RoleTest {
    @Test
    public void testRole() {
        CommonOperationsHibernateDao<TagEntity, Long> tagDao = new DataItemHibernateDao<>(TagEntity.class);
        List<TagEntity> tags = tagDao.getAll();
        for (TagEntity t : tags) {
            System.out.println(t.getName() + ":::" + t.getColumnName());
            List<HourEntity> hours = t.getHourEntities();
            for (HourEntity h : hours) {
                System.out.println(h.getDtime() + ":::" + h.getValue());
            }
        }
    }
}
