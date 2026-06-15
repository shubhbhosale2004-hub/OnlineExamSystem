package com.exam.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Utility class for managing JDBC database connections.
 * Reads configuration from db.properties on the classpath.
 */
public class DBConnection {

    private static String jdbcUrl;
    private static String dbUser;
    private static String dbPass;

    /* Load driver and configuration once at class-load time */
    static {
        try {
            Properties props = new Properties();
            InputStream input = DBConnection.class.getClassLoader()
                    .getResourceAsStream("db.properties");
            if (input == null) {
                throw new RuntimeException("db.properties not found on classpath");
            }
            props.load(input);
            input.close();

            String driverClass = props.getProperty("db.driver");
            jdbcUrl = props.getProperty("db.url");
            dbUser = props.getProperty("db.username");
            dbPass = props.getProperty("db.password");

            Class.forName(driverClass);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to initialize database configuration", ex);
        }
    }

    /**
     * Obtains a fresh JDBC connection to the configured database.
     * Caller is responsible for closing the returned connection.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
    }

    /**
     * Safely closes JDBC resources in the correct order.
     */
    public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {
        try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
        try { if (ps != null) ps.close(); } catch (SQLException ignored) {}
        try { if (conn != null) conn.close(); } catch (SQLException ignored) {}
    }
}
