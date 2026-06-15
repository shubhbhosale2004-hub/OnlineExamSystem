package com.exam.controller;

import com.exam.util.SessionUtil;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter that protects all application routes.
 * Public paths (login, logout, static assets) are allowed through.
 * Admin and student routes require the appropriate session role.
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String path = request.getRequestURI().substring(request.getContextPath().length());

        // Allow public resources without authentication
        if (isPublicResource(path)) {
            chain.doFilter(request, response);
            return;
        }

        // Enforce role-based access for admin routes
        if (path.startsWith("/admin/")) {
            if (SessionUtil.isAdmin(request)) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
            return;
        }

        // Enforce role-based access for student routes
        if (path.startsWith("/student/")) {
            if (SessionUtil.isStudent(request)) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
            return;
        }

        // For any other path, just ensure the user is logged in
        if (SessionUtil.isLoggedIn(request)) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    /** Determines whether the requested path is publicly accessible. */
    private boolean isPublicResource(String path) {
        return path.equals("/login")
            || path.equals("/logout")
            || path.startsWith("/css/")
            || path.startsWith("/js/")
            || path.startsWith("/images/")
            || path.equals("/index.jsp")
            || path.equals("/")
            || path.equals("");
    }
}
