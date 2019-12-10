package model.dao.mysql;

import model.dao.HelperDAO;
import model.dao.UserDAO;
import model.dao.exceptions.DAOException;
import model.entities.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserDAO implements UserDAO, HelperDAO {

    // Constants ----------------------------------------------------------------------------------

    private static final String SQL_FIND_BY_ID =
            "SELECT * FROM cinema.user WHERE id = ?";
    private static final String SQL_FIND_BY_EMAIL_AND_PASSWORD =
            "SELECT * FROM cinema.user WHERE email = ? AND password = MD5(?)";
    private static final String SQL_LIST_ORDER_BY_ID =
            "SELECT * FROM cinema.user ORDER BY id";
    private static final String SQL_INSERT =
            "INSERT INTO cinema.user (surname, name, birthday, email, password, role) VALUES (?, ?, ?, ?, MD5(?), ?)";
    private static final String SQL_UPDATE =
            "UPDATE cinema.user SET surname = ?, name = ?, birthday = ?, email = ?, password = MD5(?), role = ? WHERE id = ?";
    private static final String SQL_DELETE =
            "DELETE FROM cinema.user WHERE id = ?";
    private static final String SQL_EXIST_EMAIL =
            "SELECT id FROM cinema.user WHERE email = ?";
    private static final String SQL_CHANGE_PASSWORD =
            "UPDATE cinema.user SET password = MD5(?) WHERE id = ?";

    // Vars ---------------------------------------------------------------------------------------

    private MySQLDAOFactory daoFactory;

    // Constructors -------------------------------------------------------------------------------

    /**
     * Construct an User DAO for the given DAOFactory. Package private so that it can be constructed
     * inside the DAO package only.
     * @param daoFactory The DAOFactory to construct this User DAO for.
     */
    MySQLUserDAO (MySQLDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    // Actions ------------------------------------------------------------------------------------

    @Override
    public User find(Long id) throws DAOException {
        return find(SQL_FIND_BY_ID, id);
    }

    @Override
    public User find(String email, String password) throws DAOException {
        return find(SQL_FIND_BY_EMAIL_AND_PASSWORD, email, password);
    }

    /**
     * Returns the user from the database matching the given SQL query with the given values.
     * @param sql The SQL query to be executed in the database.
     * @param values The PreparedStatement values to be set.
     * @return The user from the database matching the given SQL query with the given values.
     * @throws DAOException If something fails at database level.
     */
    private User find(String sql, Object... values) throws DAOException {
        User user = null;

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, sql, false, values);
                ResultSet resultSet = statement.executeQuery();
        ) {
            if (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return user;
    }

    @Override
    public List<User> list() throws DAOException {
        List<User> users = new ArrayList<>();

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_ID);
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                users.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return users;
    }

    @Override
    public void create(User user) throws IllegalArgumentException, DAOException {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User is already created, the user ID is not null.");
        }

        Object[] values = {
                user.getSurname(),
                user.getName(),
                user.getBirthday(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().name()
        };

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, SQL_INSERT, true, values);
        ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new DAOException("Creating user failed, no generated key obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(User user) throws DAOException {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User is not created yet, the user ID is null.");
        }

        Object[] values = {
                user.getSurname(),
                user.getName(),
                user.getBirthday(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().name(),
                user.getId()
        };

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, SQL_UPDATE, false, values);
        ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Updating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(User user) throws DAOException {
        Object[] values = {
                user.getId()
        };

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, SQL_DELETE, false, values);
        ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Deleting user failed, no rows affected.");
            } else {
                user.setId(null);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean existEmail(String email) throws DAOException {
        Object[] values = {
                email
        };

        boolean exist = false;

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, SQL_EXIST_EMAIL, false, values);
                ResultSet resultSet = statement.executeQuery();
        ) {
            exist = resultSet.next();
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return exist;
    }

    @Override
    public void changePassword(User user) throws DAOException {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User is not created yet, the user ID is null.");
        }

        Object[] values = {
                user.getPassword(),
                user.getId()
        };

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, SQL_CHANGE_PASSWORD, false, values);
        ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Changing password failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    // Helpers ------------------------------------------------------------------------------------


    private static User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setSurname(resultSet.getString("surname"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(User.UserRole.valueOf(resultSet.getString("role")));
        return user;
    }

}
