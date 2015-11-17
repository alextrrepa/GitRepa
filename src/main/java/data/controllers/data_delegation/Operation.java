package data.controllers.data_delegation;

import dao.DataDaoIF;
import dao.DataItemHibernateDao;
import entities.DayDataEntity;
import entities.HourEntity;
import entities.TagData;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Operation {
    private final static Logger log = Logger.getLogger(Operation.class);

    public void getIndexPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("data/data.jsp").forward(request, response);
    }

    public void viewingData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startdatetime = request.getParameter("startdatetime");
        String enddatetime = request.getParameter("enddatetime");
        String datatype = request.getParameter("datatype");

        Date startdate = null;
        Date enddate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            startdate = formatter.parse(startdatetime);
            enddate = formatter.parse(enddatetime);
        } catch (ParseException e) {
            request.setAttribute("error", "Не указаны начало и конец периода");
        }

        DataDaoIF<TagData, Long> dataDao = new DataItemHibernateDao<>(TagData.class);
        List<TagData> data = dataDao.getHourDataBetweenDates(startdate, enddate);
        request.setAttribute("colnames", data);

        if (datatype.equalsIgnoreCase("hours")) {
            request.setAttribute("datatype", "Часовые данные");
            request.setAttribute("data", getHourData(data));
        } else if (datatype.equalsIgnoreCase("day")) {
            request.setAttribute("datatype", "Суточные данные");
            request.setAttribute("data", getDayData(data));
        }
        request.getRequestDispatcher("/data/data.jsp").forward(request, response);
    }

    private HourEntity[][] getHourData(List<TagData> hoursData) {
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
            for (int j = 0; j < data[i].length; j++) {
                revertData[j][i] = data[i][j];
            }
        }
        return revertData;
    }

    private DayDataEntity[][] getDayData(List<TagData> dayData) {
        int rowCount = dayData.size();
        DayDataEntity[][] data = new DayDataEntity[rowCount][];
        int maxRowSize = dayData.get(0).getDayDataEntities().size();

        for (int i = 0; i < rowCount; i++) {
            TagData tData = dayData.get(i);
            List<DayDataEntity> hs = tData.getDayDataEntities();
            data[i] = hs.toArray(new DayDataEntity[hs.size()]);
            if (dayData.get(i).getDayDataEntities().size() > maxRowSize) {
                maxRowSize = dayData.get(i).getDayDataEntities().size();
            }
        }

        DayDataEntity[][] revertData = new DayDataEntity[maxRowSize][rowCount];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                revertData[j][i] = data[i][j];
            }
        }
        return revertData;
    }
}