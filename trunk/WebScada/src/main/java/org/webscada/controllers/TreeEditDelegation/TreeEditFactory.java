package org.webscada.controllers.TreeEditDelegation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class TreeEditFactory {
    public void createContext(HttpServletRequest request, HttpServletResponse response) {
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            System.out.println(name);
        }
    }
}
