<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="Manage Results"/></jsp:include>
<div class="container-fluid py-4">
    <h3 class="text-white mb-4"><i class="bi bi-graph-up me-2"></i>Exam Results</h3>
    <c:if test="${msg!=null}"><div class="alert alert-success">Operation completed.</div></c:if>
    <div class="card glass-card mb-3"><div class="card-body py-2">
        <form class="d-flex gap-2 align-items-center" method="get" action="${pageContext.request.contextPath}/admin/results">
            <label class="text-light">Filter by Exam:</label>
            <select class="form-select form-select-sm" name="examId" style="width:auto;" onchange="this.form.submit()">
                <option value="">All Exams</option>
                <c:forEach items="${exams}" var="e"><option value="${e.examId}" ${filterExamId==e.examId?'selected':''}><c:out value="${e.examTitle}"/></option></c:forEach>
            </select>
        </form></div></div>
    <div class="card glass-card"><div class="card-body p-0"><div class="table-responsive">
        <table class="table table-dark table-hover mb-0"><thead><tr><th>#</th><th>Student</th><th>Exam</th><th>Total Q</th><th>Correct</th><th>Wrong</th><th>Marks</th><th>%</th><th>Status</th><th>Actions</th></tr></thead>
        <tbody><c:forEach items="${results}" var="r" varStatus="idx"><tr><td>${idx.index+1}</td>
            <td><c:out value="${r.studentName}"/></td><td><c:out value="${r.examTitle}"/></td><td>${r.totalQuestions}</td>
            <td class="text-success">${r.correctAnswers}</td><td class="text-danger">${r.wrongAnswers}</td><td>${r.obtainedMarks}/${r.totalMarks}</td><td>${r.percentage}%</td>
            <td><span class="badge ${r.status=='Pass'?'bg-success':'bg-danger'}">${r.status}</span></td>
            <td><a href="${pageContext.request.contextPath}/admin/results?action=delete&id=${r.resultId}" class="btn btn-sm btn-outline-danger" onclick="return confirm('Delete?')"><i class="bi bi-trash"></i></a></td>
        </tr></c:forEach></tbody></table></div></div></div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
