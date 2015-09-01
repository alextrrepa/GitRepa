package ru.scada.controllers;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.hibernate.Session;
import ru.scada.model.HourEntity;
import ru.scada.util.SessionUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ReportController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
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
        }
    }
}
