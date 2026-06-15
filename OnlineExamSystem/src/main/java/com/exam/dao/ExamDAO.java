package com.exam.dao;

import com.exam.model.Exam;
import com.exam.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for exam CRUD operations.
 */
public class ExamDAO {

    /** Returns all exams with the associated subject name via JOIN. */
    public List<Exam> getAllExams() {
        List<Exam> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT e.*, s.subject_name FROM exams e "
                       + "JOIN subjects s ON e.subject_id = s.subject_id ORDER BY e.exam_id DESC";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return list;
    }

    /** Returns only active exams whose date window includes the current moment. */
    public List<Exam> getActiveExams() {
        List<Exam> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT e.*, s.subject_name FROM exams e "
                       + "JOIN subjects s ON e.subject_id = s.subject_id "
                       + "WHERE e.is_active = 1 AND NOW() BETWEEN e.start_date AND e.end_date "
                       + "ORDER BY e.exam_title";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return list;
    }

    /** Finds a single exam by primary key. */
    public Exam getById(int examId) {
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT e.*, s.subject_name FROM exams e "
                       + "JOIN subjects s ON e.subject_id = s.subject_id WHERE e.exam_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, examId);
            rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return null;
    }

    public List<Exam> getExamsBySubject(int subjectId) {
        List<Exam> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT e.*, s.subject_name FROM exams e "
                       + "JOIN subjects s ON e.subject_id = s.subject_id WHERE e.subject_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, subjectId);
            rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return list;
    }

    public boolean addExam(Exam exam) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO exams (exam_title, subject_id, total_questions, total_marks, "
                       + "duration_minutes, pass_percentage, is_active, start_date, end_date) VALUES (?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, exam.getExamTitle());
            ps.setInt(2, exam.getSubjectId());
            ps.setInt(3, exam.getTotalQuestions());
            ps.setInt(4, exam.getTotalMarks());
            ps.setInt(5, exam.getDurationMinutes());
            ps.setDouble(6, exam.getPassPercentage());
            ps.setBoolean(7, exam.getIsActive());
            ps.setTimestamp(8, exam.getStartDate());
            ps.setTimestamp(9, exam.getEndDate());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, null); }
        return false;
    }

    public boolean updateExam(Exam exam) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "UPDATE exams SET exam_title=?, subject_id=?, total_questions=?, total_marks=?, "
                       + "duration_minutes=?, pass_percentage=?, is_active=?, start_date=?, end_date=? WHERE exam_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, exam.getExamTitle());
            ps.setInt(2, exam.getSubjectId());
            ps.setInt(3, exam.getTotalQuestions());
            ps.setInt(4, exam.getTotalMarks());
            ps.setInt(5, exam.getDurationMinutes());
            ps.setDouble(6, exam.getPassPercentage());
            ps.setBoolean(7, exam.getIsActive());
            ps.setTimestamp(8, exam.getStartDate());
            ps.setTimestamp(9, exam.getEndDate());
            ps.setInt(10, exam.getExamId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, null); }
        return false;
    }

    public boolean deleteExam(int examId) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("DELETE FROM exams WHERE exam_id = ?");
            ps.setInt(1, examId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, null); }
        return false;
    }

    public int getTotalExamCount() {
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT COUNT(*) FROM exams");
            rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return 0;
    }

    private Exam mapRow(ResultSet rs) throws SQLException {
        Exam e = new Exam();
        e.setExamId(rs.getInt("exam_id"));
        e.setExamTitle(rs.getString("exam_title"));
        e.setSubjectId(rs.getInt("subject_id"));
        e.setSubjectName(rs.getString("subject_name"));
        e.setTotalQuestions(rs.getInt("total_questions"));
        e.setTotalMarks(rs.getInt("total_marks"));
        e.setDurationMinutes(rs.getInt("duration_minutes"));
        e.setPassPercentage(rs.getDouble("pass_percentage"));
        e.setIsActive(rs.getBoolean("is_active"));
        e.setStartDate(rs.getTimestamp("start_date"));
        e.setEndDate(rs.getTimestamp("end_date"));
        e.setCreatedAt(rs.getTimestamp("created_at"));
        return e;
    }
}
