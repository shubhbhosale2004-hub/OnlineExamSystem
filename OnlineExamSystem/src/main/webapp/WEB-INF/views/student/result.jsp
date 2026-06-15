<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="Exam Result"/></jsp:include>
<div class="container py-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card glass-card animate-fadeIn">
                <div class="card-header text-center py-4 ${result.status == 'Pass' ? 'bg-success-gradient' : 'bg-danger-gradient'}">
                    <h2 class="text-white fw-bold mb-1">${result.status == 'Pass' ? '🎉 Congratulations!' : '😔 Better Luck Next Time'}</h2>
                    <p class="text-white-50 mb-0"><c:out value="${result.examTitle}"/></p>
                </div>
                <div class="card-body p-4 text-center">
                    <!-- Score Circle -->
                    <div class="score-circle mx-auto mb-4" style="--percentage: ${result.percentage}">
                        <span class="score-value text-white">${result.percentage}%</span>
                    </div>
                    <span class="badge ${result.status == 'Pass' ? 'bg-success' : 'bg-danger'} fs-5 px-4 py-2 mb-4">${result.status}</span>

                    <!-- Stats Grid -->
                    <div class="row g-3 mt-3 text-start">
                        <div class="col-md-4"><div class="p-3 rounded bg-dark"><small class="text-muted">Total Questions</small><h4 class="text-white mb-0">${result.totalQuestions}</h4></div></div>
                        <div class="col-md-4"><div class="p-3 rounded bg-dark"><small class="text-muted">Attempted</small><h4 class="text-white mb-0">${result.attempted}</h4></div></div>
                        <div class="col-md-4"><div class="p-3 rounded bg-dark"><small class="text-muted">Unanswered</small><h4 class="text-white mb-0">${result.totalQuestions - result.attempted}</h4></div></div>
                        <div class="col-md-4"><div class="p-3 rounded bg-dark"><small class="text-success">Correct</small><h4 class="text-success mb-0">${result.correctAnswers}</h4></div></div>
                        <div class="col-md-4"><div class="p-3 rounded bg-dark"><small class="text-danger">Wrong</small><h4 class="text-danger mb-0">${result.wrongAnswers}</h4></div></div>
                        <div class="col-md-4"><div class="p-3 rounded bg-dark"><small class="text-muted">Marks</small><h4 class="text-white mb-0">${result.obtainedMarks}/${result.totalMarks}</h4></div></div>
                    </div>

                    <div class="mt-4">
                        <a href="${pageContext.request.contextPath}/student/dashboard" class="btn btn-gradient me-2"><i class="bi bi-house me-1"></i>Dashboard</a>
                        <a href="${pageContext.request.contextPath}/student/results" class="btn btn-outline-light"><i class="bi bi-trophy me-1"></i>All Results</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
