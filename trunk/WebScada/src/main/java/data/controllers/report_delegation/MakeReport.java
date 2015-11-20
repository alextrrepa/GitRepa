package data.controllers.report_delegation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeReport {
    private Command command;

    public MakeReport(Command command) {
        this.command = command;
    }

    public void makeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        command.execute(request, response);
    }
}
