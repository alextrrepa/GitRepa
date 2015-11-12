package data.controllers.data_delegation;

import dao.DataDaoIF;
import dao.DataItemHibernateDao;
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

        if (datatype.equalsIgnoreCase("hours")) {
            request.setAttribute("datatype", "Часовые данные");
            DataDaoIF<TagData, Long> dataDao = new DataItemHibernateDao<>(TagData.class);
            List<TagData> hoursData = dataDao.getDataBetweenDates(startdate, enddate);

            request.setAttribute("colnames", hoursData);
            request.setAttribute("data", getHourData(hoursData));
        } else if (datatype.equalsIgnoreCase("day")) {
            request.setAttribute("datatype", "Суточные данные");
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

        System.out.println(revertData.length);
        for (int i = 0; i < revertData.length; i++) {
            for (int j = 0; j < revertData[i].length; j++) {
                System.out.println(revertData[i][j].getDtime() + " " + revertData[i][j].getValue());
            }
        }
        return revertData;
    }
}
