<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="Error"/></jsp:include>
<div class="container py-5 text-center">
    <div class="card glass-card mx-auto" style="max-width:500px;">
        <div class="card-body p-5">
            <i class="bi bi-exclamation-triangle-fill text-warning" style="font-size:3rem;"></i>
            <h3 class="mt-3 text-white">Oops! Something went wrong</h3>
            <p class="text-muted mt-2"><c:out value="${error}" default="The page you are looking for could not be found."/></p>
            <a href="${pageContext.request.contextPath}/" class="btn btn-gradient mt-3"><i class="bi bi-house me-1"></i>Go Home</a>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
