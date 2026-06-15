<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="Manage Students"/></jsp:include>
<div class="container-fluid py-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3 class="text-white"><i class="bi bi-people me-2"></i>Manage Students</h3>
        <a href="${pageContext.request.contextPath}/admin/students?action=add" class="btn btn-gradient"><i class="bi bi-plus-lg me-1"></i>Add Student</a>
    </div>
    <c:if test="${msg == 'added'}"><div class="alert alert-success">Student added successfully.</div></c:if>
    <c:if test="${msg == 'updated'}"><div class="alert alert-success">Student updated successfully.</div></c:if>
    <c:if test="${msg == 'deleted'}"><div class="alert alert-warning">Student deleted.</div></c:if>
    <div class="card glass-card"><div class="card-body p-0"><div class="table-responsive">
        <table class="table table-dark table-hover mb-0">
            <thead><tr><th>#</th><th>Username</th><th>Full Name</th><th>Email</th><th>Phone</th><th>Gender</th><th>Status</th><th>Actions</th></tr></thead>
            <tbody>
                <c:forEach items="${students}" var="s" varStatus="idx"><tr>
                    <td>${idx.index+1}</td><td><c:out value="${s.username}"/></td><td><c:out value="${s.fullName}"/></td>
                    <td><c:out value="${s.email}"/></td><td><c:out value="${s.phone}"/></td><td><c:out value="${s.gender}"/></td>
                    <td><span class="badge ${s.isActive?'bg-success':'bg-secondary'}">${s.isActive?'Active':'Inactive'}</span></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/students?action=edit&id=${s.studentId}" class="btn btn-sm btn-outline-info"><i class="bi bi-pencil"></i></a>
                        <a href="${pageContext.request.contextPath}/admin/students?action=delete&id=${s.studentId}" class="btn btn-sm btn-outline-danger" onclick="return confirm('Delete this student?')"><i class="bi bi-trash"></i></a>
                    </td>
                </tr></c:forEach>
            </tbody>
        </table>
    </div></div></div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
