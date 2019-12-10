package model.dao;

import model.dao.mysql.MySQLDAOFactory;

public abstract class DAOFactory {
    // List of DAO types supported by the factory
    public static final int MySQl = 1;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract UserDAO getUserDAO();
    public abstract FilmDAO getFilmDAO();
    public abstract PosterDAO getPosterDAO();

    //TODO Add getConnection() method or not

    public static DAOFactory getDAOFactory(int whichFactory) {

        switch (whichFactory) {
            case MySQl:
                return new MySQLDAOFactory();
            default:
                return null;
        }
    }

}
