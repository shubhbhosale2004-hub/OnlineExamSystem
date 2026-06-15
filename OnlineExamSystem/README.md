# Online Examination System

A full-stack web-based examination platform built with Java Servlets, JSP, MySQL, and Bootstrap 5. Students can take MCQ-based exams with a countdown timer, and administrators can manage all aspects of the examination process.

## Features

- **Student Module**: Register, login, take exams, view results, manage profile
- **Admin Module**: Manage students, subjects, exams, questions, and view statistics
- **MCQ Exams**: Timed exams with question navigation and auto-submit
- **Instant Results**: Score calculation with pass/fail status immediately after submission
- **Security**: BCrypt password hashing, PreparedStatement (SQL injection prevention), session-based authentication, XSS prevention via JSTL `<c:out>`
- **Responsive UI**: Bootstrap 5 with premium dark glassmorphism theme
- **MVC Architecture**: Clean separation of concerns

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Frontend | HTML5, CSS3, JavaScript, Bootstrap 5.3 |
| Backend | Java 17, Jakarta Servlet 6.0, JSP, JSTL 3.0 |
| Database | MySQL 8.x |
| Server | Apache Tomcat 10.1 |
| Build | Maven 3.x |
| Security | jBCrypt 0.4 |

## Prerequisites

- JDK 17 or later
- Apache Tomcat 10.1.x
- MySQL 8.0+
- Maven 3.x (or Eclipse/IntelliJ with Maven support)
- Eclipse IDE or IntelliJ IDEA

## Setup Instructions

### 1. Database Setup

```bash
mysql -u root -p < database/schema.sql
mysql -u root -p < database/sample_data.sql
```

### 2. Configure Database Connection

Edit `src/main/resources/db.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/online_exam_db
db.username=root
db.password=your_password
```

### 3. Build with Maven

```bash
cd OnlineExamSystem
mvn clean package
```

### 4. Deploy to Tomcat

Copy `target/OnlineExamSystem.war` to Tomcat's `webapps/` directory, or:

**Eclipse**: Import as Maven project → Right-click → Run on Server → Select Tomcat 10.1

### 5. Access the Application

| URL | Description |
|-----|-------------|
| `http://localhost:8080/OnlineExamSystem/` | Landing page |
| `http://localhost:8080/OnlineExamSystem/login` | Login page |

### Default Credentials

| Role | Username | Password |
|------|----------|----------|
| Admin | admin | admin123 |
| Student | student1 | pass123 |
| Student | student2 | pass123 |
| Student | student3 | pass123 |

## Project Structure

```
OnlineExamSystem/
├── pom.xml
├── database/
│   ├── schema.sql
│   └── sample_data.sql
├── docs/
│   ├── ProjectReport.md
│   ├── er_diagram.md
│   └── dfd.md
├── screenshots/
│   └── README.md
├── src/main/
│   ├── java/com/exam/
│   │   ├── controller/    (15 Servlets + 1 Filter)
│   │   ├── dao/           (8 Data Access Objects)
│   │   ├── model/         (8 POJO classes)
│   │   └── util/          (3 utility classes)
│   ├── resources/
│   │   └── db.properties
│   └── webapp/
│       ├── WEB-INF/
│       │   ├── web.xml
│       │   └── views/     (20+ JSP pages)
│       ├── css/style.css
│       ├── js/            (3 JavaScript files)
│       └── index.jsp
└── README.md
```

## Manual JAR Setup (Without Maven)

If not using Maven, place these JARs in `WEB-INF/lib/`:

1. `mysql-connector-j-8.3.0.jar`
2. `jbcrypt-0.4.jar`
3. `jakarta.servlet.jsp.jstl-3.0.1.jar`
4. `jakarta.servlet.jsp.jstl-api-3.0.0.jar`

## License

This project is for educational purposes.
