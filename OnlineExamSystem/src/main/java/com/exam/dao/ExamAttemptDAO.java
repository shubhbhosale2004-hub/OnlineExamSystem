package com.exam.dao;

import com.exam.model.ExamAttempt;
import com.exam.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for exam attempt tracking.
 */
public class ExamAttemptDAO {

    /** Creates a new attempt and returns the generated attempt_id. */
    public int startAttempt(ExamAttempt attempt) {
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO exam_attempts (student_id, exam_id, start_time, status) VALUES (?,?,NOW(),?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, attempt.getStudentId());
            ps.setInt(2, attempt.getExamId());
            ps.setString(3, "in_progress");
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return -1;
    }

    /** Marks an attempt as completed or auto_submitted. */
    public boolean completeAttempt(int attemptId, String status) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("UPDATE exam_attempts SET end_time = NOW(), status = ? WHERE attempt_id = ?");
            ps.setString(1, status);
            ps.setInt(2, attemptId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, null); }
        return false;
    }

    public ExamAttempt getById(int attemptId) {
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT * FROM exam_attempts WHERE attempt_id = ?");
            ps.setInt(1, attemptId);
            rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return null;
    }

    /** Gets all attempts for a student, including exam title via JOIN. */
    public List<ExamAttempt> getAttemptsByStudent(int studentId) {
        List<ExamAttempt> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT ea.*, e.exam_title FROM exam_attempts ea "
                       + "JOIN exams e ON ea.exam_id = e.exam_id WHERE ea.student_id = ? ORDER BY ea.start_time DESC";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                ExamAttempt a = mapRow(rs);
                a.setExamTitle(rs.getString("exam_title"));
                list.add(a);
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return list;
    }

    /** Gets all attempts for an exam, including student name. */
    public List<ExamAttempt> getAttemptsByExam(int examId) {
        List<ExamAttempt> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT ea.*, s.full_name AS student_name FROM exam_attempts ea "
                       + "JOIN students s ON ea.student_id = s.student_id WHERE ea.exam_id = ? ORDER BY ea.start_time DESC";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, examId);
            rs = ps.executeQuery();
            while (rs.next()) {
                ExamAttempt a = mapRow(rs);
                a.setStudentName(rs.getString("student_name"));
                list.add(a);
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return list;
    }

    /** Checks whether a student has already attempted a specific exam. */
    public boolean hasStudentAttemptedExam(int studentId, int examId) {
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT COUNT(*) FROM exam_attempts WHERE student_id = ? AND exam_id = ?");
            ps.setInt(1, studentId);
            ps.setInt(2, examId);
            rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return false;
    }

    /** Gets an in-progress attempt for a student/exam combination. */
    public ExamAttempt getActiveAttempt(int studentId, int examId) {
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(
                "SELECT * FROM exam_attempts WHERE student_id = ? AND exam_id = ? AND status = 'in_progress'");
            ps.setInt(1, studentId);
            ps.setInt(2, examId);
            rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return null;
    }

    private ExamAttempt mapRow(ResultSet rs) throws SQLException {
        ExamAttempt a = new ExamAttempt();
        a.setAttemptId(rs.getInt("attempt_id"));
        a.setStudentId(rs.getInt("student_id"));
        a.setExamId(rs.getInt("exam_id"));
        a.setStartTime(rs.getTimestamp("start_time"));
        a.setEndTime(rs.getTimestamp("end_time"));
        a.setStatus(rs.getString("status"));
        a.setCreatedAt(rs.getTimestamp("created_at"));
        return a;
    }
}
