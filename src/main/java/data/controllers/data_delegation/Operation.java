package data.controllers.data_delegation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DataDaoIF;
import dao.DataItemHibernateDao;
import entities.TagData;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
            List<TagData> hours = dataDao.getDataBetweenDates(startdate, enddate);
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
            String result = gson.toJson(hours);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.write(result);
            out.close();
//            request.setAttribute("data", result);
        } else if (datatype.equalsIgnoreCase("day")) {
            request.setAttribute("datatype", "Суточные данные");
        }
/*
        CommonOperationsHibernateDao<TagEntity, Long> tagDao = new DataItemHibernateDao<>(TagEntity.class);
        List<TagEntity> tags = tagDao.getAll();
        request.setAttribute("headers", tags);
*/
//        request.getRequestDispatcher("/data/data.jsp").forward(request, response);
    }
}
