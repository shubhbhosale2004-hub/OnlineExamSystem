<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="My Profile"/></jsp:include>
<div class="container py-4">
    <h3 class="text-white mb-4"><i class="bi bi-person-circle me-2"></i>My Profile</h3>
    <c:if test="${success != null}"><div class="alert alert-success"><c:out value="${success}"/></div></c:if>
    <c:if test="${error != null}"><div class="alert alert-danger"><c:out value="${error}"/></div></c:if>
    <div class="card glass-card">
        <div class="card-body p-4">
            <form action="${pageContext.request.contextPath}/student/profile" method="post">
                <div class="row g-3">
                    <div class="col-md-6">
                        <label class="form-label text-light">Username</label>
                        <input type="text" class="form-control" value="<c:out value='${student.username}'/>" readonly>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label text-light">Full Name</label>
                        <input type="text" class="form-control" name="fullName" value="<c:out value='${student.fullName}'/>" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label text-light">Email</label>
                        <input type="email" class="form-control" name="email" value="<c:out value='${student.email}'/>" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label text-light">Phone</label>
                        <input type="text" class="form-control" name="phone" value="<c:out value='${student.phone}'/>">
                    </div>
                    <div class="col-md-6">
                        <label class="form-label text-light">Gender</label>
                        <select class="form-select" name="gender">
                            <option value="">Select</option>
                            <option value="Male" ${student.gender == 'Male' ? 'selected' : ''}>Male</option>
                            <option value="Female" ${student.gender == 'Female' ? 'selected' : ''}>Female</option>
                            <option value="Other" ${student.gender == 'Other' ? 'selected' : ''}>Other</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label text-light">Date of Birth</label>
                        <input type="date" class="form-control" name="dob" value="${student.dob}">
                    </div>
                    <div class="col-12">
                        <label class="form-label text-light">Address</label>
                        <textarea class="form-control" name="address" rows="3"><c:out value="${student.address}"/></textarea>
                    </div>
                    <div class="col-12">
                        <button type="submit" class="btn btn-gradient"><i class="bi bi-check-circle me-1"></i>Update Profile</button>
                        <a href="${pageContext.request.contextPath}/student/change-password" class="btn btn-outline-light ms-2"><i class="bi bi-key me-1"></i>Change Password</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
