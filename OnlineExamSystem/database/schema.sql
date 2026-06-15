-- ============================================================
-- Online Examination System - Database Schema
-- Database: online_exam_db
-- Engine: MySQL 8.x / InnoDB
-- Character Set: UTF-8 (utf8mb4)
-- ============================================================

-- Create database if it doesn't already exist
CREATE DATABASE IF NOT EXISTS online_exam_db
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE online_exam_db;

-- ============================================================
-- Drop existing tables in reverse dependency order
-- ============================================================
DROP TABLE IF EXISTS results;
DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS exam_attempts;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS exams;
DROP TABLE IF EXISTS subjects;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS admin;

-- ============================================================
-- Table 1: admin
-- Stores administrator credentials and profile information
-- ============================================================
CREATE TABLE admin (
    admin_id INT AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL COMMENT 'BCrypt hashed password',
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (admin_id),
    UNIQUE KEY uk_admin_username (username),
    UNIQUE KEY uk_admin_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- Table 2: students
-- Stores student registration data and login credentials
-- ============================================================
CREATE TABLE students (
    student_id INT AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL COMMENT 'BCrypt hashed password',
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(15) DEFAULT NULL,
    gender ENUM('Male', 'Female', 'Other') DEFAULT NULL,
    dob DATE DEFAULT NULL,
    address TEXT DEFAULT NULL,
    is_active TINYINT(1) DEFAULT 1 COMMENT '1=Active, 0=Inactive',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (student_id),
    UNIQUE KEY uk_student_username (username),
    UNIQUE KEY uk_student_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- Table 3: subjects
-- Academic subjects under which exams are categorized
-- ============================================================
CREATE TABLE subjects (
    subject_id INT AUTO_INCREMENT,
    subject_name VARCHAR(100) NOT NULL,
    subject_code VARCHAR(20) NOT NULL,
    description TEXT DEFAULT NULL,
    is_active TINYINT(1) DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (subject_id),
    UNIQUE KEY uk_subject_code (subject_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- Table 4: exams
-- Examination metadata linked to a subject
-- ============================================================
CREATE TABLE exams (
    exam_id INT AUTO_INCREMENT,
    exam_title VARCHAR(200) NOT NULL,
    subject_id INT NOT NULL,
    total_questions INT NOT NULL,
    total_marks INT NOT NULL,
    duration_minutes INT NOT NULL,
    pass_percentage DECIMAL(5,2) DEFAULT 40.00,
    is_active TINYINT(1) DEFAULT 1,
    start_date DATETIME DEFAULT NULL,
    end_date DATETIME DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (exam_id),
    INDEX idx_exam_subject (subject_id),
    CONSTRAINT fk_exam_subject FOREIGN KEY (subject_id)
        REFERENCES subjects(subject_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- Table 5: questions
-- MCQ questions belonging to a specific exam
-- ============================================================
CREATE TABLE questions (
    question_id INT AUTO_INCREMENT,
    exam_id INT NOT NULL,
    question_text TEXT NOT NULL,
    option_a VARCHAR(500) NOT NULL,
    option_b VARCHAR(500) NOT NULL,
    option_c VARCHAR(500) NOT NULL,
    option_d VARCHAR(500) NOT NULL,
    correct_option CHAR(1) NOT NULL COMMENT 'A, B, C, or D',
    marks INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (question_id),
    INDEX idx_question_exam (exam_id),
    CONSTRAINT fk_question_exam FOREIGN KEY (exam_id)
        REFERENCES exams(exam_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- Table 6: exam_attempts
-- Tracks each student's attempt at an exam
-- ============================================================
CREATE TABLE exam_attempts (
    attempt_id INT AUTO_INCREMENT,
    student_id INT NOT NULL,
    exam_id INT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME DEFAULT NULL,
    status ENUM('in_progress', 'completed', 'auto_submitted') DEFAULT 'in_progress',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (attempt_id),
    UNIQUE KEY uk_student_exam (student_id, exam_id),
    INDEX idx_attempt_student (student_id),
    INDEX idx_attempt_exam (exam_id),
    CONSTRAINT fk_attempt_student FOREIGN KEY (student_id)
        REFERENCES students(student_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_attempt_exam FOREIGN KEY (exam_id)
        REFERENCES exams(exam_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- Table 7: answers
-- Individual answers submitted by a student for each question
-- ============================================================
CREATE TABLE answers (
    answer_id INT AUTO_INCREMENT,
    attempt_id INT NOT NULL,
    question_id INT NOT NULL,
    selected_option CHAR(1) DEFAULT NULL COMMENT 'A, B, C, D, or NULL if skipped',
    is_correct TINYINT(1) DEFAULT 0,
    PRIMARY KEY (answer_id),
    INDEX idx_answer_attempt (attempt_id),
    INDEX idx_answer_question (question_id),
    CONSTRAINT fk_answer_attempt FOREIGN KEY (attempt_id)
        REFERENCES exam_attempts(attempt_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_answer_question FOREIGN KEY (question_id)
        REFERENCES questions(question_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- Table 8: results
-- Aggregated result record for each completed exam attempt
-- ============================================================
CREATE TABLE results (
    result_id INT AUTO_INCREMENT,
    attempt_id INT NOT NULL,
    student_id INT NOT NULL,
    exam_id INT NOT NULL,
    total_questions INT NOT NULL,
    attempted INT NOT NULL,
    correct_answers INT NOT NULL,
    wrong_answers INT NOT NULL,
    total_marks INT NOT NULL,
    obtained_marks INT NOT NULL,
    percentage DECIMAL(5,2) NOT NULL,
    status ENUM('Pass', 'Fail') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (result_id),
    UNIQUE KEY uk_result_attempt (attempt_id),
    INDEX idx_result_student (student_id),
    INDEX idx_result_exam (exam_id),
    CONSTRAINT fk_result_attempt FOREIGN KEY (attempt_id)
        REFERENCES exam_attempts(attempt_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_result_student FOREIGN KEY (student_id)
        REFERENCES students(student_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_result_exam FOREIGN KEY (exam_id)
        REFERENCES exams(exam_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- Schema creation complete
-- Run sample_data.sql next to populate test data
-- ============================================================
