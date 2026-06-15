<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="Manage Exams"/></jsp:include>
<div class="container-fluid py-4">
    <div class="d-flex justify-content-between mb-4"><h3 class="text-white"><i class="bi bi-journal-text me-2"></i>Manage Exams</h3>
        <a href="${pageContext.request.contextPath}/admin/exams?action=add" class="btn btn-gradient"><i class="bi bi-plus-lg me-1"></i>Add Exam</a></div>
    <c:if test="${msg!=null}"><div class="alert alert-success">Operation completed successfully.</div></c:if>
    <div class="card glass-card"><div class="card-body p-0"><div class="table-responsive">
        <table class="table table-dark table-hover mb-0"><thead><tr><th>#</th><th>Title</th><th>Subject</th><th>Questions</th><th>Duration</th><th>Marks</th><th>Pass%</th><th>Status</th><th>Actions</th></tr></thead>
        <tbody><c:forEach items="${exams}" var="e" varStatus="idx"><tr><td>${idx.index+1}</td><td><c:out value="${e.examTitle}"/></td><td><c:out value="${e.subjectName}"/></td>
            <td>${e.totalQuestions}</td><td>${e.durationMinutes}m</td><td>${e.totalMarks}</td><td>${e.passPercentage}%</td>
            <td><span class="badge ${e.isActive?'bg-success':'bg-secondary'}">${e.isActive?'Active':'Inactive'}</span></td>
            <td><a href="${pageContext.request.contextPath}/admin/exams?action=edit&id=${e.examId}" class="btn btn-sm btn-outline-info" title="Edit"><i class="bi bi-pencil"></i></a>
                <a href="${pageContext.request.contextPath}/admin/questions?examId=${e.examId}" class="btn btn-sm btn-outline-warning" title="Questions"><i class="bi bi-list-check"></i></a>
                <a href="${pageContext.request.contextPath}/admin/exam-stats?examId=${e.examId}" class="btn btn-sm btn-outline-success" title="Stats"><i class="bi bi-bar-chart"></i></a>
                <a href="${pageContext.request.contextPath}/admin/exams?action=delete&id=${e.examId}" class="btn btn-sm btn-outline-danger" onclick="return confirm('Delete?')" title="Delete"><i class="bi bi-trash"></i></a></td>
        </tr></c:forEach></tbody></table></div></div></div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
