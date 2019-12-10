package model.dao;

import model.dao.exceptions.DAOException;
import model.entities.Film;
import java.util.List;

public interface FilmDAO {
    public void create(Film film) throws IllegalArgumentException, DAOException;
    public List<Film> list() throws DAOException;
}
