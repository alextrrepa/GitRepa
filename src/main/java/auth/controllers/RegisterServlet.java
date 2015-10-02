package auth.controllers;

import auth.dao.UserDao;
import auth.entities.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("user");
        String pass = request.getParameter("password");
        String email = request.getParameter("email");
        if (login == null || pass == null) {
            request.setAttribute("message", "wrong parameters");
        } else {
            registrate(login, pass, email);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void registrate(String login, String plainPassword, String email) {
        User user = new User();
        user.setUsername(login);
        user.setEmail(email);
        generatePassword(user, plainPassword);
        UserDao uDao = new UserDao();
        uDao.create(user);
    }

    private void generatePassword(User user, String plainPassword) {
        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        Object salt = rng.nextBytes();
        String hashedPasswordBase64 = new Sha256Hash(plainPassword, salt, 1024).toBase64();
        user.setPassword(hashedPasswordBase64);
        user.setSalt(salt.toString());
    }
}