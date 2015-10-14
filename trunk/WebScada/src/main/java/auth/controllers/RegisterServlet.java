package auth.controllers;

import auth.entities.UserEntity;
import auth.entities.UserRoleEntity;
import dao.AuthItemHibernateDao;
import dao.CommonOperationsDaoIF;
import org.apache.log4j.Logger;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private final static Logger log = Logger.getLogger(RegisterServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("selectRole");
        try {
            registrate(username, password, role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void registrate(String username, String plainPassword, String role) throws Exception {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        generatePassword(user, plainPassword);
        CommonOperationsDaoIF<UserEntity, Long> authDao = new AuthItemHibernateDao<>(UserEntity.class);
        authDao.create(user);

        CommonOperationsDaoIF<UserRoleEntity, Long> roleDao = new AuthItemHibernateDao<>(UserRoleEntity.class);
        UserRoleEntity roleEntity = new UserRoleEntity();
        roleEntity.setRolename(role);
        roleDao.create(roleEntity);
    }

    private void generatePassword(UserEntity user, String plainPassword) {
        RandomNumberGenerator random = new SecureRandomNumberGenerator();
        Object salt = random.nextBytes();
        String hashedPasswordBase64 = new Sha256Hash(plainPassword, salt, 1024).toBase64();
        user.setPassword(hashedPasswordBase64);
        user.setSalt(salt.toString());
    }
}