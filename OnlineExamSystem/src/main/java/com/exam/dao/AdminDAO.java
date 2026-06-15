package com.exam.dao;

import com.exam.model.Admin;
import com.exam.util.DBConnection;
import com.exam.util.PasswordUtil;

import java.sql.*;

/**
 * Data Access Object for administrator operations.
 */
public class AdminDAO {

    /** Authenticates an admin by username and plaintext password. */
    public Admin authenticate(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM admin WHERE username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password");
                if (PasswordUtil.checkPassword(password, storedHash)) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn, ps, rs);
        }
        return null;
    }

    /** Finds an admin by primary key. */
    public Admin getById(int adminId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT * FROM admin WHERE admin_id = ?");
            ps.setInt(1, adminId);
            rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn, ps, rs);
        }
        return null;
    }

    /** Updates the admin password (expects an already-hashed value). */
    public boolean updatePassword(int adminId, String newHashedPassword) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("UPDATE admin SET password = ? WHERE admin_id = ?");
            ps.setString(1, newHashedPassword);
            ps.setInt(2, adminId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn, ps, null);
        }
        return false;
    }

    /* Maps a ResultSet row to an Admin object */
    private Admin mapRow(ResultSet rs) throws SQLException {
        Admin admin = new Admin();
        admin.setAdminId(rs.getInt("admin_id"));
        admin.setUsername(rs.getString("username"));
        admin.setPassword(rs.getString("password"));
        admin.setFullName(rs.getString("full_name"));
        admin.setEmail(rs.getString("email"));
        admin.setCreatedAt(rs.getTimestamp("created_at"));
        return admin;
    }
}
