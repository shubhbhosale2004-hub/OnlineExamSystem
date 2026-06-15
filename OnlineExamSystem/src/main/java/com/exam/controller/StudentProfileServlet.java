package com.exam.controller;

import com.exam.dao.StudentDAO;
import com.exam.model.Student;
import com.exam.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/student/profile")
public class StudentProfileServlet extends HttpServlet {

    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = SessionUtil.getUserId(request);
        request.setAttribute("student", studentDAO.getById(studentId));
        request.getRequestDispatcher("/WEB-INF/views/student/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = SessionUtil.getUserId(request);
        Student student = studentDAO.getById(studentId);

        student.setFullName(request.getParameter("fullName"));
        student.setEmail(request.getParameter("email"));
        student.setPhone(request.getParameter("phone"));
        student.setGender(request.getParameter("gender"));
        String dobStr = request.getParameter("dob");
        if (dobStr != null && !dobStr.isEmpty()) {
            student.setDob(Date.valueOf(dobStr));
        }
        student.setAddress(request.getParameter("address"));

        if (studentDAO.updateStudent(student)) {
            request.setAttribute("success", "Profile updated successfully.");
        } else {
            request.setAttribute("error", "Failed to update profile. Please try again.");
        }
        request.setAttribute("student", student);
        request.getRequestDispatcher("/WEB-INF/views/student/profile.jsp").forward(request, response);
    }
}
