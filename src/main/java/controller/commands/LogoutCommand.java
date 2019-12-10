package controller.commands;

import controller.ActionCommand;
import utilities.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getProperty("index_page");

        request.getSession().invalidate();

        return page;
    }
}
