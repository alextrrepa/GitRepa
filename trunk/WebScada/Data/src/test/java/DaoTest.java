import org.junit.Test;
import ru.scada.dao.GenericDao;
import ru.scada.dao.ItemDao;
import ru.scada.model.HourEntity;

import java.util.List;

public class DaoTest {
    @Test
    public void hourTest() {
        GenericDao<HourEntity> dao = new ItemDao<>(HourEntity.class);
        List<HourEntity> hList = dao.showHoursData("2015-09-08 16:00", "2015-17-09 21:00");
        for (HourEntity h : hList) {
            System.out.println(h.getDtime() + ":::" + h.getValue());
        }
    }

    @Test
    public void countTest() {
        /*GenericDao<CurrentEntity, Long> dao = new ItemDao<>(CurrentEntity.class);
        List<CurrentEntity> l = dao.showHoursData();
        for (CurrentEntity tt : l) {
            String name = tt.getTagEntity().getName();
            System.out.println(name + " " + tt.getDatetime() + " " + tt.getValue());         }*/
    }
}