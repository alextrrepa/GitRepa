package data.controllers.report_delegation;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReportActions {
    private final static Logger log = Logger.getLogger(ReportActions.class);

    public void getIndexPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("report/report.jsp").forward(request, response);
    }

    public void hourReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream servletOutputStream = null;

        JasperReport jasperReport;
        JasperDesign jasperDesign;
        JasperPrint jasperPrint;

        ServletContext context = request.getServletContext();
        String reportLocation = context.getRealPath("WEB-INF");

        try (InputStream fileInputStream = new FileInputStream(reportLocation + "/HourReport.jrxml")) {
            jasperDesign = JRXmlLoader.load(fileInputStream);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JREmptyDataSource());

            servletOutputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        } catch (JRException e) {
            e.printStackTrace();
        } finally {
            if (servletOutputStream != null) {
                servletOutputStream.flush();
                servletOutputStream.close();
            }
        }
       /* ServletOutputStream servletOutputStream = response.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream fileInputStream;
        BufferedInputStream bufferedInputStream;
        try {
            ServletContext context = request.getServletContext();
            String reportLocation = context.getRealPath("WEB-INF");

            fileInputStream = new FileInputStream(reportLocation + "/HourReport.jasper");
            bufferedInputStream = new BufferedInputStream(fileInputStream);


//            JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(hours);

            CommonOperationsHibernateDao<TagData, Long> tagDao = new DataItemHibernateDao<>(TagData.class);
            List<TagData> tags = tagDao.getAll();

            JasperDesign jasperDesign = new JasperDesign();
            JRDesignStyle columnHeaderStyle = new JRDesignStyle();
            columnHeaderStyle.setName("Arial_Normal");
            columnHeaderStyle.setDefault(true);
            columnHeaderStyle.setFontName("Arial");
//            columnHeaderStyle.setFontSize(8);
            columnHeaderStyle.setPdfFontName("Helvetica");
            columnHeaderStyle.setPdfEncoding("Cp1252");
            columnHeaderStyle.setPdfEmbedded(false);
            jasperDesign.addStyle(columnHeaderStyle);

            for (TagData t : tags) {
                JRDesignField field = new JRDesignField();
                field.setName(t.getColumnName());
                field.setValueClass(String.class);
                jasperDesign.addField(field);
            }

            JRDesignBand columnHeaderBand = new JRDesignBand();
            JRDesignBand detailBand = new JRDesignBand();
            columnHeaderBand.setHeight(50);
            detailBand.setHeight(25);

            int offset = 30;
            int width = 0;
            for (TagData t : tags) {
//                String name = (String) showList.get(i);
                JRDesignStaticText columnHeader = new JRDesignStaticText();
                JRDesignTextField reportData = new JRDesignTextField();
                columnHeader.setX(offset);
                columnHeader.setY(10);
                reportData.setX(offset);
                reportData.setY(10);

                width = (t.getColumnName()).length() * 8;
                columnHeader.setWidth(width);
                reportData.setWidth(width);
                offset = offset + width + 10;


*//*
                if (output.equals(“pdf”)) {
                    if (offset > pageWidth – 30) {
                        break;
                    }
                }
*//*
                columnHeader.setStyle(columnHeaderStyle);
                reportData.setStyle(columnHeaderStyle);
                columnHeader.setText(t.getColumnName());

                JRDesignExpression expression = new JRDesignExpression();
                expression.setValueClass(java.lang.String.class);
                expression.setText("$F{" + t.getColumnName() + "}");
                reportData.setExpression(expression);

                columnHeaderBand.addElement(columnHeader);

                detailBand.addElement(reportData);
            }

            jasperDesign.setColumnHeader(columnHeaderBand);

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
        }*/
    }
}