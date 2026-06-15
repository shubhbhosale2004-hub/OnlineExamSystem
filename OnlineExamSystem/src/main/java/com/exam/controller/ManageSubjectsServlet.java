package com.exam.controller;

import com.exam.dao.SubjectDAO;
import com.exam.model.Subject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/subjects")
public class ManageSubjectsServlet extends HttpServlet {

    private final SubjectDAO subjectDAO = new SubjectDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            subjectDAO.deleteSubject(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect(request.getContextPath() + "/admin/subjects?msg=deleted");
            return;
        }
        request.setAttribute("subjects", subjectDAO.getAllSubjects());
        request.setAttribute("msg", request.getParameter("msg"));
        request.getRequestDispatcher("/WEB-INF/views/admin/manage-subjects.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        Subject subject = new Subject();
        subject.setSubjectName(request.getParameter("subjectName"));
        subject.setSubjectCode(request.getParameter("subjectCode"));
        subject.setDescription(request.getParameter("description"));
        subject.setIsActive(true);

        if ("edit".equals(action)) {
            subject.setSubjectId(Integer.parseInt(request.getParameter("subjectId")));
            subjectDAO.updateSubject(subject);
            response.sendRedirect(request.getContextPath() + "/admin/subjects?msg=updated");
        } else {
            subjectDAO.addSubject(subject);
            response.sendRedirect(request.getContextPath() + "/admin/subjects?msg=added");
        }
    }
}
