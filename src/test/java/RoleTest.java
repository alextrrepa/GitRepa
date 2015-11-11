import dao.DataDaoIF;
import dao.DataItemHibernateDao;
import entities.HourEntity;
import entities.TagData;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RoleTest {
    @Test
    public void testRole() {
        Date startdate = null;
        Date enddate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            startdate = formatter.parse("2015-08-18 11:00");
            enddate = formatter.parse("2015-08-18 13:00");
        } catch (ParseException e) {
            System.out.println("Parse Exception");
        }

//        CommonOperationsHibernateDao<TagData, Long> countDao = new DataItemHibernateDao<>(TagData.class);
//        Integer size = countDao.getCount();

        DataDaoIF<TagData, Long> dataDao = new DataItemHibernateDao<>(TagData.class);
        List<TagData> hoursData = dataDao.getDataBetweenDates(startdate, enddate);
        System.out.println(hoursData.size());

        HourEntity[][] data = new HourEntity[hoursData.size()][];
        System.out.println(data.length);

        for (TagData tag : hoursData) {
            System.out.println(tag.getColumnName());
            List<HourEntity> list = tag.getHourEntities();
            for (HourEntity h : list) {
                System.out.println(h.getDtime() + " " + h.getValue());
            }
        }

       /* for (int i = 0; i < hoursData.size(); i++) {
            TagData tData = hoursData.get(i);
            List<HourEntity> hs = tData.getHourEntities();
            data[i] = hs.toArray(new HourEntity[hs.size()]);
        }

        for (int i = 0; i < data.length; i++) {
              for (int j = 0; j < data[i].length; j ++) {
                  System.out.println(data[i][j].getDtime() + " " + data[i][j].getValue());
              }
        }*/
    }
}

