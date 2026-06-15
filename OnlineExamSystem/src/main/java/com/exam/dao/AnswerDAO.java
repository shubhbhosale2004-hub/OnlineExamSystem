package com.exam.dao;

import com.exam.model.Answer;
import com.exam.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for student answer records.
 */
public class AnswerDAO {

    public boolean saveAnswer(Answer answer) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO answers (attempt_id, question_id, selected_option, is_correct) VALUES (?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, answer.getAttemptId());
            ps.setInt(2, answer.getQuestionId());
            ps.setString(3, answer.getSelectedOption());
            ps.setBoolean(4, answer.getIsCorrect());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, null); }
        return false;
    }

    public List<Answer> getAnswersByAttempt(int attemptId) {
        List<Answer> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT * FROM answers WHERE attempt_id = ? ORDER BY question_id");
            ps.setInt(1, attemptId);
            rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return list;
    }

    public Answer getByAttemptAndQuestion(int attemptId, int questionId) {
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT * FROM answers WHERE attempt_id = ? AND question_id = ?");
            ps.setInt(1, attemptId);
            ps.setInt(2, questionId);
            rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException ex) { ex.printStackTrace(); }
        finally { DBConnection.closeConnection(conn, ps, rs); }
        return null;
    }

    private Answer mapRow(ResultSet rs) throws SQLException {
        Answer a = new Answer();
        a.setAnswerId(rs.getInt("answer_id"));
        a.setAttemptId(rs.getInt("attempt_id"));
        a.setQuestionId(rs.getInt("question_id"));
        a.setSelectedOption(rs.getString("selected_option"));
        a.setIsCorrect(rs.getBoolean("is_correct"));
        return a;
    }
}
