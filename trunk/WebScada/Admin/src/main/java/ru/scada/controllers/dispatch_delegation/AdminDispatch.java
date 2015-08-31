package ru.scada.controllers.dispatch_delegation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminDispatch implements Command {
    private Dispatch dispatch;

    public AdminDispatch(Dispatch dispatch) {
        this.dispatch = dispatch;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch.dispatchAdmin(request, response);
    }
}
