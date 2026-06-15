<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${param.pageTitle != null ? param.pageTitle : 'ExamPortal - Online Examination System'}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<!-- Navigation Bar -->
<nav class="navbar navbar-expand-lg navbar-dark glass-navbar sticky-top">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/">
            <i class="bi bi-mortarboard-fill me-2"></i>ExamPortal
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mainNav">
            <ul class="navbar-nav me-auto">
                <c:if test="${sessionScope.role == 'admin'}">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/dashboard"><i class="bi bi-speedometer2 me-1"></i>Dashboard</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/students"><i class="bi bi-people me-1"></i>Students</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/subjects"><i class="bi bi-book me-1"></i>Subjects</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/exams"><i class="bi bi-journal-text me-1"></i>Exams</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/results"><i class="bi bi-graph-up me-1"></i>Results</a></li>
                </c:if>
                <c:if test="${sessionScope.role == 'student'}">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/student/dashboard"><i class="bi bi-speedometer2 me-1"></i>Dashboard</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/student/exams"><i class="bi bi-journal-check me-1"></i>Exams</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/student/results"><i class="bi bi-trophy me-1"></i>Results</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/student/profile"><i class="bi bi-person me-1"></i>Profile</a></li>
                </c:if>
            </ul>
            <ul class="navbar-nav">
                <c:if test="${sessionScope.userId != null}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">
                            <i class="bi bi-person-circle me-1"></i>
                            <c:out value="${sessionScope.user.fullName}"/>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end dropdown-menu-dark">
                            <c:if test="${sessionScope.role == 'student'}">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/student/profile"><i class="bi bi-gear me-2"></i>Profile</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/student/change-password"><i class="bi bi-key me-2"></i>Change Password</a></li>
                                <li><hr class="dropdown-divider"></li>
                            </c:if>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout"><i class="bi bi-box-arrow-right me-2"></i>Logout</a></li>
                        </ul>
                    </li>
                </c:if>
                <c:if test="${sessionScope.userId == null}">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login"><i class="bi bi-box-arrow-in-right me-1"></i>Login</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<main class="main-content">
