<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"><jsp:param name="pageTitle" value="Manage Subjects"/></jsp:include>
<div class="container-fluid py-4">
    <div class="d-flex justify-content-between mb-4"><h3 class="text-white"><i class="bi bi-book me-2"></i>Manage Subjects</h3>
        <button class="btn btn-gradient" data-bs-toggle="modal" data-bs-target="#subjectModal" onclick="clearForm()"><i class="bi bi-plus-lg me-1"></i>Add Subject</button></div>
    <c:if test="${msg!=null}"><div class="alert alert-success">Operation completed successfully.</div></c:if>
    <div class="card glass-card"><div class="card-body p-0"><div class="table-responsive">
        <table class="table table-dark table-hover mb-0"><thead><tr><th>#</th><th>Code</th><th>Name</th><th>Description</th><th>Status</th><th>Actions</th></tr></thead>
        <tbody><c:forEach items="${subjects}" var="s" varStatus="idx"><tr><td>${idx.index+1}</td><td><c:out value="${s.subjectCode}"/></td><td><c:out value="${s.subjectName}"/></td>
            <td><c:out value="${s.description}"/></td><td><span class="badge ${s.isActive?'bg-success':'bg-secondary'}">${s.isActive?'Active':'Inactive'}</span></td>
            <td><button class="btn btn-sm btn-outline-info" onclick="editSubject(${s.subjectId},'<c:out value="${s.subjectName}"/>','<c:out value="${s.subjectCode}"/>','<c:out value="${s.description}"/>')"><i class="bi bi-pencil"></i></button>
                <a href="${pageContext.request.contextPath}/admin/subjects?action=delete&id=${s.subjectId}" class="btn btn-sm btn-outline-danger" onclick="return confirm('Delete?')"><i class="bi bi-trash"></i></a></td></tr></c:forEach></tbody></table>
    </div></div></div>
</div>
<!-- Modal -->
<div class="modal fade" id="subjectModal"><div class="modal-dialog"><div class="modal-content bg-dark">
    <form action="${pageContext.request.contextPath}/admin/subjects" method="post">
        <input type="hidden" name="action" id="modalAction" value="add"><input type="hidden" name="subjectId" id="modalSubjectId">
        <div class="modal-header border-secondary"><h5 class="modal-title text-white" id="modalTitle">Add Subject</h5><button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button></div>
        <div class="modal-body">
            <div class="mb-3"><label class="form-label text-light">Subject Name</label><input type="text" class="form-control" name="subjectName" id="modalName" required></div>
            <div class="mb-3"><label class="form-label text-light">Subject Code</label><input type="text" class="form-control" name="subjectCode" id="modalCode" required></div>
            <div class="mb-3"><label class="form-label text-light">Description</label><textarea class="form-control" name="description" id="modalDesc" rows="3"></textarea></div>
        </div>
        <div class="modal-footer border-secondary"><button type="submit" class="btn btn-gradient">Save</button></div>
    </form>
</div></div></div>
<script>
function clearForm(){document.getElementById('modalAction').value='add';document.getElementById('modalSubjectId').value='';document.getElementById('modalName').value='';document.getElementById('modalCode').value='';document.getElementById('modalDesc').value='';document.getElementById('modalTitle').textContent='Add Subject';}
function editSubject(id,name,code,desc){document.getElementById('modalAction').value='edit';document.getElementById('modalSubjectId').value=id;document.getElementById('modalName').value=name;document.getElementById('modalCode').value=code;document.getElementById('modalDesc').value=desc;document.getElementById('modalTitle').textContent='Edit Subject';new bootstrap.Modal(document.getElementById('subjectModal')).show();}
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
