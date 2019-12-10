package model.dao.mysql;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MySQLConnectionPool {
    private static MySQLConnectionPool pool = null;
    private static DataSource dataSource = null;

    private MySQLConnectionPool() {
        try {
            InitialContext initialContext = new InitialContext();
            Context envContext = (Context) initialContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/cinema");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static synchronized MySQLConnectionPool getInstance(){
        if (pool == null) {
            pool = new MySQLConnectionPool();
        }
        return pool;
    }

    public Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
