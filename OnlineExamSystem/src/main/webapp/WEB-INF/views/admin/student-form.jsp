<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="${student != null ? 'Edit Student' : 'Add Student'}"/></jsp:include>
<div class="container py-4"><div class="row justify-content-center"><div class="col-md-8">
    <h3 class="text-white mb-4"><i class="bi bi-person-plus me-2"></i>${student != null ? 'Edit Student' : 'Add New Student'}</h3>
    <div class="card glass-card"><div class="card-body p-4">
        <form action="${pageContext.request.contextPath}/admin/students" method="post">
            <input type="hidden" name="action" value="${student != null ? 'edit' : 'add'}">
            <c:if test="${student != null}"><input type="hidden" name="studentId" value="${student.studentId}"></c:if>
            <div class="row g-3">
                <div class="col-md-6"><label class="form-label text-light">Username</label>
                    <input type="text" class="form-control" name="username" value="<c:out value='${student.username}'/>" ${student != null ? 'readonly' : 'required'}></div>
                <c:if test="${student == null}">
                    <div class="col-md-6"><label class="form-label text-light">Password</label>
                        <input type="password" class="form-control" name="password" required minlength="6"></div>
                </c:if>
                <div class="col-md-6"><label class="form-label text-light">Full Name</label>
                    <input type="text" class="form-control" name="fullName" value="<c:out value='${student.fullName}'/>" required></div>
                <div class="col-md-6"><label class="form-label text-light">Email</label>
                    <input type="email" class="form-control" name="email" value="<c:out value='${student.email}'/>" required></div>
                <div class="col-md-6"><label class="form-label text-light">Phone</label>
                    <input type="text" class="form-control" name="phone" value="<c:out value='${student.phone}'/>"></div>
                <div class="col-md-6"><label class="form-label text-light">Gender</label>
                    <select class="form-select" name="gender"><option value="">Select</option>
                        <option value="Male" ${student.gender=='Male'?'selected':''}>Male</option>
                        <option value="Female" ${student.gender=='Female'?'selected':''}>Female</option>
                        <option value="Other" ${student.gender=='Other'?'selected':''}>Other</option></select></div>
                <div class="col-md-6"><label class="form-label text-light">Date of Birth</label>
                    <input type="date" class="form-control" name="dob" value="${student.dob}"></div>
                <div class="col-md-6"><div class="form-check mt-4"><input type="checkbox" class="form-check-input" name="isActive" ${student==null||student.isActive?'checked':''}><label class="form-check-label text-light">Active</label></div></div>
                <div class="col-12"><label class="form-label text-light">Address</label>
                    <textarea class="form-control" name="address" rows="2"><c:out value="${student.address}"/></textarea></div>
                <div class="col-12">
                    <button type="submit" class="btn btn-gradient"><i class="bi bi-check-lg me-1"></i>${student != null ? 'Update' : 'Add'} Student</button>
                    <a href="${pageContext.request.contextPath}/admin/students" class="btn btn-outline-light ms-2">Cancel</a></div>
            </div>
        </form>
    </div></div>
</div></div></div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
