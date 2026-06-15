<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="Student Dashboard"/></jsp:include>

<div class="container-fluid py-4">
    <!-- Welcome Banner -->
    <div class="welcome-banner mb-4 p-4 rounded-3 text-white">
        <h2 class="fw-bold"><i class="bi bi-emoji-smile me-2"></i>Welcome, <c:out value="${student.fullName}"/>!</h2>
        <p class="mb-0 opacity-75">Ready to test your knowledge? Check out available exams below.</p>
    </div>

    <!-- Stats Cards -->
    <div class="row g-4 mb-4">
        <div class="col-md-3 col-sm-6">
            <div class="card glass-card stat-card border-start-primary animate-fadeIn">
                <div class="card-body"><div class="d-flex align-items-center">
                    <div class="stat-icon bg-primary-soft"><i class="bi bi-journal-check"></i></div>
                    <div class="ms-3"><h3 class="mb-0 text-white counter">${availableExamsCount}</h3><small class="text-muted">Available Exams</small></div>
                </div></div>
            </div>
        </div>
        <div class="col-md-3 col-sm-6">
            <div class="card glass-card stat-card border-start-success animate-fadeIn">
                <div class="card-body"><div class="d-flex align-items-center">
                    <div class="stat-icon bg-success-soft"><i class="bi bi-trophy"></i></div>
                    <div class="ms-3"><h3 class="mb-0 text-white counter">${completedExamsCount}</h3><small class="text-muted">Completed</small></div>
                </div></div>
            </div>
        </div>
        <div class="col-md-3 col-sm-6">
            <div class="card glass-card stat-card border-start-warning animate-fadeIn">
                <div class="card-body"><div class="d-flex align-items-center">
                    <div class="stat-icon bg-warning-soft"><i class="bi bi-percent"></i></div>
                    <div class="ms-3"><h3 class="mb-0 text-white">${averageScore}%</h3><small class="text-muted">Average Score</small></div>
                </div></div>
            </div>
        </div>
        <div class="col-md-3 col-sm-6">
            <div class="card glass-card stat-card border-start-info animate-fadeIn">
                <div class="card-body"><div class="d-flex align-items-center">
                    <div class="stat-icon bg-info-soft"><i class="bi bi-bar-chart-line"></i></div>
                    <div class="ms-3"><h3 class="mb-0 text-white counter">${totalResults}</h3><small class="text-muted">Total Results</small></div>
                </div></div>
            </div>
        </div>
    </div>

    <!-- Recent Results -->
    <div class="card glass-card">
        <div class="card-header d-flex justify-content-between align-items-center">
            <h5 class="mb-0 text-white"><i class="bi bi-clock-history me-2"></i>Recent Results</h5>
            <a href="${pageContext.request.contextPath}/student/results" class="btn btn-sm btn-outline-light">View All</a>
        </div>
        <div class="card-body p-0">
            <c:choose>
                <c:when test="${not empty recentResults}">
                    <div class="table-responsive">
                        <table class="table table-dark table-hover mb-0">
                            <thead><tr><th>Exam</th><th>Score</th><th>Percentage</th><th>Status</th></tr></thead>
                            <tbody>
                                <c:forEach items="${recentResults}" var="r">
                                    <tr>
                                        <td><c:out value="${r.examTitle}"/></td>
                                        <td>${r.obtainedMarks}/${r.totalMarks}</td>
                                        <td>${r.percentage}%</td>
                                        <td><span class="badge ${r.status == 'Pass' ? 'bg-success' : 'bg-danger'}">${r.status}</span></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise><p class="text-muted text-center py-4">No results yet. Start an exam to see your scores here!</p></c:otherwise>
            </c:choose>
        </div>
    </div>

    <!-- Quick Actions -->
    <div class="mt-4 text-center">
        <a href="${pageContext.request.contextPath}/student/exams" class="btn btn-gradient me-2"><i class="bi bi-play-circle me-1"></i>View Exams</a>
        <a href="${pageContext.request.contextPath}/student/results" class="btn btn-outline-light"><i class="bi bi-trophy me-1"></i>All Results</a>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
