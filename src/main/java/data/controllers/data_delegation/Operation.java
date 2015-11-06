package data.controllers.data_delegation;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Operation {
    private final static Logger log = Logger.getLogger(Operation.class);

    public void getIndexPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("data/data.jsp").forward(request, response);
    }

    public void viewingData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startdatetime = request.getParameter("startdatetime");
        String enddatetime = request.getParameter("enddatetime");
        String datatype = request.getParameter("datatype");

        Date startdate;
        Date enddate;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            startdate = formatter.parse(startdatetime);
            enddate = formatter.parse(enddatetime);
            log.info(startdate);
            log.info(enddate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


//        log.info("StartDateTime:::" + startdatetime);
//        log.info("EndDateTime:::" + enddatetime);
//        log.info("DataType:::" + datatype);
        request.getRequestDispatcher("/data/data.jsp").forward(request, response);
    }
}
