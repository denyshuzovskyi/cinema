package model.dao.mysql;

import model.dao.FilmDAO;
import model.dao.HelperDAO;
import model.dao.exceptions.DAOException;
import model.entities.Film;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLFilmDAO implements FilmDAO, HelperDAO {

    // Constants ----------------------------------------------------------------------------------

    private static final String SQL_INSERT_FILM =
            "INSERT INTO cinema.film (title, genre, director, writer, actors, country, release_date, duration_minutes, age_limit, description, poster_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_INSERT_POSTER =
            "INSERT INTO cinema.poster (name, image) VALUES (?, ?)";

    private static final String SQL_LIST_ORDER_BY_RELEASE_DATE =
            "SELECT cinema.film.id, cinema.film.title, cinema.film.genre, cinema.film.director, " +
                    "cinema.film.writer, cinema.film.actors, cinema.film.country, cinema.film.release_date, " +
                    "cinema.film.duration_minutes, cinema.film.age_limit, cinema.film.description, cinema.film.poster_id, " +
                    "cinema.poster.name, cinema.poster.image " +
                    "FROM cinema.film " +
                    "LEFT JOIN cinema.poster ON cinema.film.poster_id=cinema.poster.id ORDER BY cinema.film.release_date";

    // Vars ---------------------------------------------------------------------------------------

    private MySQLDAOFactory daoFactory;

    // Constructors -------------------------------------------------------------------------------

    public MySQLFilmDAO(MySQLDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    // Actions ------------------------------------------------------------------------------------

    @Override
    public void create(Film film) throws IllegalArgumentException, DAOException {
        if (film.getPoster().getId() != null) {
            throw new IllegalArgumentException("Film's poster is already created, the user ID is not null.");
        }
        if (film.getId() != null) {
            throw new IllegalArgumentException("Film is already created, the user ID is not null.");
        }

        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            connection.setAutoCommit(false);

            boolean successFlag;

            //poster
            Object[] poster_values = {
                    film.getPoster().getName(),
                    film.getPoster().getImage()
            };

            successFlag = insertPoster(connection, SQL_INSERT_POSTER, poster_values, film.getPoster());

            if (successFlag == true) {
                //film
                Object[] film_values = {
                        film.getTitle(),
                        film.getGenre(),
                        film.getDirector(),
                        film.getWriter(),
                        film.getActors(),
                        film.getCountry(),
                        film.getRelease_date(),
                        film.getDuration_minutes(),
                        film.getAge_limit(),
                        film.getDescription(),
                        film.getPoster().getId()
                };

                successFlag = insertFilm(connection, SQL_INSERT_FILM, film_values, film);
            }

            //Commit Transactions
            if (successFlag == true) {
                connection.commit();
            } else {
                connection.rollback();
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
            }
        }
    }

    @Override
    public List<Film> list() throws DAOException {
        List<Film> films = new ArrayList<>();

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_RELEASE_DATE);
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                films.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return films;
    }

    private boolean insertPoster(Connection connection, String SQL_QUERY, Object[] values, Film.Poster poster){
        boolean successFlag = false;

        try (PreparedStatement statement = prepareStatement(connection, SQL_QUERY, true, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                return successFlag;
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    poster.setId(generatedKeys.getLong(1));
                } else {
                    return successFlag;
                }
            }
            successFlag = true;
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return successFlag;
    }

    private boolean insertFilm(Connection connection, String SQL_QUERY, Object[] values, Film film){
        boolean successFlag = false;

        try (PreparedStatement statement = prepareStatement(connection, SQL_QUERY, true, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                return successFlag;
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    film.setId(generatedKeys.getLong(1));
                } else {
                    return successFlag;
                }
            }
            successFlag = true;
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return successFlag;
    }



    // Helpers ------------------------------------------------------------------------------------

    private static Film map(ResultSet resultSet) throws SQLException {
        Film film = new Film();
        film.setId(resultSet.getLong("id"));
        film.setTitle(resultSet.getString("title"));
        film.setGenre(resultSet.getString("genre"));
        film.setDirector(resultSet.getString("director"));
        film.setWriter(resultSet.getString("writer"));
        film.setActors(resultSet.getString("actors"));
        film.setCountry(resultSet.getString("country"));
        film.setRelease_date(resultSet.getString("release_date"));
        film.setDuration_minutes(resultSet.getInt("duration_minutes"));
        film.setAge_limit(resultSet.getInt("age_limit"));
        film.setDescription(resultSet.getString("description"));
        film.getPoster().setId(resultSet.getLong("poster_id"));
        film.getPoster().setName(resultSet.getString("name"));
        film.getPoster().setImage(resultSet.getBytes("image"));
        return film;
    }
}
