package ru.scada.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import ru.scada.dao.ItemDAO;
import ru.scada.dao.ItemDAOHibernate;
import ru.scada.model.TagEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TagNamesController extends HttpServlet {
    private static final Logger log = Logger.getLogger(TagNamesController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performTask(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performTask(request, response);
    }

    private void performTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ItemDAO<TagEntity, Long> tagsDao = new ItemDAOHibernate(TagEntity.class);
        List<TagEntity> tags = tagsDao.getTags();
        List<String> names = new ArrayList<>();
        for (TagEntity t : tags) {
            names.add(t.getName());
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String resp = gson.toJson(names);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(resp);
    }
}
