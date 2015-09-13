package ru.scada.controllers.report_delegation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import ru.scada.dao.GenericDao;
import ru.scada.dao.ItemDao;
import ru.scada.model.HourEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ReportActions {
    private final static Logger log = Logger.getLogger(ReportActions.class);

    public void showHoursData(HttpServletRequest request, HttpServletResponse response) {
        int startPageIndex = Integer.parseInt(request.getParameter("jtStartIndex"));
        int numRecordsPerPage = Integer.parseInt(request.getParameter("jtPageSize"));

        response.setContentType("application/json");

        GenericDao<HourEntity, Long> dao = new ItemDao<>(HourEntity.class);
        List<HourEntity> hours = dao.showHoursData(/*startPageIndex, numRecordsPerPage*/);
        Long counts = dao.getCount();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        JsonElement element = gson.toJsonTree(hours, new TypeToken<List<HourEntity>>() {
        }.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        String listData = jsonArray.toString();
        listData = "{\"Result\":\"OK\",\"Records\":" + listData + ",\"TotalRecordCount\":" + counts + "}";
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (Exception e) {
            String error = "{\"Result\":\"ERROR\",\"Message\":" + e.getMessage() + "}";
            try {
                PrintWriter out = response.getWriter();
                out.write(error);
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        writer.write(listData);
        writer.close();
    }
}