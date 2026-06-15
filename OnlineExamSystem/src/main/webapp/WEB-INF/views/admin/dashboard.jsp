<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="Admin Dashboard"/></jsp:include>
<div class="container-fluid py-4">
    <div class="welcome-banner mb-4 p-4 rounded-3 text-white">
        <h2 class="fw-bold"><i class="bi bi-shield-check me-2"></i>Admin Dashboard</h2>
        <p class="mb-0 opacity-75">Manage your examination system from here.</p>
    </div>
    <div class="row g-4 mb-4">
        <div class="col-md-3 col-sm-6"><div class="card glass-card stat-card border-start-primary animate-fadeIn"><div class="card-body"><div class="d-flex align-items-center"><div class="stat-icon bg-primary-soft"><i class="bi bi-people"></i></div><div class="ms-3"><h3 class="mb-0 text-white counter">${totalStudents}</h3><small class="text-muted">Students</small></div></div></div></div></div>
        <div class="col-md-3 col-sm-6"><div class="card glass-card stat-card border-start-success animate-fadeIn"><div class="card-body"><div class="d-flex align-items-center"><div class="stat-icon bg-success-soft"><i class="bi bi-book"></i></div><div class="ms-3"><h3 class="mb-0 text-white counter">${totalSubjects}</h3><small class="text-muted">Subjects</small></div></div></div></div></div>
        <div class="col-md-3 col-sm-6"><div class="card glass-card stat-card border-start-warning animate-fadeIn"><div class="card-body"><div class="d-flex align-items-center"><div class="stat-icon bg-warning-soft"><i class="bi bi-journal-text"></i></div><div class="ms-3"><h3 class="mb-0 text-white counter">${totalExams}</h3><small class="text-muted">Exams</small></div></div></div></div></div>
        <div class="col-md-3 col-sm-6"><div class="card glass-card stat-card border-start-info animate-fadeIn"><div class="card-body"><div class="d-flex align-items-center"><div class="stat-icon bg-info-soft"><i class="bi bi-graph-up"></i></div><div class="ms-3"><h3 class="mb-0 text-white counter">${totalResults}</h3><small class="text-muted">Results</small></div></div></div></div></div>
    </div>
    <div class="card glass-card">
        <div class="card-header d-flex justify-content-between"><h5 class="mb-0 text-white"><i class="bi bi-clock-history me-2"></i>Recent Results</h5><a href="${pageContext.request.contextPath}/admin/results" class="btn btn-sm btn-outline-light">View All</a></div>
        <div class="card-body p-0">
            <c:if test="${not empty recentResults}">
            <div class="table-responsive"><table class="table table-dark table-hover mb-0">
                <thead><tr><th>Student</th><th>Exam</th><th>Score</th><th>%</th><th>Status</th></tr></thead>
                <tbody><c:forEach items="${recentResults}" var="r"><tr>
                    <td><c:out value="${r.studentName}"/></td><td><c:out value="${r.examTitle}"/></td>
                    <td>${r.obtainedMarks}/${r.totalMarks}</td><td>${r.percentage}%</td>
                    <td><span class="badge ${r.status=='Pass'?'bg-success':'bg-danger'}">${r.status}</span></td>
                </tr></c:forEach></tbody>
            </table></div>
            </c:if>
        </div>
    </div>
    <div class="mt-4 text-center">
        <a href="${pageContext.request.contextPath}/admin/students" class="btn btn-gradient me-2"><i class="bi bi-people me-1"></i>Manage Students</a>
        <a href="${pageContext.request.contextPath}/admin/exams" class="btn btn-outline-light me-2"><i class="bi bi-journal me-1"></i>Manage Exams</a>
        <a href="${pageContext.request.contextPath}/admin/results" class="btn btn-outline-light"><i class="bi bi-graph-up me-1"></i>View Results</a>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
