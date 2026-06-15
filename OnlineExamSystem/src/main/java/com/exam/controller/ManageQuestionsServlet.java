package com.exam.controller;

import com.exam.dao.ExamDAO;
import com.exam.dao.QuestionDAO;
import com.exam.model.Question;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/questions")
public class ManageQuestionsServlet extends HttpServlet {

    private final QuestionDAO questionDAO = new QuestionDAO();
    private final ExamDAO examDAO = new ExamDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int examId = 0;
        try { examId = Integer.parseInt(request.getParameter("examId")); } catch (Exception ignored) {}
        if (action == null) action = "list";

        switch (action) {
            case "add":
                request.setAttribute("exam", examDAO.getById(examId));
                request.getRequestDispatcher("/WEB-INF/views/admin/question-form.jsp").forward(request, response);
                break;
            case "edit":
                int qId = Integer.parseInt(request.getParameter("id"));
                Question q = questionDAO.getById(qId);
                request.setAttribute("question", q);
                request.setAttribute("exam", examDAO.getById(q.getExamId()));
                request.getRequestDispatcher("/WEB-INF/views/admin/question-form.jsp").forward(request, response);
                break;
            case "delete":
                Question dq = questionDAO.getById(Integer.parseInt(request.getParameter("id")));
                questionDAO.deleteQuestion(dq.getQuestionId());
                response.sendRedirect(request.getContextPath() + "/admin/questions?examId=" + dq.getExamId() + "&msg=deleted");
                break;
            default:
                request.setAttribute("exam", examDAO.getById(examId));
                request.setAttribute("questions", questionDAO.getQuestionsByExam(examId));
                request.setAttribute("msg", request.getParameter("msg"));
                request.getRequestDispatcher("/WEB-INF/views/admin/manage-questions.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int examId = Integer.parseInt(request.getParameter("examId"));

        Question question = new Question();
        question.setExamId(examId);
        question.setQuestionText(request.getParameter("questionText"));
        question.setOptionA(request.getParameter("optionA"));
        question.setOptionB(request.getParameter("optionB"));
        question.setOptionC(request.getParameter("optionC"));
        question.setOptionD(request.getParameter("optionD"));
        question.setCorrectOption(request.getParameter("correctOption"));
        question.setMarks(Integer.parseInt(request.getParameter("marks")));

        if ("edit".equals(action)) {
            question.setQuestionId(Integer.parseInt(request.getParameter("questionId")));
            questionDAO.updateQuestion(question);
        } else {
            questionDAO.addQuestion(question);
        }
        response.sendRedirect(request.getContextPath() + "/admin/questions?examId=" + examId + "&msg=saved");
    }
}
