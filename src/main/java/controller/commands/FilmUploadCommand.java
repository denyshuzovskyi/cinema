package controller.commands;

import controller.ActionCommand;
import controller.services.FilmService;
import javax.servlet.http.HttpServletRequest;

public class FilmUploadCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        FilmService filmService = new FilmService();
        return filmService.uploadFilm(request);
    }
}
