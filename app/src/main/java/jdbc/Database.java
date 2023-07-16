package jdbc;

import java.sql.*;

public class Database {

    private static final Database INSTANCE = new Database();

    private Connection connection;

    private Database() {
        try {
            String dbUrl = new Settings().getString(Settings.DB_JDBC_CONNECTION_URL);
            connection = DriverManager.getConnection(dbUrl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public int executeUpdate(String sql) {
        try (Statement stmt = connection.createStatement()) {
            return stmt.executeUpdate(sql);
        } catch (Exception ex) {
            ex.printStackTrace();

            return -1;
        }
    }

//    public ResultSet executeQuery(String sql) {
//        try (Statement stmt = connection.createStatement()) {
//            return stmt.executeQuery(sql);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//
//            return null;
//        }
//    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
