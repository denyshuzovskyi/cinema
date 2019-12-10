package controller.commands;

import controller.ActionCommand;
import controller.services.UserService;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        return userService.authenticateUser(request);
    }
}
