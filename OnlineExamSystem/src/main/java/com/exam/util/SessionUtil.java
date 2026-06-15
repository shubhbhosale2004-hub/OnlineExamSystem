package com.exam.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Helper methods for managing user sessions and role-based access.
 */
public class SessionUtil {

    /** Checks if the current request has an active logged-in session. */
    public static boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("userId") != null;
    }

    /** Returns true if the logged-in user holds the admin role. */
    public static boolean isAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && "admin".equals(session.getAttribute("role"));
    }

    /** Returns true if the logged-in user holds the student role. */
    public static boolean isStudent(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && "student".equals(session.getAttribute("role"));
    }

    /** Retrieves the numeric user ID stored in the session. */
    public static int getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userId") != null) {
            return (int) session.getAttribute("userId");
        }
        return -1;
    }

    /** Retrieves the role string from the session. */
    public static String getUserRole(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (String) session.getAttribute("role");
        }
        return null;
    }

    /** Stores user information in the session after successful authentication. */
    public static void setUserSession(HttpServletRequest request, Object user,
                                       String role, int userId) {
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
        session.setAttribute("role", role);
        session.setAttribute("userId", userId);
        session.setMaxInactiveInterval(30 * 60); // 30 minutes
    }

    /** Invalidates the current session, effectively logging the user out. */
    public static void destroySession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
