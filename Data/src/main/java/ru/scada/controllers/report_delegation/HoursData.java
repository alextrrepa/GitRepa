package ru.scada.controllers.report_delegation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HoursData implements Command {
    private ReportActions actions;

    public HoursData(ReportActions actions) {
        this.actions = actions;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        actions.showHoursData(request, response);
    }
}
