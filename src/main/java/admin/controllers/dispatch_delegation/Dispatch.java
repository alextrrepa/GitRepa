package admin.controllers.dispatch_delegation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatch {
    public void dispatchModbus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/modbus_page.jsp");
        requestDispatcher.forward(request, response);
    }

    public void dispatchAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/data.jsp");
        requestDispatcher.forward(request, response);
    }
}