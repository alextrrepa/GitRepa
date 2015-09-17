package ru.scada.controllers;

import org.apache.log4j.Logger;
import ru.scada.dao.GenericDao;
import ru.scada.dao.ItemDao;
import ru.scada.model.CurrentEntity;
import ru.scada.model.HourEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataController extends HttpServlet {
    private final static Logger log = Logger.getLogger(DataController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dtime1 = request.getParameter("dtime1");
        String dtime2 = request.getParameter("dtime2");

        String dtype = request.getParameter("selectData");

        Date sDate = null;
        Date enDate = null;
        try {
            sDate = formatter.parse(dtime1);
            enDate = formatter.parse(dtime2);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (dtype.equalsIgnoreCase("hoursData")) {
            GenericDao<HourEntity> dao = new ItemDao<>(HourEntity.class);
            dao.showData(sDate, enDate);
        }

        if (dtype.equalsIgnoreCase("dayData")) {
            GenericDao<CurrentEntity> dao = new ItemDao<>(CurrentEntity.class);
            dao.showData(sDate, enDate);
        }

//        request.setAttribute("data", tagEntities);
        RequestDispatcher dispatcher = request.getRequestDispatcher("report.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
