package ru.scada.controllers.report_delegation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoReport {
    private Command command;

    public DoReport(Command command) {
        this.command = command;
    }

    public void makeCommand(HttpServletRequest request, HttpServletResponse response) {
        command.execute(request, response);
    }
}
