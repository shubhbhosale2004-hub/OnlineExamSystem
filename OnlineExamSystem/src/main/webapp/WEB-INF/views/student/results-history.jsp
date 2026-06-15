<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="Results History"/></jsp:include>
<div class="container py-4">
    <h3 class="text-white mb-4"><i class="bi bi-trophy me-2"></i>My Results</h3>
    <div class="card glass-card">
        <div class="card-body p-0">
            <c:choose>
                <c:when test="${not empty results}">
                    <div class="table-responsive">
                        <table class="table table-dark table-hover mb-0">
                            <thead><tr><th>#</th><th>Exam</th><th>Total Q</th><th>Correct</th><th>Wrong</th><th>Marks</th><th>Percentage</th><th>Status</th><th>Action</th></tr></thead>
                            <tbody>
                                <c:forEach items="${results}" var="r" varStatus="idx">
                                    <tr>
                                        <td>${idx.index + 1}</td>
                                        <td><c:out value="${r.examTitle}"/></td>
                                        <td>${r.totalQuestions}</td>
                                        <td class="text-success">${r.correctAnswers}</td>
                                        <td class="text-danger">${r.wrongAnswers}</td>
                                        <td>${r.obtainedMarks}/${r.totalMarks}</td>
                                        <td>${r.percentage}%</td>
                                        <td><span class="badge ${r.status == 'Pass' ? 'bg-success' : 'bg-danger'}">${r.status}</span></td>
                                        <td><a href="${pageContext.request.contextPath}/student/result?attemptId=${r.attemptId}" class="btn btn-sm btn-outline-info">View</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise><p class="text-muted text-center py-4">No results to display.</p></c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
