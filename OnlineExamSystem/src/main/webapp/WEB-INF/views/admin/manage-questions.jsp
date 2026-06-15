<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="Manage Questions"/></jsp:include>
<div class="container-fluid py-4">
    <div class="d-flex justify-content-between mb-4">
        <div><h3 class="text-white"><i class="bi bi-list-check me-2"></i>Questions for: <c:out value="${exam.examTitle}"/></h3>
            <a href="${pageContext.request.contextPath}/admin/exams" class="text-muted"><i class="bi bi-arrow-left me-1"></i>Back to Exams</a></div>
        <a href="${pageContext.request.contextPath}/admin/questions?action=add&examId=${exam.examId}" class="btn btn-gradient"><i class="bi bi-plus-lg me-1"></i>Add Question</a></div>
    <c:if test="${msg!=null}"><div class="alert alert-success">Operation completed.</div></c:if>
    <div class="card glass-card"><div class="card-body p-0"><div class="table-responsive">
        <table class="table table-dark table-hover mb-0"><thead><tr><th>#</th><th>Question</th><th>A</th><th>B</th><th>C</th><th>D</th><th>Answer</th><th>Marks</th><th>Actions</th></tr></thead>
        <tbody><c:forEach items="${questions}" var="q" varStatus="idx"><tr>
            <td>${idx.index+1}</td><td style="max-width:250px;"><c:out value="${q.questionText}"/></td>
            <td><c:out value="${q.optionA}"/></td><td><c:out value="${q.optionB}"/></td><td><c:out value="${q.optionC}"/></td><td><c:out value="${q.optionD}"/></td>
            <td><span class="badge bg-success">${q.correctOption}</span></td><td>${q.marks}</td>
            <td><a href="${pageContext.request.contextPath}/admin/questions?action=edit&id=${q.questionId}&examId=${exam.examId}" class="btn btn-sm btn-outline-info"><i class="bi bi-pencil"></i></a>
                <a href="${pageContext.request.contextPath}/admin/questions?action=delete&id=${q.questionId}&examId=${exam.examId}" class="btn btn-sm btn-outline-danger" onclick="return confirm('Delete?')"><i class="bi bi-trash"></i></a></td>
        </tr></c:forEach></tbody></table></div></div></div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
