package model.dao.mysql;

import model.dao.*;

import java.sql.Connection;

public class MySQLDAOFactory extends DAOFactory {

    public Connection getConnection(){
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        return pool.getConnection();
    }

    @Override
    public UserDAO getUserDAO() {
        return new MySQLUserDAO(this);
    }

    @Override
    public FilmDAO getFilmDAO() {
        return new MySQLFilmDAO(this);
    }
}
