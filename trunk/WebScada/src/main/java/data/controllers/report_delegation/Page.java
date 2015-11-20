package data.controllers.report_delegation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Page implements Command {
    private ReportActions actions;

    public Page(ReportActions actions) {
        this.actions = actions;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        actions.getIndexPage(request, response);
    }
}
