<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="${exam != null ? 'Edit Exam' : 'Add Exam'}"/></jsp:include>
<div class="container py-4"><div class="row justify-content-center"><div class="col-md-8">
    <h3 class="text-white mb-4"><i class="bi bi-journal-plus me-2"></i>${exam != null ? 'Edit Exam' : 'Add New Exam'}</h3>
    <div class="card glass-card"><div class="card-body p-4">
        <form action="${pageContext.request.contextPath}/admin/exams" method="post">
            <input type="hidden" name="action" value="${exam != null ? 'edit' : 'add'}">
            <c:if test="${exam != null}"><input type="hidden" name="examId" value="${exam.examId}"></c:if>
            <div class="row g-3">
                <div class="col-md-8"><label class="form-label text-light">Exam Title</label><input type="text" class="form-control" name="examTitle" value="<c:out value='${exam.examTitle}'/>" required></div>
                <div class="col-md-4"><label class="form-label text-light">Subject</label>
                    <select class="form-select" name="subjectId" required><option value="">Select Subject</option>
                        <c:forEach items="${subjects}" var="s"><option value="${s.subjectId}" ${exam!=null&&exam.subjectId==s.subjectId?'selected':''}><c:out value="${s.subjectName}"/></option></c:forEach></select></div>
                <div class="col-md-4"><label class="form-label text-light">Total Questions</label><input type="number" class="form-control" name="totalQuestions" value="${exam!=null?exam.totalQuestions:10}" min="1" required></div>
                <div class="col-md-4"><label class="form-label text-light">Total Marks</label><input type="number" class="form-control" name="totalMarks" value="${exam!=null?exam.totalMarks:10}" min="1" required></div>
                <div class="col-md-4"><label class="form-label text-light">Duration (minutes)</label><input type="number" class="form-control" name="durationMinutes" value="${exam!=null?exam.durationMinutes:30}" min="1" required></div>
                <div class="col-md-4"><label class="form-label text-light">Pass Percentage</label><input type="number" class="form-control" name="passPercentage" value="${exam!=null?exam.passPercentage:40}" step="0.01" min="0" max="100" required></div>
                <div class="col-md-4"><label class="form-label text-light">Start Date</label><input type="datetime-local" class="form-control" name="startDate" value="${exam!=null?exam.startDate:''}"></div>
                <div class="col-md-4"><label class="form-label text-light">End Date</label><input type="datetime-local" class="form-control" name="endDate" value="${exam!=null?exam.endDate:''}"></div>
                <div class="col-md-4"><div class="form-check mt-4"><input type="checkbox" class="form-check-input" name="isActive" ${exam==null||exam.isActive?'checked':''}><label class="form-check-label text-light">Active</label></div></div>
                <div class="col-12"><button type="submit" class="btn btn-gradient"><i class="bi bi-check-lg me-1"></i>${exam != null ? 'Update' : 'Add'} Exam</button>
                    <a href="${pageContext.request.contextPath}/admin/exams" class="btn btn-outline-light ms-2">Cancel</a></div>
            </div>
        </form></div></div></div></div></div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
