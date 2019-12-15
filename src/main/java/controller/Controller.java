package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
@MultipartConfig
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Page page;

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(req);

        page = command.execute(req);

        if(page != null){
            if (page.getWayToSend() == Page.WayToSend.redirect) {
                resp.sendRedirect(page.getPagePath());
            } else { //Page.WayToSend.forward
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page.getPagePath());
                dispatcher.forward(req, resp);
            }
        }else {

        }
    }
}
