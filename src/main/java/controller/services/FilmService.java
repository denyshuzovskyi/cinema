package controller.services;

import model.dao.DAOFactory;
import model.dao.FilmDAO;
import model.entities.Film;
import utilities.ConfigurationManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

public class FilmService {
    private DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQl);
    private FilmDAO filmDAO = MySQLDAOFactory.getFilmDAO();

    public String uploadFilm (HttpServletRequest request){
        String page;

        Film film;

        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String director = request.getParameter("director");
        String writer = request.getParameter("writer");
        String actors = request.getParameter("actors");
        String country = request.getParameter("country");
        String release_date = request.getParameter("release_date");

        Integer hours = Integer.valueOf(request.getParameter("hours"));
        Integer minutes = Integer.valueOf(request.getParameter("minutes"));
        Integer duration_minutes = hoursToMinutes(hours) + minutes;

        Integer age_limit = Integer.valueOf(request.getParameter("age_limit"));
        String description = request.getParameter("description");

        film = new Film(title, genre, director, writer, actors, country, release_date, duration_minutes, age_limit, description);

        try {
            Part file = request.getPart("poster");
            film.getPoster().setName(file.getSubmittedFileName());
            film.getPoster().setImage(file.getInputStream().readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        //TODO ensure that received data is valid

        try {
            filmDAO.create(film);
        }catch (Exception e){
            System.err.println(e);
            page = ConfigurationManager.getProperty("film_upload_page");
            return page;
        }

        page = ConfigurationManager.getProperty("index_page");

        return page;
    }

    public String getFilmList(HttpServletRequest request){
        String page = ConfigurationManager.getProperty("index_page");
        List<Film> films = null;

        try {
            films = filmDAO.list();
        }catch (Exception e){
            System.err.println(e);
        }

        request.setAttribute("films", films);

        return page;
    }

    private Integer hoursToMinutes (Integer hours){
        return hours * 60;
    }
}
