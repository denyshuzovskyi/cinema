package model.dao.mysql;

import model.dao.HelperDAO;
import model.dao.PosterDAO;
import model.dao.exceptions.DAOException;
import model.entities.Poster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLPosterDAO implements PosterDAO, HelperDAO {

    // Constants ----------------------------------------------------------------------------------

    private static final String SQL_INSERT =
            "INSERT INTO cinema.poster (name, image) VALUES (?, ?)";

    // Vars ---------------------------------------------------------------------------------------

    private MySQLDAOFactory daoFactory;

    // Constructors -------------------------------------------------------------------------------

    public MySQLPosterDAO(MySQLDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    // Actions ------------------------------------------------------------------------------------

    @Override
    public void create(Poster poster) throws IllegalArgumentException, DAOException {
//        if (poster.getId() != null) {
//            throw new IllegalArgumentException("User is already created, the user ID is not null.");
//        }
//
//        Object[] values = {
//                poster.getName(),
//                poster.getImage()
//        };
//
//        try (
//                Connection connection = daoFactory.getConnection();
//                PreparedStatement statement = prepareStatement(connection, SQL_INSERT, true, values);
//        ) {
//            int affectedRows = statement.executeUpdate();
//            if (affectedRows == 0) {
//                throw new DAOException("Creating user failed, no rows affected.");
//            }
//
//            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    poster.setId(generatedKeys.getLong(1));
//                } else {
//                    throw new DAOException("Creating user failed, no generated key obtained.");
//                }
//            }
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        }
    }



    // Helpers ------------------------------------------------------------------------------------

//    private static Poster map(ResultSet resultSet) throws SQLException {
//        Poster poster = new Poster();
//        poster.setId(resultSet.getLong("id"));
//        poster.setName(resultSet.getString("name"));
//        poster.setImage(resultSet.getBytes("image")); //or blob if this variant will net work
//        return poster;
//    }
}
