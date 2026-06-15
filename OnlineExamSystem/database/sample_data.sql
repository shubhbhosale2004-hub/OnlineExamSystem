-- ============================================================
-- Online Examination System - Sample Test Data
-- Run this AFTER schema.sql
-- ============================================================

USE online_exam_db;

-- ============================================================
-- Admin user (password: admin123)
-- BCrypt hash generated with work factor 12
-- ============================================================
INSERT INTO admin (username, password, full_name, email) VALUES
('admin', '$2a$12$LJ3m4ys3GFP5Jk3pS8v2eOZJFdNr8V5xjy5r5F4vF6dXKqNvDqKNe', 'System Administrator', 'admin@examportal.com');

-- ============================================================
-- Student accounts (password for all: pass123)
-- BCrypt hash of 'pass123'
-- ============================================================
INSERT INTO students (username, password, full_name, email, phone, gender, dob, address, is_active) VALUES
('student1', '$2a$12$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'John Doe', 'john.doe@student.com', '9876543210', 'Male', '2000-05-15', '123 Main Street, New York, NY 10001', 1),
('student2', '$2a$12$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Jane Smith', 'jane.smith@student.com', '9876543211', 'Female', '2001-08-22', '456 Oak Avenue, Los Angeles, CA 90001', 1),
('student3', '$2a$12$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Bob Wilson', 'bob.wilson@student.com', '9876543212', 'Male', '1999-12-10', '789 Pine Road, Chicago, IL 60601', 1),
('student4', '$2a$12$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Alice Brown', 'alice.brown@student.com', '9876543213', 'Female', '2002-03-18', '321 Elm Street, Houston, TX 77001', 1),
('student5', '$2a$12$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Charlie Davis', 'charlie.davis@student.com', '9876543214', 'Male', '2001-07-04', '654 Maple Drive, Phoenix, AZ 85001', 1);

-- ============================================================
-- Subjects
-- ============================================================
INSERT INTO subjects (subject_name, subject_code, description, is_active) VALUES
('Java Programming', 'JAVA101', 'Core Java concepts including OOP, collections, exception handling, and multithreading.', 1),
('Python Programming', 'PY101', 'Python fundamentals covering data types, functions, modules, and file handling.', 1),
('Database Management Systems', 'DBMS101', 'Relational database concepts, SQL queries, normalization, and transactions.', 1),
('Web Development', 'WEB101', 'HTML, CSS, JavaScript fundamentals and responsive web design principles.', 1);

-- ============================================================
-- Exams (one per subject, scheduled broadly for testing)
-- ============================================================
INSERT INTO exams (exam_title, subject_id, total_questions, total_marks, duration_minutes, pass_percentage, is_active, start_date, end_date) VALUES
('Java Fundamentals Test', 1, 10, 10, 30, 40.00, 1, '2025-01-01 00:00:00', '2027-12-31 23:59:59'),
('Python Basics Assessment', 2, 10, 10, 30, 40.00, 1, '2025-01-01 00:00:00', '2027-12-31 23:59:59'),
('DBMS Concepts Quiz', 3, 10, 10, 30, 40.00, 1, '2025-01-01 00:00:00', '2027-12-31 23:59:59'),
('Web Development Essentials', 4, 10, 10, 30, 40.00, 1, '2025-01-01 00:00:00', '2027-12-31 23:59:59');

-- ============================================================
-- Questions for Exam 1: Java Fundamentals Test
-- ============================================================
INSERT INTO questions (exam_id, question_text, option_a, option_b, option_c, option_d, correct_option, marks) VALUES
(1, 'Which keyword is used to define a class in Java?', 'struct', 'class', 'define', 'object', 'B', 1),
(1, 'What is the default value of an int variable in Java?', '1', 'null', '0', 'undefined', 'C', 1),
(1, 'Which method is the entry point of a Java application?', 'start()', 'run()', 'main()', 'init()', 'C', 1),
(1, 'Which of the following is NOT a primitive data type in Java?', 'int', 'String', 'boolean', 'char', 'B', 1),
(1, 'What does JVM stand for?', 'Java Virtual Machine', 'Java Variable Manager', 'Java Version Module', 'Java Verified Method', 'A', 1),
(1, 'Which access modifier makes a member accessible only within the same class?', 'public', 'protected', 'private', 'default', 'C', 1),
(1, 'Which collection class allows duplicate elements?', 'HashSet', 'TreeSet', 'ArrayList', 'LinkedHashSet', 'C', 1),
(1, 'What is the parent class of all classes in Java?', 'Base', 'Root', 'Object', 'Main', 'C', 1),
(1, 'Which keyword is used to inherit a class in Java?', 'implements', 'inherits', 'extends', 'super', 'C', 1),
(1, 'What is the size of an int in Java?', '2 bytes', '4 bytes', '8 bytes', '16 bytes', 'B', 1);

-- ============================================================
-- Questions for Exam 2: Python Basics Assessment
-- ============================================================
INSERT INTO questions (exam_id, question_text, option_a, option_b, option_c, option_d, correct_option, marks) VALUES
(2, 'Which symbol is used for comments in Python?', '//', '/* */', '#', '--', 'C', 1),
(2, 'What is the output of print(type(5))?', '<class ''float''>', '<class ''int''>', '<class ''number''>', '<class ''str''>', 'B', 1),
(2, 'Which keyword is used to define a function in Python?', 'function', 'func', 'def', 'define', 'C', 1),
(2, 'What does the len() function return?', 'Data type', 'Memory size', 'Number of elements', 'Index position', 'C', 1),
(2, 'Which data structure uses key-value pairs in Python?', 'List', 'Tuple', 'Set', 'Dictionary', 'D', 1),
(2, 'How do you start a for loop in Python?', 'for (i=0; i<n; i++)', 'for i in range(n):', 'foreach i in n:', 'loop i from 0 to n:', 'B', 1),
(2, 'Which of these is an immutable data type in Python?', 'List', 'Dictionary', 'Set', 'Tuple', 'D', 1),
(2, 'What operator is used for exponentiation in Python?', '^', '**', 'exp()', '^^', 'B', 1),
(2, 'Which method adds an element to the end of a list?', 'insert()', 'add()', 'append()', 'push()', 'C', 1),
(2, 'What is the correct file extension for Python files?', '.python', '.pt', '.py', '.pyt', 'C', 1);

-- ============================================================
-- Questions for Exam 3: DBMS Concepts Quiz
-- ============================================================
INSERT INTO questions (exam_id, question_text, option_a, option_b, option_c, option_d, correct_option, marks) VALUES
(3, 'What does SQL stand for?', 'Structured Query Language', 'Simple Question Language', 'Sequential Query Logic', 'Standard Query Library', 'A', 1),
(3, 'Which SQL command is used to retrieve data from a table?', 'GET', 'FETCH', 'SELECT', 'RETRIEVE', 'C', 1),
(3, 'What is a primary key?', 'A foreign reference', 'A unique identifier for each row', 'An index column', 'A table name', 'B', 1),
(3, 'Which normal form eliminates transitive dependencies?', 'First Normal Form', 'Second Normal Form', 'Third Normal Form', 'BCNF', 'C', 1),
(3, 'Which SQL clause is used to filter grouped results?', 'WHERE', 'FILTER', 'HAVING', 'GROUP BY', 'C', 1),
(3, 'What type of join returns all records from both tables?', 'INNER JOIN', 'LEFT JOIN', 'RIGHT JOIN', 'FULL OUTER JOIN', 'D', 1),
(3, 'Which command is used to remove a table from the database?', 'DELETE TABLE', 'REMOVE TABLE', 'DROP TABLE', 'DESTROY TABLE', 'C', 1),
(3, 'What does ACID stand for in database transactions?', 'Atomicity, Consistency, Isolation, Durability', 'Access, Control, Integrity, Data', 'Authentication, Cipher, Index, Database', 'Attribute, Column, Index, Domain', 'A', 1),
(3, 'Which SQL command modifies existing records?', 'MODIFY', 'ALTER', 'UPDATE', 'CHANGE', 'C', 1),
(3, 'What is a foreign key?', 'A primary key in another table', 'A reference to a primary key in another table', 'An encrypted key', 'A composite key', 'B', 1);

-- ============================================================
-- Questions for Exam 4: Web Development Essentials
-- ============================================================
INSERT INTO questions (exam_id, question_text, option_a, option_b, option_c, option_d, correct_option, marks) VALUES
(4, 'What does HTML stand for?', 'Hyper Transfer Markup Language', 'HyperText Markup Language', 'High Tech Modern Language', 'Home Tool Markup Language', 'B', 1),
(4, 'Which CSS property changes the text color?', 'text-color', 'font-color', 'color', 'text-style', 'C', 1),
(4, 'Which HTML element is used for the largest heading?', '<heading>', '<h6>', '<head>', '<h1>', 'D', 1),
(4, 'What does CSS stand for?', 'Computer Style Sheets', 'Cascading Style Sheets', 'Creative Style System', 'Colorful Style Sheets', 'B', 1),
(4, 'Which JavaScript method writes to the browser console?', 'print()', 'log()', 'console.log()', 'document.write()', 'C', 1),
(4, 'Which HTML attribute specifies an alternate text for an image?', 'title', 'src', 'alt', 'caption', 'C', 1),
(4, 'Which property is used to change the background color in CSS?', 'bgcolor', 'background-color', 'color-background', 'back-color', 'B', 1),
(4, 'What is the correct syntax for linking an external CSS file?', '<style src="style.css">', '<link rel="stylesheet" href="style.css">', '<css href="style.css">', '<stylesheet>style.css</stylesheet>', 'B', 1),
(4, 'Which JavaScript keyword declares a variable?', 'var', 'dim', 'variable', 'declare', 'A', 1),
(4, 'Which HTML element defines a hyperlink?', '<link>', '<href>', '<a>', '<url>', 'C', 1);

-- ============================================================
-- Sample data insertion complete
-- Default credentials:
--   Admin:   admin / admin123
--   Student: student1 / pass123 (through student5)
-- ============================================================
