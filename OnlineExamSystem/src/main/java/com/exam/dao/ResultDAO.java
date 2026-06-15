package com.exam.dao;

import com.exam.model.Result;
import com.exam.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for exam result records.
 */
public class ResultDAO {

    public boolean saveResult(Result result) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO results (attempt_id, student_id, exam_id, total_questions, attempted, "
                       + "correct_answers, wrong_answers, total_marks, obtained_marks, percentage, status) "
                       + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, result.getAttemptId());
            ps.setInt(2, result.getStudentId());
            ps.setInt(3, result.getExamId());
            ps.setInt(4, result.getTotalQuestions());
            ps.setInt(5, result.getAttempted());
            ps.setInt(6, result.getCorrectAnswers());
            ps.setInt(7, result.getWrongAnswers());
            ps.setInt(8, result.getTotalMarks());
            ps.setInt(9, result.getObtainedMarks());
            ps.setDouble(10, result.getPercentage());
            ps.setString(11, result.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, null); }
        return false;
    }

    public Result getByAttemptId(int attemptId) {
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT r.*, s.full_name AS student_name, e.exam_title FROM results r "
                       + "JOIN students s ON r.student_id = s.student_id "
                       + "JOIN exams e ON r.exam_id = e.exam_id WHERE r.attempt_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, attemptId);
            rs = ps.executeQuery();
            if (rs.next()) return mapRowFull(rs);
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return null;
    }

    public List<Result> getResultsByStudent(int studentId) {
        List<Result> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT r.*, e.exam_title FROM results r "
                       + "JOIN exams e ON r.exam_id = e.exam_id WHERE r.student_id = ? ORDER BY r.created_at DESC";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Result r = mapRowBase(rs);
                r.setExamTitle(rs.getString("exam_title"));
                list.add(r);
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return list;
    }

    public List<Result> getResultsByExam(int examId) {
        List<Result> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT r.*, s.full_name AS student_name FROM results r "
                       + "JOIN students s ON r.student_id = s.student_id WHERE r.exam_id = ? ORDER BY r.percentage DESC";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, examId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Result r = mapRowBase(rs);
                r.setStudentName(rs.getString("student_name"));
                list.add(r);
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return list;
    }

    public List<Result> getAllResults() {
        List<Result> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT r.*, s.full_name AS student_name, e.exam_title FROM results r "
                       + "JOIN students s ON r.student_id = s.student_id "
                       + "JOIN exams e ON r.exam_id = e.exam_id ORDER BY r.created_at DESC";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) list.add(mapRowFull(rs));
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return list;
    }

    public boolean deleteResult(int resultId) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("DELETE FROM results WHERE result_id = ?");
            ps.setInt(1, resultId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, null); }
        return false;
    }

    public int getTotalResultCount() {
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT COUNT(*) FROM results");
            rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return 0;
    }

    public double getAveragePercentageByExam(int examId) {
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT AVG(percentage) FROM results WHERE exam_id = ?");
            ps.setInt(1, examId);
            rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return 0.0;
    }

    public int getPassCountByExam(int examId) {
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT COUNT(*) FROM results WHERE exam_id = ? AND status = 'Pass'");
            ps.setInt(1, examId);
            rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return 0;
    }

    public int getFailCountByExam(int examId) {
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT COUNT(*) FROM results WHERE exam_id = ? AND status = 'Fail'");
            ps.setInt(1, examId);
            rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return 0;
    }

    /* Maps base result fields (no JOINed names). */
    private Result mapRowBase(ResultSet rs) throws SQLException {
        Result r = new Result();
        r.setResultId(rs.getInt("result_id"));
        r.setAttemptId(rs.getInt("attempt_id"));
        r.setStudentId(rs.getInt("student_id"));
        r.setExamId(rs.getInt("exam_id"));
        r.setTotalQuestions(rs.getInt("total_questions"));
        r.setAttempted(rs.getInt("attempted"));
        r.setCorrectAnswers(rs.getInt("correct_answers"));
        r.setWrongAnswers(rs.getInt("wrong_answers"));
        r.setTotalMarks(rs.getInt("total_marks"));
        r.setObtainedMarks(rs.getInt("obtained_marks"));
        r.setPercentage(rs.getDouble("percentage"));
        r.setStatus(rs.getString("status"));
        r.setCreatedAt(rs.getTimestamp("created_at"));
        return r;
    }

    /* Maps result with student_name and exam_title from JOINs. */
    private Result mapRowFull(ResultSet rs) throws SQLException {
        Result r = mapRowBase(rs);
        r.setStudentName(rs.getString("student_name"));
        r.setExamTitle(rs.getString("exam_title"));
        return r;
    }
}
