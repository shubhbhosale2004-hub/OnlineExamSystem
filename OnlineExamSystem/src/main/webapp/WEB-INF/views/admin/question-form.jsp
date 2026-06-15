<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="${question != null ? 'Edit Question' : 'Add Question'}"/></jsp:include>
<div class="container py-4"><div class="row justify-content-center"><div class="col-md-8">
    <h3 class="text-white mb-4"><i class="bi bi-patch-question me-2"></i>${question != null ? 'Edit' : 'Add'} Question for: <c:out value="${exam.examTitle}"/></h3>
    <div class="card glass-card"><div class="card-body p-4">
        <form action="${pageContext.request.contextPath}/admin/questions" method="post">
            <input type="hidden" name="action" value="${question != null ? 'edit' : 'add'}">
            <input type="hidden" name="examId" value="${exam.examId}">
            <c:if test="${question != null}"><input type="hidden" name="questionId" value="${question.questionId}"></c:if>
            <div class="mb-3"><label class="form-label text-light">Question Text</label><textarea class="form-control" name="questionText" rows="3" required><c:out value="${question.questionText}"/></textarea></div>
            <div class="row g-3">
                <div class="col-md-6"><label class="form-label text-light">Option A</label><input type="text" class="form-control" name="optionA" value="<c:out value='${question.optionA}'/>" required></div>
                <div class="col-md-6"><label class="form-label text-light">Option B</label><input type="text" class="form-control" name="optionB" value="<c:out value='${question.optionB}'/>" required></div>
                <div class="col-md-6"><label class="form-label text-light">Option C</label><input type="text" class="form-control" name="optionC" value="<c:out value='${question.optionC}'/>" required></div>
                <div class="col-md-6"><label class="form-label text-light">Option D</label><input type="text" class="form-control" name="optionD" value="<c:out value='${question.optionD}'/>" required></div>
                <div class="col-md-6"><label class="form-label text-light">Correct Option</label>
                    <select class="form-select" name="correctOption" required><option value="">Select</option>
                        <option value="A" ${question.correctOption=='A'?'selected':''}>A</option><option value="B" ${question.correctOption=='B'?'selected':''}>B</option>
                        <option value="C" ${question.correctOption=='C'?'selected':''}>C</option><option value="D" ${question.correctOption=='D'?'selected':''}>D</option></select></div>
                <div class="col-md-6"><label class="form-label text-light">Marks</label><input type="number" class="form-control" name="marks" value="${question != null ? question.marks : 1}" min="1" required></div>
                <div class="col-12"><button type="submit" class="btn btn-gradient"><i class="bi bi-check-lg me-1"></i>Save Question</button>
                    <a href="${pageContext.request.contextPath}/admin/questions?examId=${exam.examId}" class="btn btn-outline-light ms-2">Cancel</a></div>
            </div>
        </form></div></div></div></div></div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
