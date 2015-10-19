package auth.controllers;

import auth.entities.UserEntity;
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
        boolean blocked;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String b = request.getParameter("blocked");
        if (b == null) {
            blocked = false;
        } else {
            blocked = true;
        }
        String role = request.getParameter("selectRole");
        String description = request.getParameter("description");
//        log.info(username);
//        log.info(password);
//        log.info(blocked);
//        log.info(role);
//        log.info(description);
        try {
            registrate(username, password, blocked, role, description);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void registrate(String username, String plainPassword, Boolean blocked, String role, String description) throws Exception {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        generatePassword(user, plainPassword);
        user.setLocked(blocked);


//        CommonOperationsDaoIF<UserEntity, Long> authDao = new AuthItemHibernateDao<>(UserEntity.class);
//        authDao.create(user);

//        CommonOperationsDaoIF<UserRoleEntity, Long> roleDao = new AuthItemHibernateDao<>(UserRoleEntity.class);
//        UserRoleEntity roleEntity = new UserRoleEntity();
//        roleEntity.setRolename(role);
//        roleDao.create(roleEntity);
    }

    private void generatePassword(UserEntity user, String plainPassword) {
        RandomNumberGenerator random = new SecureRandomNumberGenerator();
        Object salt = random.nextBytes();
        String hashedPasswordBase64 = new Sha256Hash(plainPassword, salt, 1024).toBase64();
        user.setPassword(hashedPasswordBase64);
        user.setSalt(salt.toString());
    }
}