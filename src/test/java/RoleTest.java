import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
/*        DataDaoIF<TagData, Long> dataDao = new DataItemHibernateDao<>(TagData.class);
        List<TagData> hoursData = dataDao.getDataBetweenDates(startdate, enddate);

        int rowCount = hoursData.size();

        HourEntity[][] data = new HourEntity[rowCount][];

        int maxRowSize = hoursData.get(0).getHourEntities().size();

        for (int i = 0; i < rowCount; i++) {
            TagData tData = hoursData.get(i);
            List<HourEntity> hs = tData.getHourEntities();
            data[i] = hs.toArray(new HourEntity[hs.size()]);
            if (hoursData.get(i).getHourEntities().size() > maxRowSize) {
                maxRowSize = hoursData.get(i).getHourEntities().size();
            }
        }

        HourEntity[][] revertData = new HourEntity[maxRowSize][rowCount];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j ++) {
                revertData[j][i] = data[i][j];
            }
        }

        System.out.println(revertData.length);
        for (int i = 0; i < revertData.length; i++) {
            for (int j = 0; j < revertData[i].length; j++) {
                System.out.println(revertData[i][j].getDtime() + " " + revertData[i][j].getValue());
            }
            System.out.println("*//*************************//*");
        }*/
    }
}