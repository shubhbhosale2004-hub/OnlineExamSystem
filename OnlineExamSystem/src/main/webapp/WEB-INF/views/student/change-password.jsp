<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="Change Password"/></jsp:include>
<div class="container py-4">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h3 class="text-white mb-4"><i class="bi bi-key me-2"></i>Change Password</h3>
            <c:if test="${success != null}"><div class="alert alert-success"><c:out value="${success}"/></div></c:if>
            <c:if test="${error != null}"><div class="alert alert-danger"><c:out value="${error}"/></div></c:if>
            <div class="card glass-card">
                <div class="card-body p-4">
                    <form action="${pageContext.request.contextPath}/student/change-password" method="post">
                        <div class="mb-3">
                            <label class="form-label text-light">Current Password</label>
                            <input type="password" class="form-control" name="currentPassword" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label text-light">New Password</label>
                            <input type="password" class="form-control" name="newPassword" minlength="6" required>
                            <div class="form-text text-muted">Minimum 6 characters</div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label text-light">Confirm New Password</label>
                            <input type="password" class="form-control" name="confirmPassword" required>
                        </div>
                        <button type="submit" class="btn btn-gradient"><i class="bi bi-check-lg me-1"></i>Change Password</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
