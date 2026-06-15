package com.exam.dao;

import com.exam.model.Question;
import com.exam.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for question management.
 */
public class QuestionDAO {

    public List<Question> getQuestionsByExam(int examId) {
        List<Question> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT * FROM questions WHERE exam_id = ? ORDER BY question_id");
            ps.setInt(1, examId);
            rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return list;
    }

    public Question getById(int questionId) {
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT * FROM questions WHERE question_id = ?");
            ps.setInt(1, questionId);
            rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return null;
    }

    public boolean addQuestion(Question question) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO questions (exam_id, question_text, option_a, option_b, option_c, option_d, correct_option, marks) "
                       + "VALUES (?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, question.getExamId());
            ps.setString(2, question.getQuestionText());
            ps.setString(3, question.getOptionA());
            ps.setString(4, question.getOptionB());
            ps.setString(5, question.getOptionC());
            ps.setString(6, question.getOptionD());
            ps.setString(7, question.getCorrectOption());
            ps.setInt(8, question.getMarks());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, null); }
        return false;
    }

    public boolean updateQuestion(Question question) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "UPDATE questions SET question_text=?, option_a=?, option_b=?, option_c=?, option_d=?, "
                       + "correct_option=?, marks=? WHERE question_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, question.getQuestionText());
            ps.setString(2, question.getOptionA());
            ps.setString(3, question.getOptionB());
            ps.setString(4, question.getOptionC());
            ps.setString(5, question.getOptionD());
            ps.setString(6, question.getCorrectOption());
            ps.setInt(7, question.getMarks());
            ps.setInt(8, question.getQuestionId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, null); }
        return false;
    }

    public boolean deleteQuestion(int questionId) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("DELETE FROM questions WHERE question_id = ?");
            ps.setInt(1, questionId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, null); }
        return false;
    }

    public int getQuestionCountByExam(int examId) {
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT COUNT(*) FROM questions WHERE exam_id = ?");
            ps.setInt(1, examId);
            rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return 0;
    }

    private Question mapRow(ResultSet rs) throws SQLException {
        Question q = new Question();
        q.setQuestionId(rs.getInt("question_id"));
        q.setExamId(rs.getInt("exam_id"));
        q.setQuestionText(rs.getString("question_text"));
        q.setOptionA(rs.getString("option_a"));
        q.setOptionB(rs.getString("option_b"));
        q.setOptionC(rs.getString("option_c"));
        q.setOptionD(rs.getString("option_d"));
        q.setCorrectOption(rs.getString("correct_option"));
        q.setMarks(rs.getInt("marks"));
        q.setCreatedAt(rs.getTimestamp("created_at"));
        return q;
    }
}
