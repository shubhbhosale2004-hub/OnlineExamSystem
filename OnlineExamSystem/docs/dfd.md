# Data Flow Diagrams — Online Examination System

## DFD Level 0 (Context Diagram)

```mermaid
flowchart LR
    Student["Student\n(External Entity)"] -->|Login, Take Exam, View Results| System["Online Examination\nSystem"]
    Admin["Admin\n(External Entity)"] -->|Manage Students, Exams, Questions| System
    System -->|Exam Questions, Results| Student
    System -->|Reports, Statistics| Admin
    System <-->|Read/Write Data| DB[("MySQL\nDatabase")]
```

## DFD Level 1 (Detailed Data Flow)

```mermaid
flowchart TB
    Student["Student"] --> P1["1.0\nAuthentication"]
    Admin["Admin"] --> P1
    P1 -->|Valid Session| P2["2.0\nStudent Management"]
    P1 -->|Valid Session| P3["3.0\nExam Management"]
    P1 -->|Valid Session| P4["4.0\nQuestion Processing"]
    P1 -->|Valid Session| P5["5.0\nResult Generation"]

    P2 <-->|Student Records| D1[("D1: students")]
    P3 <-->|Exam & Subject Records| D2[("D2: exams")]
    P3 <-->|Subject Records| D3[("D3: subjects")]
    P4 <-->|Question Records| D4[("D4: questions")]
    P4 <-->|Answer Records| D5[("D5: answers")]
    P5 <-->|Attempt Records| D6[("D6: exam_attempts")]
    P5 <-->|Result Records| D7[("D7: results")]

    Student -->|Take Exam| P4
    P4 -->|Submitted Answers| P5
    P5 -->|Score & Status| Student
    P5 -->|Statistics| Admin

    Admin -->|CRUD Operations| P2
    Admin -->|CRUD Operations| P3
    Admin -->|CRUD Operations| P4
    Admin -->|View Analytics| P5
```

### Process Descriptions

| Process | Description |
|---------|-------------|
| 1.0 Authentication | Validates user credentials, creates sessions, handles login/logout |
| 2.0 Student Management | Admin adds/edits/deletes students; students update their profiles |
| 3.0 Exam Management | Admin creates exams linked to subjects with duration and pass criteria |
| 4.0 Question Processing | Admin manages MCQ questions; students answer questions during exams |
| 5.0 Result Generation | Calculates scores, determines pass/fail, generates statistics |
