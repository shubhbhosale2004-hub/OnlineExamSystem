<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="Exam Statistics"/></jsp:include>
<div class="container-fluid py-4">
    <div class="mb-4"><h3 class="text-white"><i class="bi bi-bar-chart-line me-2"></i>Statistics: <c:out value="${exam.examTitle}"/></h3>
        <a href="${pageContext.request.contextPath}/admin/exams" class="text-muted"><i class="bi bi-arrow-left me-1"></i>Back to Exams</a></div>
    <div class="row g-4 mb-4">
        <div class="col-md-2 col-sm-4"><div class="card glass-card text-center p-3"><h3 class="text-white mb-0">${totalAttempts}</h3><small class="text-muted">Total Attempts</small></div></div>
        <div class="col-md-2 col-sm-4"><div class="card glass-card text-center p-3"><h3 class="text-white mb-0">${averagePercentage}%</h3><small class="text-muted">Average Score</small></div></div>
        <div class="col-md-2 col-sm-4"><div class="card glass-card text-center p-3"><h3 class="text-success mb-0">${passCount}</h3><small class="text-muted">Passed</small></div></div>
        <div class="col-md-2 col-sm-4"><div class="card glass-card text-center p-3"><h3 class="text-danger mb-0">${failCount}</h3><small class="text-muted">Failed</small></div></div>
        <div class="col-md-2 col-sm-4"><div class="card glass-card text-center p-3"><h3 class="text-info mb-0">${highestScore}%</h3><small class="text-muted">Highest</small></div></div>
        <div class="col-md-2 col-sm-4"><div class="card glass-card text-center p-3"><h3 class="text-warning mb-0">${lowestScore}%</h3><small class="text-muted">Lowest</small></div></div>
    </div>
    <div class="card glass-card"><div class="card-header"><h5 class="text-white mb-0">Individual Results</h5></div><div class="card-body p-0"><div class="table-responsive">
        <table class="table table-dark table-hover mb-0"><thead><tr><th>#</th><th>Student</th><th>Correct</th><th>Wrong</th><th>Marks</th><th>Percentage</th><th>Status</th></tr></thead>
        <tbody><c:forEach items="${results}" var="r" varStatus="idx"><tr><td>${idx.index+1}</td><td><c:out value="${r.studentName}"/></td>
            <td class="text-success">${r.correctAnswers}</td><td class="text-danger">${r.wrongAnswers}</td><td>${r.obtainedMarks}/${r.totalMarks}</td>
            <td>${r.percentage}%</td><td><span class="badge ${r.status=='Pass'?'bg-success':'bg-danger'}">${r.status}</span></td></tr></c:forEach></tbody></table>
    </div></div></div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
