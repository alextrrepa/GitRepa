import org.junit.Test;
import ru.scada.dao.GenericDao;
import ru.scada.dao.ItemDao;
import ru.scada.model.HourEntity;

public class DaoTest {
    @Test
    public void hourTest() {
        /*GenericDao<HourEntity, Long> dao = new ItemDao<>(HourEntity.class);
        List<HourEntity> hours = dao.showHoursData();
        for (HourEntity h : hours) {
            System.out.println(h.getDtime() + " " + h.getValue());
        }*/
    }

    @Test
    public void countTest() {
        GenericDao<HourEntity, Long> dao = new ItemDao<>(HourEntity.class);
        Long c = dao.getCount();
        System.out.println("Counts" + c);
    }
}
