package controller.commands;

import controller.ActionCommand;
import controller.Page;
import controller.services.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request) {
        UserService userService = new UserService();
        return userService.registerUser(request);
    }
}
