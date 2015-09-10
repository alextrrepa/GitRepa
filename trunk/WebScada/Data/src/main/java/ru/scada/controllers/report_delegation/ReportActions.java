package ru.scada.controllers.report_delegation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.scada.dao.GenericDao;
import ru.scada.dao.ItemDao;
import ru.scada.model.HourEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ReportActions {
    public void showHoursData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GenericDao<HourEntity, Long> dao = new ItemDao<>();
        List<HourEntity> hours = dao.showHoursData();
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(hours);
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.close();
    }
}