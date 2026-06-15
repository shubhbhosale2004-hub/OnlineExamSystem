package com.exam.controller;

import com.exam.dao.StudentDAO;
import com.exam.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/admin/students")
public class ManageStudentsServlet extends HttpServlet {

    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                request.getRequestDispatcher("/WEB-INF/views/admin/student-form.jsp").forward(request, response);
                break;
            case "edit":
                int editId = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("student", studentDAO.getById(editId));
                request.getRequestDispatcher("/WEB-INF/views/admin/student-form.jsp").forward(request, response);
                break;
            case "delete":
                int deleteId = Integer.parseInt(request.getParameter("id"));
                studentDAO.deleteStudent(deleteId);
                response.sendRedirect(request.getContextPath() + "/admin/students?msg=deleted");
                break;
            default:
                request.setAttribute("students", studentDAO.getAllStudents());
                request.setAttribute("msg", request.getParameter("msg"));
                request.getRequestDispatcher("/WEB-INF/views/admin/manage-students.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        Student student = new Student();
        student.setFullName(request.getParameter("fullName"));
        student.setEmail(request.getParameter("email"));
        student.setPhone(request.getParameter("phone"));
        student.setGender(request.getParameter("gender"));
        String dobStr = request.getParameter("dob");
        if (dobStr != null && !dobStr.isEmpty()) student.setDob(Date.valueOf(dobStr));
        student.setAddress(request.getParameter("address"));
        student.setIsActive("on".equals(request.getParameter("isActive")) || "true".equals(request.getParameter("isActive")));

        if ("edit".equals(action)) {
            student.setStudentId(Integer.parseInt(request.getParameter("studentId")));
            studentDAO.updateStudent(student);
            response.sendRedirect(request.getContextPath() + "/admin/students?msg=updated");
        } else {
            student.setUsername(request.getParameter("username"));
            student.setPassword(request.getParameter("password"));
            studentDAO.addStudent(student);
            response.sendRedirect(request.getContextPath() + "/admin/students?msg=added");
        }
    }
}
