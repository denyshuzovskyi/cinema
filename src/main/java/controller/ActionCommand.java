package controller;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    Page execute(HttpServletRequest request);
}
