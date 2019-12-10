package model.dao;

import model.dao.exceptions.DAOException;
import model.entities.Poster;

public interface PosterDAO {
    public void create(Poster poster) throws IllegalArgumentException, DAOException;
}
