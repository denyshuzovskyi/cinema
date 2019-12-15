package controller.commands;

import controller.ActionCommand;
import controller.Page;
import utilities.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    @Override
    public Page execute(HttpServletRequest request) {
        Page page = new Page(ConfigurationManager.getProperty("index_page"), Page.WayToSend.redirect);

        request.getSession().invalidate();

        return page;
    }
}
