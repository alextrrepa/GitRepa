package auth.controllers;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    private final static Logger log = Logger.getLogger(LoginServlet.class);
    private final String user = "admin";
    private final String pass = "admin";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String password = request.getParameter("password");
        log.info(username + ":::" + password);

        if (user.equals(username) && pass.equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", "Pankaj");
            session.setMaxInactiveInterval(30 * 60);
            Cookie userName = new Cookie("user", username);
            userName.setMaxAge(30 * 60);
            response.addCookie(userName);
            response.sendRedirect("loginSuccess.jsp");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            dispatcher.include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
