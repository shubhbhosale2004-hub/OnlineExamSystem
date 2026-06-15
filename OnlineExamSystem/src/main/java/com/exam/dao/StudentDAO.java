package com.exam.dao;

import com.exam.model.Student;
import com.exam.util.DBConnection;
import com.exam.util.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for student CRUD and authentication.
 */
public class StudentDAO {

    /** Authenticates a student; returns null if credentials are invalid. */
    public Student authenticate(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT * FROM students WHERE username = ?");
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

    /** Returns every student record. */
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT * FROM students ORDER BY student_id DESC");
            rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn, ps, rs);
        }
        return list;
    }

    /** Returns only active students. */
    public List<Student> getActiveStudents() {
        List<Student> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT * FROM students WHERE is_active = 1 ORDER BY full_name");
            rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn, ps, rs);
        }
        return list;
    }

    /** Finds a student by primary key. */
    public Student getById(int studentId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT * FROM students WHERE student_id = ?");
            ps.setInt(1, studentId);
            rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn, ps, rs);
        }
        return null;
    }

    /** Inserts a new student; the password is hashed before storage. */
    public boolean addStudent(Student student) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO students (username, password, full_name, email, phone, gender, dob, address, is_active) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getUsername());
            ps.setString(2, PasswordUtil.hashPassword(student.getPassword()));
            ps.setString(3, student.getFullName());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPhone());
            ps.setString(6, student.getGender());
            ps.setDate(7, student.getDob());
            ps.setString(8, student.getAddress());
            ps.setBoolean(9, student.getIsActive());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn, ps, null);
        }
        return false;
    }

    /** Updates every field except the password. */
    public boolean updateStudent(Student student) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "UPDATE students SET full_name=?, email=?, phone=?, gender=?, dob=?, address=?, is_active=? "
                       + "WHERE student_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getFullName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getGender());
            ps.setDate(5, student.getDob());
            ps.setString(6, student.getAddress());
            ps.setBoolean(7, student.getIsActive());
            ps.setInt(8, student.getStudentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn, ps, null);
        }
        return false;
    }

    /** Deletes a student by primary key. */
    public boolean deleteStudent(int studentId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("DELETE FROM students WHERE student_id = ?");
            ps.setInt(1, studentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn, ps, null);
        }
        return false;
    }

    /** Changes the password for a student (hashes the new password). */
    public boolean changePassword(int studentId, String newPassword) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("UPDATE students SET password = ? WHERE student_id = ?");
            ps.setString(1, PasswordUtil.hashPassword(newPassword));
            ps.setInt(2, studentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn, ps, null);
        }
        return false;
    }

    /** Returns total count of registered students. */
    public int getTotalStudentCount() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT COUNT(*) FROM students");
            rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn, ps, rs);
        }
        return 0;
    }

    /* Maps a ResultSet row to a Student object. */
    private Student mapRow(ResultSet rs) throws SQLException {
        Student s = new Student();
        s.setStudentId(rs.getInt("student_id"));
        s.setUsername(rs.getString("username"));
        s.setPassword(rs.getString("password"));
        s.setFullName(rs.getString("full_name"));
        s.setEmail(rs.getString("email"));
        s.setPhone(rs.getString("phone"));
        s.setGender(rs.getString("gender"));
        s.setDob(rs.getDate("dob"));
        s.setAddress(rs.getString("address"));
        s.setIsActive(rs.getBoolean("is_active"));
        s.setCreatedAt(rs.getTimestamp("created_at"));
        s.setUpdatedAt(rs.getTimestamp("updated_at"));
        return s;
    }
}
