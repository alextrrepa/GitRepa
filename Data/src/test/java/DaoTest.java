import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import ru.scada.dao.GenericDao;
import ru.scada.dao.ItemDao;
import ru.scada.model.HourEntity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DaoTest {
    @Test
    public void hourTest() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String s1 = "2015-08-18 11:00";
        String s2 = "2015-08-18 12:00";
        Date sDate = null;
        Date enDate = null;
        try {
            sDate = formatter.parse(s1);
            enDate = formatter.parse(s2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GenericDao<HourEntity> dao = new ItemDao<>(HourEntity.class);
        Map<String, List<HourEntity>> m = dao.showData(sDate, enDate);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        String json = gson.toJson(m);
        System.out.println(json);
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