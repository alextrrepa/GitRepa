package ru.scada.controllers;

import org.apache.log4j.Logger;
import ru.scada.controllers.report_delegation.Command;
import ru.scada.controllers.report_delegation.DoReport;
import ru.scada.controllers.report_delegation.HoursData;
import ru.scada.controllers.report_delegation.ReportActions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReportController extends HttpServlet {
    private final static Logger log = Logger.getLogger(ReportController.class);
    private Map<String, Command> commandMap = new HashMap<>();

    public ReportController() {
        ReportActions actions = new ReportActions();
        commandMap.put("hoursData", new HoursData(actions));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String parameter = request.getParameter("actions");
        Command command = commandMap.get(parameter);
        DoReport doReport = new DoReport(command);
        doReport.makeCommand(request, response);
/*        response.setContentType("application/pdf");
        ServletOutputStream servletOutputStream = response.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fileInputStream;
        BufferedInputStream bufferedInputStream;
        try {
            ServletContext context = getServletContext();
            String reportLocation = context.getRealPath("WEB-INF");

            fileInputStream = new FileInputStream(reportLocation + "/MyRep.jasper");
            bufferedInputStream = new BufferedInputStream(fileInputStream);

            Session session = SessionUtil.getSession();
            List<HourEntity> hours = session.createCriteria(HourEntity.class).list();
            session.close();

            JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(hours);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<String, Object>(), source);

            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            response.setContentLength(baos.size());
            baos.writeTo(servletOutputStream);
            fileInputStream.close();
            bufferedInputStream.close();
        } catch (JRException e) {
            e.printStackTrace();
        }finally {
            servletOutputStream.flush();
            servletOutputStream.close();
            baos.close();
        }*/
    }
}
