package controller.commands;

import controller.ActionCommand;
import controller.Page;
import utilities.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {

    @Override
    public Page execute(HttpServletRequest request) {
        Page page = new Page(ConfigurationManager.getProperty("index_page"), Page.WayToSend.redirect);
        return page;
    }
}
