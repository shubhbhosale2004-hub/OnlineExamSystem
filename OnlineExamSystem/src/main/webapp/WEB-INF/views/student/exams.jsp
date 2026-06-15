<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="Available Exams"/></jsp:include>
<div class="container py-4">
    <h3 class="text-white mb-4"><i class="bi bi-journal-check me-2"></i>Available Examinations</h3>
    <c:choose>
        <c:when test="${not empty exams}">
            <div class="row g-4">
                <c:forEach items="${exams}" var="exam">
                    <div class="col-md-4 col-sm-6">
                        <div class="card glass-card exam-card h-100 animate-fadeIn">
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title text-white fw-bold"><c:out value="${exam.examTitle}"/></h5>
                                <p class="text-info mb-2"><i class="bi bi-book me-1"></i><c:out value="${exam.subjectName}"/></p>
                                <ul class="list-unstyled text-muted small flex-grow-1">
                                    <li><i class="bi bi-check2-circle me-1"></i>Questions: ${exam.totalQuestions}</li>
                                    <li><i class="bi bi-star me-1"></i>Total Marks: ${exam.totalMarks}</li>
                                    <li><i class="bi bi-clock me-1"></i>Duration: ${exam.durationMinutes} minutes</li>
                                    <li><i class="bi bi-graph-up me-1"></i>Pass: ${exam.passPercentage}%</li>
                                </ul>
                                <c:choose>
                                    <c:when test="${attemptedExamIds.contains(exam.examId)}">
                                        <button class="btn btn-secondary w-100" disabled><i class="bi bi-check-circle-fill me-1"></i>Already Attempted</button>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${pageContext.request.contextPath}/student/start-exam?examId=${exam.examId}"
                                           class="btn btn-gradient w-100" onclick="return confirm('Are you sure you want to start this exam? The timer will begin immediately.')">
                                            <i class="bi bi-play-circle me-1"></i>Start Exam
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="text-center py-5"><i class="bi bi-inbox text-muted" style="font-size:3rem;"></i><p class="text-muted mt-2">No exams available at the moment.</p></div>
        </c:otherwise>
    </c:choose>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
