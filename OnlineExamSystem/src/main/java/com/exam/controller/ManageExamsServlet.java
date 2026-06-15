package com.exam.controller;

import com.exam.dao.ExamDAO;
import com.exam.dao.SubjectDAO;
import com.exam.model.Exam;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/admin/exams")
public class ManageExamsServlet extends HttpServlet {

    private final ExamDAO examDAO = new ExamDAO();
    private final SubjectDAO subjectDAO = new SubjectDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                request.setAttribute("subjects", subjectDAO.getAllSubjects());
                request.getRequestDispatcher("/WEB-INF/views/admin/exam-form.jsp").forward(request, response);
                break;
            case "edit":
                request.setAttribute("exam", examDAO.getById(Integer.parseInt(request.getParameter("id"))));
                request.setAttribute("subjects", subjectDAO.getAllSubjects());
                request.getRequestDispatcher("/WEB-INF/views/admin/exam-form.jsp").forward(request, response);
                break;
            case "delete":
                examDAO.deleteExam(Integer.parseInt(request.getParameter("id")));
                response.sendRedirect(request.getContextPath() + "/admin/exams?msg=deleted");
                break;
            default:
                request.setAttribute("exams", examDAO.getAllExams());
                request.setAttribute("msg", request.getParameter("msg"));
                request.getRequestDispatcher("/WEB-INF/views/admin/manage-exams.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        Exam exam = new Exam();
        exam.setExamTitle(request.getParameter("examTitle"));
        exam.setSubjectId(Integer.parseInt(request.getParameter("subjectId")));
        exam.setTotalQuestions(Integer.parseInt(request.getParameter("totalQuestions")));
        exam.setTotalMarks(Integer.parseInt(request.getParameter("totalMarks")));
        exam.setDurationMinutes(Integer.parseInt(request.getParameter("durationMinutes")));
        exam.setPassPercentage(Double.parseDouble(request.getParameter("passPercentage")));
        exam.setIsActive("on".equals(request.getParameter("isActive")) || "true".equals(request.getParameter("isActive")));

        String startStr = request.getParameter("startDate");
        String endStr = request.getParameter("endDate");
        if (startStr != null && !startStr.isEmpty()) exam.setStartDate(Timestamp.valueOf(startStr.replace("T", " ") + ":00"));
        if (endStr != null && !endStr.isEmpty()) exam.setEndDate(Timestamp.valueOf(endStr.replace("T", " ") + ":00"));

        if ("edit".equals(action)) {
            exam.setExamId(Integer.parseInt(request.getParameter("examId")));
            examDAO.updateExam(exam);
            response.sendRedirect(request.getContextPath() + "/admin/exams?msg=updated");
        } else {
            examDAO.addExam(exam);
            response.sendRedirect(request.getContextPath() + "/admin/exams?msg=added");
        }
    }
}
