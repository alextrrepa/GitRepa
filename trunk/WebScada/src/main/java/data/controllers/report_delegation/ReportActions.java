package data.controllers.report_delegation;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;

public class ReportActions {
    private final static Logger log = Logger.getLogger(ReportActions.class);

    public void getIndexPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("report/report.jsp").forward(request, response);
    }

    public void hourReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        ServletOutputStream servletOutputStream = response.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream fileInputStream;
        BufferedInputStream bufferedInputStream;
        try {
            ServletContext context = request.getServletContext();
            String reportLocation = context.getRealPath("WEB-INF");

            fileInputStream = new FileInputStream(reportLocation + "/HourReport.jasper");
            bufferedInputStream = new BufferedInputStream(fileInputStream);

//            Session session = SessionUtil.getSession();
//            List<HourEntity> hours = session.createCriteria(HourEntity.class).list();
//            session.close();

//            JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(hours);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<String, Object>(), new JREmptyDataSource());

            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            response.setContentLength(baos.size());
            baos.writeTo(servletOutputStream);
            fileInputStream.close();
            bufferedInputStream.close();
        } catch (JRException e) {
            e.printStackTrace();
        } finally {
            servletOutputStream.flush();
            servletOutputStream.close();
            baos.close();
        }
    }
}