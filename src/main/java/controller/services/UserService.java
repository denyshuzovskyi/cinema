package controller.services;

import controller.Page;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.entities.User;
import utilities.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class UserService {
    private DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQl);
    private UserDAO userDAO = MySQLDAOFactory.getUserDAO();


    public Page registerUser (HttpServletRequest request){
        Page page;

        String surname = request.getParameter("surname");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User.UserRole role = User.UserRole.user;

        //TODO ensure that received data is valid
        //request.setAttribute("registration_error_message", "incorrect data");

        User user = new User(surname, name, birthday, email, password, role);

        try {
            userDAO.create(user);
        }catch (Exception e){
            System.err.println(e);

            page = new Page(ConfigurationManager.getProperty("user_registration_page"), Page.WayToSend.forward);

            return page;
        }

        //delete user's password
        user.setPassword("");
        request.getSession().setAttribute("user", user);
        page = new Page( ConfigurationManager.getProperty("index_page"), Page.WayToSend.redirect);

        return page;
    }

    public Page authenticateUser (HttpServletRequest request) {
        Page page;

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //TODO ensure that received data is valid

        User user;

        try {
            user = userDAO.find(email, password);
        } catch (Exception e) {
            System.err.println(e);

            page = new Page(ConfigurationManager.getProperty("login_page"), Page.WayToSend.forward);

            return page;
        }

        if (user != null) {
            user.setPassword("");
            request.getSession().setAttribute("user", user);
            page = new Page(ConfigurationManager.getProperty("index_page"), Page.WayToSend.redirect);
        } else {
            request.setAttribute("authentication_error_message", "Incorrect email or password");
            page = new Page(ConfigurationManager.getProperty("login_page"), Page.WayToSend.forward);
        }

        return page;
    }
}
