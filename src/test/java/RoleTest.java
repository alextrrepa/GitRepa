import org.junit.Test;

public class RoleTest {
    @Test
    public void testRole() {
/*        Date startdate = null;
        Date enddate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            startdate = formatter.parse("2015-08-17 00:00");
            enddate = formatter.parse("2015-08-18 00:00");
        } catch (ParseException e) {
            System.out.println("Parse Exception");
        }

        DataDaoIF<TagData, Long> dataDao = new DataItemHibernateDao<>(TagData.class);
        List<TagData> hoursData = dataDao.getDataBetweenDates(startdate, enddate);

        int rowCount = hoursData.size();
//        System.out.println(rowCount);

        DayDataEntity[][] data = new DayDataEntity[rowCount][];

        int maxRowSize = hoursData.get(0).getDayDataEntities().size();
//        System.out.println(maxRowSize);

        for (int i = 0; i < rowCount; i++) {
            TagData tData = hoursData.get(i);
            List<DayDataEntity> hs = tData.getDayDataEntities();
            data[i] = hs.toArray(new DayDataEntity[hs.size()]);
            if (hoursData.get(i).getDayDataEntities().size() > maxRowSize) {
                maxRowSize = hoursData.get(i).getDayDataEntities().size();
            }
        }

        DayDataEntity[][] revertData = new DayDataEntity[maxRowSize][rowCount];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                revertData[j][i] = data[i][j];
            }
        }

        System.out.println(revertData.length);
        for (DayDataEntity[] aRevertData : revertData) {
            for (DayDataEntity anARevertData : aRevertData) {
                System.out.println(anARevertData.getDtime() + " " + anARevertData.getValue());
            }
            System.out.println("***********************");
        }*/
    }
}