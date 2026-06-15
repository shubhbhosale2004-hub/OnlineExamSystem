package com.exam.dao;

import com.exam.model.Subject;
import com.exam.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for subject CRUD operations.
 */
public class SubjectDAO {

    public List<Subject> getAllSubjects() {
        List<Subject> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT * FROM subjects ORDER BY subject_name");
            rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return list;
    }

    public List<Subject> getActiveSubjects() {
        List<Subject> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT * FROM subjects WHERE is_active = 1 ORDER BY subject_name");
            rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return list;
    }

    public Subject getById(int subjectId) {
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT * FROM subjects WHERE subject_id = ?");
            ps.setInt(1, subjectId);
            rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return null;
    }

    public boolean addSubject(Subject subject) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("INSERT INTO subjects (subject_name, subject_code, description, is_active) VALUES (?,?,?,?)");
            ps.setString(1, subject.getSubjectName());
            ps.setString(2, subject.getSubjectCode());
            ps.setString(3, subject.getDescription());
            ps.setBoolean(4, subject.getIsActive());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, null); }
        return false;
    }

    public boolean updateSubject(Subject subject) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("UPDATE subjects SET subject_name=?, subject_code=?, description=?, is_active=? WHERE subject_id=?");
            ps.setString(1, subject.getSubjectName());
            ps.setString(2, subject.getSubjectCode());
            ps.setString(3, subject.getDescription());
            ps.setBoolean(4, subject.getIsActive());
            ps.setInt(5, subject.getSubjectId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, null); }
        return false;
    }

    public boolean deleteSubject(int subjectId) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("DELETE FROM subjects WHERE subject_id = ?");
            ps.setInt(1, subjectId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, null); }
        return false;
    }

    public int getTotalSubjectCount() {
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT COUNT(*) FROM subjects");
            rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return 0;
    }

    private Subject mapRow(ResultSet rs) throws SQLException {
        Subject s = new Subject();
        s.setSubjectId(rs.getInt("subject_id"));
        s.setSubjectName(rs.getString("subject_name"));
        s.setSubjectCode(rs.getString("subject_code"));
        s.setDescription(rs.getString("description"));
        s.setIsActive(rs.getBoolean("is_active"));
        s.setCreatedAt(rs.getTimestamp("created_at"));
        return s;
    }
}
