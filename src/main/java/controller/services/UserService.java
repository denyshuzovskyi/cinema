package controller.services;

import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.entities.User;
import utilities.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class UserService {
    private DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQl);
    private UserDAO userDAO = MySQLDAOFactory.getUserDAO();


    public String registerUser (HttpServletRequest request){
        String page;

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

            page = ConfigurationManager.getProperty("user_registration_page");
            return page;
        }

        //delete user's password
        user.setPassword("");
        request.getSession().setAttribute("user", user);
        page = ConfigurationManager.getProperty("result_page");

        return page;
    }

    public String authenticateUser (HttpServletRequest request) {
        String page;

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //TODO ensure that received data is valid

        User user;

        try {
            user = userDAO.find(email, password);
        } catch (Exception e) {
            System.err.println(e);

            page = ConfigurationManager.getProperty("login_page");
            return page;
        }

        if (user != null) {
            user.setPassword("");
            request.getSession().setAttribute("user", user);
            page = ConfigurationManager.getProperty("index_page");
        } else {
            request.setAttribute("authentication_error_message", "Incorrect email or password");
            page = ConfigurationManager.getProperty("login_page");
        }

        return page;
    }
}
