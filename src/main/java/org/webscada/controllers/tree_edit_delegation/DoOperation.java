package org.webscada.controllers.tree_edit_delegation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoOperation {
    private Command command;

    public DoOperation(Command command) {
        this.command = command;
    }

    public String makeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        return command.execute(request, response);
    }
}
