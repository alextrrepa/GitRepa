package ru.scada.controllers;

import org.apache.log4j.Logger;
import ru.scada.dao.GenericDao;
import ru.scada.dao.ItemDao;
import ru.scada.model.HourEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DataController extends HttpServlet {
    private final static Logger log = Logger.getLogger(DataController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dtime1 = request.getParameter("dtime1");
        log.info(dtime1);
        String dtime2 = request.getParameter("dtime2");
        log.info(dtime2);
        String dtype = request.getParameter("selectData");
        GenericDao<HourEntity> dao = new ItemDao<>(HourEntity.class);
        List<HourEntity> tagEntities = dao.showHoursData(dtime1, dtime2);
//        DataBean dBean = new DataBean(dtime1, dtime2, dtype);
        request.setAttribute("data", tagEntities);
        RequestDispatcher dispatcher = request.getRequestDispatcher("report.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
