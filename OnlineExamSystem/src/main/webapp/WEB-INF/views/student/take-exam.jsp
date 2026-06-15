<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="Exam In Progress"/></jsp:include>

<div class="container-fluid py-3">
    <!-- Exam Header Bar -->
    <div class="card glass-card mb-3">
        <div class="card-body py-2 d-flex justify-content-between align-items-center flex-wrap">
            <h5 class="text-white mb-0"><i class="bi bi-pencil-square me-2"></i><c:out value="${exam.examTitle}"/></h5>
            <div class="d-flex align-items-center gap-3">
                <span class="text-muted" id="questionCounter">Question 1 of ${questions.size()}</span>
                <div class="timer-display" id="examTimer">00:00</div>
            </div>
        </div>
    </div>

    <div class="row g-3">
        <!-- Question Navigation Sidebar -->
        <div class="col-md-2">
            <div class="card glass-card">
                <div class="card-header"><h6 class="text-white mb-0">Questions</h6></div>
                <div class="card-body question-nav-grid">
                    <c:forEach items="${questions}" var="q" varStatus="idx">
                        <button type="button" class="btn btn-outline-secondary question-nav-btn"
                                id="navBtn_${idx.index}" onclick="jumpToQuestion(${idx.index})">
                            ${idx.index + 1}
                        </button>
                    </c:forEach>
                </div>
            </div>
        </div>

        <!-- Question Content Area -->
        <div class="col-md-10">
            <form id="examForm" action="${pageContext.request.contextPath}/student/submit-exam" method="post">
                <input type="hidden" name="attemptId" value="${attemptId}">
                <input type="hidden" name="examId" value="${exam.examId}">
                <input type="hidden" name="autoSubmit" id="autoSubmitField" value="false">

                <c:forEach items="${questions}" var="q" varStatus="idx">
                    <div class="question-panel card glass-card mb-3" id="questionPanel_${idx.index}" style="${idx.index > 0 ? 'display:none' : ''}">
                        <div class="card-body p-4">
                            <h5 class="text-white mb-3">
                                <span class="badge bg-primary me-2">Q${idx.index + 1}</span>
                                <c:out value="${q.questionText}"/>
                            </h5>
                            <div class="options-grid">
                                <c:forEach items="${['A','B','C','D']}" var="opt">
                                    <label class="option-card" id="optLabel_${q.questionId}_${opt}">
                                        <input type="radio" name="q_${q.questionId}" value="${opt}"
                                               class="d-none" onchange="markAnswered(${q.questionId}, '${opt}', ${idx.index})">
                                        <span class="option-letter">${opt}</span>
                                        <span class="option-text">
                                            <c:choose>
                                                <c:when test="${opt == 'A'}"><c:out value="${q.optionA}"/></c:when>
                                                <c:when test="${opt == 'B'}"><c:out value="${q.optionB}"/></c:when>
                                                <c:when test="${opt == 'C'}"><c:out value="${q.optionC}"/></c:when>
                                                <c:when test="${opt == 'D'}"><c:out value="${q.optionD}"/></c:when>
                                            </c:choose>
                                        </span>
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <!-- Navigation Buttons -->
                <div class="d-flex justify-content-between mt-3">
                    <button type="button" class="btn btn-outline-light" id="prevBtn" onclick="navigatePrev()" disabled>
                        <i class="bi bi-arrow-left me-1"></i>Previous
                    </button>
                    <button type="button" class="btn btn-outline-light" id="nextBtn" onclick="navigateNext()">
                        Next<i class="bi bi-arrow-right ms-1"></i>
                    </button>
                    <button type="button" class="btn btn-danger" onclick="confirmSubmit()">
                        <i class="bi bi-send me-1"></i>Submit Exam
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/exam-timer.js"></script>
<script>
    var totalQuestions = ${questions.size()};
    var durationMinutes = ${durationMinutes};
    initExamTimer(durationMinutes, 'examForm');
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
