package ru.scada.controllers;

import org.apache.log4j.Logger;
import ru.scada.controllers.data_delegation.DataBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DataController extends HttpServlet {
    private final static Logger log = Logger.getLogger(DataController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dtime1 = request.getParameter("dtime1");
        String dtime2 = request.getParameter("dtime2");
        String dtype = request.getParameter("selectData");
        DataBean dBean = new DataBean(dtime1, dtime2, dtype);
        request.setAttribute("data", dBean);
        RequestDispatcher dispatcher = request.getRequestDispatcher("report.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
