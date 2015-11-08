package data.controllers.data_delegation;

import dao.DataDaoIF;
import dao.DataItemHibernateDao;
import entities.TagEntity;
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
//            log.info(startdate);
//            log.info(enddate);
        } catch (ParseException e) {
            request.setAttribute("error", "Не указаны начало и конец периода");
        }

        if (datatype.equalsIgnoreCase("hours")) {
            request.setAttribute("datatype", "Часовые данные");
            DataDaoIF<TagEntity, Long> dataDao = new DataItemHibernateDao<>(TagEntity.class);
            List<TagEntity> results = dataDao.getDataBetweenDates(startdate, enddate);
            request.setAttribute("data", results);
        } else if (datatype.equalsIgnoreCase("day")) {
            request.setAttribute("datatype", "Суточные данные");
        }

/*
        CommonOperationsHibernateDao<TagEntity, Long> tagDao = new DataItemHibernateDao<>(TagEntity.class);
        List<TagEntity> tags = tagDao.getAll();
        request.setAttribute("headers", tags);
*/


        request.getRequestDispatcher("/data/data.jsp").forward(request, response);
    }
}
