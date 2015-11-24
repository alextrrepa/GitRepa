package data.controllers.report_delegation;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.type.CalculationEnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
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
import java.util.*;

public class ReportActions {
    private final static Logger log = Logger.getLogger(ReportActions.class);

    public void getIndexPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("report/report.jsp").forward(request, response);
    }

    public void hourReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JasperDesign jasperDesign = null;
        JasperReport jasperReport = null;
/*
        JasperReport jasperReport = null;
        try {
            jasperReport = JasperCompileManager
                    .compileReport(jasperDesign);
        } catch (JRException e) {
            e.printStackTrace();
        }
*/

        JRBeanCollectionDataSource jrDataSource = prepareDataSource();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("startDate", new Date());
        params.put("endDate", new Date());

        JasperPrint jasperPrint = null;
        response.setContentType("application/pdf");
        response.setCharacterEncoding("UTF-8");
        ServletContext context = request.getServletContext();
        ServletOutputStream servletOutputStream = response.getOutputStream();
        String reportLocation = context.getRealPath("WEB-INF");

        try (InputStream fileInputStream = new FileInputStream(reportLocation + "/HourReport.jrxml")) {
            jasperDesign = createDesign();
            jasperDesign = JRXmlLoader.load(fileInputStream);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, params, jrDataSource);

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

 /*       response.setContentType("application/pdf");
        response.setCharacterEncoding("UTF-8");
        ServletContext context = request.getServletContext();
        String reportLocation = context.getRealPath("WEB-INF");

        JasperReport jasperReport;
        JasperDesign jasperDesign = new JasperDesign();
        JasperPrint jasperPrint;

        JRDesignBand columnHeaderBand = new JRDesignBand();
        columnHeaderBand.setHeight(50);

        JRDesignStyle columnHeaderStyle = new JRDesignStyle();
        columnHeaderStyle.setName("Arial_Normal");
        columnHeaderStyle.setDefault(true);
        columnHeaderStyle.setFontName("Arial");
        columnHeaderStyle.setFontSize(8);
        columnHeaderStyle.setPdfFontName("Helvetica");
        columnHeaderStyle.setPdfEncoding("UTF-8");
        columnHeaderStyle.setPdfEmbedded(false);

        CommonOperationsHibernateDao<TagData, Long> columnDao = new DataItemHibernateDao<>(TagData.class);
        List<TagData> tags = columnDao.getAll();

        int offset = 30;
        int width = 0;
        for (TagData t : tags) {
            JRDesignStaticText columnHeader = new JRDesignStaticText();
            columnHeader.setX(offset);
            columnHeader.setY(10);
            columnHeader.setText(t.getColumnName());
            columnHeader.setWidth(50);
            offset += width + 10;
            columnHeaderBand.addElement(columnHeader);
        }

//        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(tags);

        ServletOutputStream servletOutputStream = null;
        try (InputStream fileInputStream = new FileInputStream(reportLocation + "/HourReport.jrxml")) {
            jasperDesign = JRXmlLoader.load(fileInputStream);
            jasperDesign.addStyle(columnHeaderStyle);
            jasperDesign.setColumnHeader(columnHeaderBand);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<String, Object>(), new JREmptyDataSource());

            servletOutputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        } catch (JRException e) {
            e.printStackTrace();
        } finally {
            if (servletOutputStream != null) {
                servletOutputStream.flush();
                servletOutputStream.close();
            }
        }*/
    }

    private JRBeanCollectionDataSource prepareDataSource() {
        List<Map<String, ?>> preparedData = new ArrayList<Map<String, ?>>();
        Map<String, Object> map;
        map = new HashMap<String, Object>();
        map.put("name", "Первый");
        map.put("value", 10);
        // В реальности нужно будет добавлять необходимые поля, сколько нужно,
        // динамически, в зависимости от параметров и данных.
        preparedData.add(map);
        map = new HashMap<String, Object>();
        map.put("name", "Второй");
        map.put("value", 4);
        // В реальности нужно будет добавлять необходимые поля, сколько нужно,
        // динамически, в зависимости от параметров и данных.
        preparedData.add(map);
        return new JRBeanCollectionDataSource(preparedData);
//        return new JRMapCollectionDataSource(preparedData);
    }

    private JasperDesign createDesign() throws JRException {
        // Эквивалентно StaticText в JasperStudio
        JRDesignStaticText staticText = null;

        // Эквивалентно TextField в JasperStudio
        JRDesignTextField textField = null;

        // Band. Details, Summary, Title и другие.
        JRDesignBand band = null;

        // Вычисляемое выражение. Для записи значений в JRDesignTextField.
        JRDesignExpression expression = null;

        // Для рисования линий.
        @SuppressWarnings("unused")
        JRDesignLine line = null;

        // Для добавления полей в отчёт.
        JRDesignField field = null;

        // Можно создавать условные стили.
        @SuppressWarnings("unused")
        JRDesignConditionalStyle conditionalStyle = null;

        // Рамка вокруг ячейки.
        JRLineBox lineBox = null;

        // Вычисляемое значение. Можно подсчитать, например сумму.
        JRDesignVariable variable = null;

        int x;
        int y;
        final int ROW_HEIGHT = 11;
        final int COLUMN_WIDTH = 60;

        JasperDesign jasperDesign = new JasperDesign();
        jasperDesign.setName("dynamicColumns");
        jasperDesign.setPageWidth(600);
        jasperDesign.setPageHeight(500);
        jasperDesign.setColumnWidth(COLUMN_WIDTH);
        jasperDesign.setColumnSpacing(0);
        jasperDesign.setLeftMargin(40);
        jasperDesign.setRightMargin(40);
        jasperDesign.setTopMargin(40);
        jasperDesign.setBottomMargin(40);
        jasperDesign.setIgnorePagination(true);
        jasperDesign
                .setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

        JRDesignStyle normalStyle = new JRDesignStyle();
        normalStyle.setName("normal");
        normalStyle.setDefault(true);
        normalStyle.setFontName("DejaVu Sans");
        normalStyle.setFontSize(8.5f);
        lineBox = normalStyle.getLineBox();
        lineBox.getTopPen().setLineWidth(0.5f);
        lineBox.getRightPen().setLineWidth(0.5f);
        lineBox.getBottomPen().setLineWidth(0.5f);
        lineBox.getLeftPen().setLineWidth(0.5f);
        jasperDesign.addStyle(normalStyle);

        JRDesignStyle headerStyle = new JRDesignStyle();
        headerStyle.setName("header");
        headerStyle.setDefault(true);
        headerStyle.setFontName("DejaVu Sans");
        headerStyle.setFontSize(8.5f);
        headerStyle.setBold(true);
        lineBox = headerStyle.getLineBox();
        lineBox.getTopPen().setLineWidth(0.5f);
        lineBox.getRightPen().setLineWidth(0.5f);
        lineBox.getBottomPen().setLineWidth(0.5f);
        lineBox.getLeftPen().setLineWidth(0.5f);
        jasperDesign.addStyle(headerStyle);

        // Параметры отчёта
        JRDesignParameter startDateParameter = new JRDesignParameter();
        startDateParameter.setName("startDate");
        startDateParameter.setValueClass(java.util.Date.class);
        jasperDesign.addParameter(startDateParameter);

        JRDesignParameter endDateParameter = new JRDesignParameter();
        endDateParameter.setName("endDate");
        endDateParameter.setValueClass(java.util.Date.class);
        jasperDesign.addParameter(endDateParameter);

        // Поля отчёта.
        field = new JRDesignField();
        field.setName("name");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("value");
        field.setValueClass(java.lang.Integer.class);
        jasperDesign.addField(field);
        // В случае отчёта с динамическими полями пробегаемся по количеству
        // полей и добавляем JRDesignField для каждого с уникальным именем.

        // Подсчитываем сумму
        variable = new JRDesignVariable();
        variable.setResetType(ResetTypeEnum.REPORT);
        variable.setValueClass(java.lang.Integer.class);
        expression = new JRDesignExpression();
        expression.setText("$F{value}");
        variable.setExpression(expression);
        variable.setCalculation(CalculationEnum.SUM);
        expression = new JRDesignExpression();
        expression.setText("0");
        variable.setInitialValueExpression(expression);
        variable.setName("summary");
        jasperDesign.addVariable(variable);

        // Title band
        band = new JRDesignBand();
        // добавляем нужные элементы в band.
        // Можно добавлять JRDesignTextField-ы и JRDesignStaticField-ы,
        // картинки и всё, что угодно. Мы пропустим для простоты.
        jasperDesign.setTitle(band);

        // Заголовки колонок.
        x = 0;
        y = 0;
        band = new JRDesignBand();
        band.setHeight(ROW_HEIGHT);
        staticText = new JRDesignStaticText();
        staticText.setX(x);
        staticText.setY(y);
        staticText.setWidth(COLUMN_WIDTH);
        staticText.setHeight(ROW_HEIGHT);
        staticText.setStyle(headerStyle);
        staticText.setText("Название");
        band.addElement(staticText);
        x += staticText.getWidth();

        staticText = new JRDesignStaticText();
        staticText.setX(x);
        staticText.setY(y);
        staticText.setWidth(COLUMN_WIDTH);
        staticText.setHeight(ROW_HEIGHT);
        staticText.setStyle(headerStyle);
        staticText.setText("Значение");
        band.addElement(staticText);
        x += staticText.getWidth();
        jasperDesign.setColumnHeader(band);

        // Detail band (данные)
        band = new JRDesignBand();
        band.setHeight(ROW_HEIGHT);
        x = 0;
        y = 0;
        textField = new JRDesignTextField();
        textField.setX(x);
        textField.setY(y);
        textField.setWidth(COLUMN_WIDTH);
        textField.setHeight(ROW_HEIGHT);
        expression = new JRDesignExpression();
        expression.setText("$F{name}");
        textField.setExpression(expression);
        textField.setStyle(normalStyle);
        band.addElement(textField);
        x += textField.getWidth();

        textField = new JRDesignTextField();
        textField.setX(x);
        textField.setY(y);
        textField.setWidth(COLUMN_WIDTH);
        textField.setHeight(ROW_HEIGHT);
        expression = new JRDesignExpression();
        expression.setText("$F{value}");
        textField.setExpression(expression);
        textField.setStyle(normalStyle);
        band.addElement(textField);
        x += textField.getWidth();
        // DetailsBand добавляется немного странно, да...
        ((JRDesignSection) jasperDesign.getDetailSection()).addBand(band);

        // Column footer
        band = new JRDesignBand();
        jasperDesign.setColumnFooter(band);

        // Подвал страницы
        band = new JRDesignBand();
        jasperDesign.setPageFooter(band);

        // Summary band
        band = new JRDesignBand();
        band.setHeight(ROW_HEIGHT);
        x = 0;
        y = 0;

        staticText = new JRDesignStaticText();
        staticText.setX(x);
        staticText.setY(y);
        staticText.setWidth(COLUMN_WIDTH);
        staticText.setHeight(ROW_HEIGHT);
        staticText.setStyle(headerStyle);
        staticText.setText("ИТОГО:");
        band.addElement(staticText);
        x += staticText.getWidth();

        textField = new JRDesignTextField();
        textField.setX(x);
        textField.setY(y);
        textField.setWidth(COLUMN_WIDTH);
        textField.setHeight(ROW_HEIGHT);
        expression = new JRDesignExpression();
        expression.setText("$V{summary}");
        textField.setExpression(expression);
        textField.setStyle(headerStyle);
        band.addElement(textField);
        x += textField.getWidth();
        jasperDesign.setSummary(band);
        return jasperDesign;
    }
}