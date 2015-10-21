package auth.controllers;

import auth.entities.RoleEntity;
import auth.entities.UserEntity;
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
import java.util.HashSet;
import java.util.Set;

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
        Long roleId = Long.valueOf(request.getParameter("selectRole"));
        try {
            registrate(username, password, blocked, roleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("roles", request.getAttribute("roles"));
        request.getRequestDispatcher("user/register.jsp").include(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void registrate(String username, String plainPassword, Boolean blocked, Long roleId) throws Exception {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        generatePassword(user, plainPassword);
        user.setLocked(blocked);

        CommonOperationsDaoIF<RoleEntity, Long> roleDao = new AuthItemHibernateDao<>(RoleEntity.class);
        RoleEntity role = roleDao.getById(roleId);
        Set<RoleEntity> sList = new HashSet<>();
        sList.add(role);
        user.setRoles(sList);

        CommonOperationsDaoIF<UserEntity, Long> regDao = new AuthItemHibernateDao<>(UserEntity.class);
        regDao.create(user);
//
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