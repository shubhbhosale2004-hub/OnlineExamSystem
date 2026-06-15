# Project Report — Online Examination System

## 1. Abstract

The Online Examination System is a web-based application designed to conduct MCQ-based examinations over the internet. Built using Java Servlet technology, JSP for the view layer, and MySQL for data persistence, the system provides a comprehensive platform for both students and administrators. Students can register, log in, take timed exams, and view their results instantly. Administrators have full control over managing students, subjects, exams, questions, and reviewing examination statistics. The application follows the MVC architectural pattern and incorporates industry-standard security measures including BCrypt password hashing, PreparedStatement for SQL injection prevention, and session-based authentication.

## 2. Introduction

### Problem Statement
Traditional paper-based examination systems are time-consuming, resource-intensive, and prone to errors in evaluation. Manual checking of answer sheets delays result publication and increases administrative overhead. There is a need for an automated, scalable, and secure online examination platform.

### Motivation
The shift to digital learning and remote education has accelerated the demand for online assessment tools. This project aims to provide a reliable, secure, and user-friendly online examination solution suitable for educational institutions.

## 3. Objectives

- Develop a secure web-based examination platform
- Enable students to take MCQ exams with a countdown timer
- Provide instant result generation with detailed score analysis
- Allow administrators to manage the complete examination lifecycle
- Implement robust security measures against common web vulnerabilities
- Create a responsive and visually appealing user interface

## 4. System Requirements

### Hardware
- Processor: Intel Core i3 or equivalent
- RAM: 4 GB minimum
- Storage: 500 MB for application + database

### Software
- JDK 17 or later
- Apache Tomcat 10.1.x
- MySQL 8.0+
- Maven 3.x
- Eclipse IDE / IntelliJ IDEA
- Web Browser (Chrome, Firefox, Edge)

## 5. Technology Stack

| Technology | Purpose |
|-----------|---------|
| Java 17 | Core programming language for business logic |
| Jakarta Servlet 6.0 | Request handling and controller implementation |
| JSP + JSTL | Server-side view rendering with tag libraries |
| MySQL 8.x | Relational database for persistent data storage |
| JDBC | Database connectivity and query execution |
| Bootstrap 5.3 | Responsive UI framework for frontend design |
| JavaScript | Client-side interactivity, timer, form validation |
| BCrypt | Secure one-way password hashing algorithm |
| Apache Tomcat 10.1 | Web application server |
| Maven | Build automation and dependency management |

## 6. System Architecture

The application follows the Model-View-Controller (MVC) pattern:

- **Model**: POJO classes and DAO classes handle data representation and database operations
- **View**: JSP pages with JSTL tags render the user interface
- **Controller**: Servlet classes process HTTP requests, invoke business logic, and route to views

## 7. Module Descriptions

### Authentication Module
Handles user login/logout with role-based access control. An authentication filter protects all routes, ensuring only authorized users access admin or student areas.

### Student Module
Allows students to view their dashboard with statistics, manage their profile, change passwords, browse available exams, take exams, and view their results history.

### Admin Module
Provides administrators with a comprehensive dashboard showing system statistics, and full CRUD capabilities for students, subjects, exams, and questions.

### Examination Module
The core module that presents MCQ questions with a countdown timer, question navigation, and both manual and automatic submission. Answer selection is intuitive with card-based option styling.

### Result Module
Instantly calculates scores upon exam submission, determining correct/wrong answers, percentage, and pass/fail status based on configurable pass percentages.

## 8. Database Design

The database consists of 8 interconnected tables: admin, students, subjects, exams, questions, exam_attempts, answers, and results. Foreign key constraints ensure referential integrity, and indexes optimize query performance.

## 9. Security Features

- **BCrypt Hashing**: All passwords stored as BCrypt hashes with salt
- **PreparedStatement**: All SQL queries use parameterized statements
- **Session Management**: Server-side sessions with 30-minute timeout
- **Authentication Filter**: Servlet filter enforces access control on all routes
- **XSS Prevention**: JSTL `<c:out>` escapes all dynamic output

## 10. Testing

Manual testing was performed for all modules including login flows, CRUD operations, exam taking with timer, auto-submission, and result verification.

## 11. Future Enhancements

- Email notifications for exam schedules and results
- Question bank with random question selection
- Multiple exam attempts with configurable retry limits
- PDF report generation for results
- Real-time exam monitoring for administrators
- RESTful API for mobile application integration

## 12. Conclusion

The Online Examination System successfully addresses the need for a digital examination platform. It provides a secure, efficient, and user-friendly solution for conducting online assessments with instant result generation and comprehensive administrative controls.

## 13. References

- Oracle Java Documentation
- Jakarta Servlet Specification 6.0
- MySQL 8.0 Reference Manual
- Bootstrap 5 Documentation
- OWASP Security Guidelines
