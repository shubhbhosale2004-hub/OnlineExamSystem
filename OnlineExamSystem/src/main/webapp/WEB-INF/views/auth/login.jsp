<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="Login - ExamPortal"/></jsp:include>

<div class="login-wrapper d-flex align-items-center justify-content-center" style="min-height:85vh;">
    <div class="card glass-card login-card animate-fadeIn" style="max-width:450px;width:100%;">
        <div class="card-body p-4 p-md-5">
            <div class="text-center mb-4">
                <i class="bi bi-mortarboard-fill text-gradient" style="font-size:3rem;"></i>
                <h3 class="text-white mt-2 fw-bold">Welcome Back</h3>
                <p class="text-muted">Sign in to your ExamPortal account</p>
            </div>

            <c:if test="${error != null}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <i class="bi bi-exclamation-circle me-1"></i><c:out value="${error}"/>
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>
            <c:if test="${param.msg == 'loggedout'}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <i class="bi bi-check-circle me-1"></i>You have been logged out successfully.
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>

            <!-- Role Tabs -->
            <ul class="nav nav-pills nav-fill mb-4" id="loginTabs" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link ${selectedRole != 'admin' ? 'active' : ''}" id="student-tab" data-bs-toggle="pill"
                            data-bs-target="#studentLogin" type="button" onclick="setRole('student')">
                        <i class="bi bi-person-badge me-1"></i>Student
                    </button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link ${selectedRole == 'admin' ? 'active' : ''}" id="admin-tab" data-bs-toggle="pill"
                            data-bs-target="#adminLogin" type="button" onclick="setRole('admin')">
                        <i class="bi bi-shield-lock me-1"></i>Admin
                    </button>
                </li>
            </ul>

            <form action="${pageContext.request.contextPath}/login" method="post" class="needs-validation" novalidate>
                <input type="hidden" name="role" id="roleField" value="${selectedRole != null ? selectedRole : 'student'}">
                <div class="mb-3">
                    <label for="username" class="form-label text-light">Username</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-person"></i></span>
                        <input type="text" class="form-control" id="username" name="username" placeholder="Enter username" required>
                    </div>
                    <div class="invalid-feedback">Please enter your username.</div>
                </div>
                <div class="mb-4">
                    <label for="password" class="form-label text-light">Password</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-lock"></i></span>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" required>
                    </div>
                    <div class="invalid-feedback">Please enter your password.</div>
                </div>
                <button type="submit" class="btn btn-gradient w-100 py-2 fw-semibold">
                    <i class="bi bi-box-arrow-in-right me-1"></i>Sign In
                </button>
            </form>
        </div>
    </div>
</div>
<script>
function setRole(role) { document.getElementById('roleField').value = role; }
// Bootstrap form validation
(function(){
    'use strict';
    document.querySelectorAll('.needs-validation').forEach(function(form){
        form.addEventListener('submit', function(e){
            if(!form.checkValidity()){e.preventDefault();e.stopPropagation();}
            form.classList.add('was-validated');
        });
    });
})();
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
