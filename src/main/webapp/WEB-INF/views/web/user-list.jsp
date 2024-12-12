<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="container my-5">
    <!-- Display Message -->
    <c:if test="${message != null}">
        <div class="alert alert-info text-center" role="alert">
            <i>${message}</i>
        </div>
    </c:if>

    <!-- Search Form -->


    <!-- No users message -->
    <c:if test="${!userPage.hasContent()}">
        <div class="alert alert-warning text-center">Không có người dùng nào</div>
    </c:if>

    <!-- User Cards -->
    <c:if test="${userPage.hasContent()}">
        <div class="row">
            <c:forEach items="${userPage.content}" var="user" varStatus="STT">
                <div class="col-md-4 col-lg-3 mb-4">
                    <div class="card shadow-sm">
                        <div class="card-body text-center">
                            <!-- Avatar -->
                            <c:choose>
                                <c:when test="${user.avatar != null && user.avatar.substring(0, 5) == 'https'}">
                                    <img src="${user.avatar}" class="rounded-circle img-fluid" style="max-height: 150px; width: auto;" alt="User Avatar">
                                </c:when>
                                <c:otherwise>
                                    <img src="/assets/images/users/user-10.png" class="rounded-circle img-fluid" style="max-height: 150px; width: auto;" alt="User Avatar">
                                </c:otherwise>
                            </c:choose>

                            <!-- User Name -->
                           <h5 class="mt-3">
                               <a href="/profile/${user.id}" class="text-decoration-none text-dark">${user.firstName} ${user.lastName}</a>
                           </h5>

                            <p class="text-muted">${user.username}</p>

                            <!-- Action Buttons -->
                            <div class="d-flex justify-content-between mt-3">
                                <button class="btn btn-outline-primary btn-sm" onclick="addFriend(${user.id})">
                                    <i class="bi bi-person-plus"></i> Kết bạn
                                </button>
                                <button class="btn btn-outline-info btn-sm" onclick="addFollow(${user.id})">
                                    <i class="bi bi-person-check"></i> Theo dõi
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <!-- Pagination -->
    <c:if test="${userPage.totalPages > 0}">
        <nav class="mt-4">
            <ul class="pagination justify-content-center">
                <c:forEach items="${pageNumbers}" var="pageNumber">
                    <li class="page-item ${pageNumber == userPage.number + 1 ? 'active' : ''}">
                        <a class="page-link" href="<c:url value='/user/searchpaginated?name=${name}&size=${userPage.size}&page=${pageNumber}'/>">${pageNumber}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </c:if>

    <!-- Page Size Selector -->
    <form action="" class="d-flex justify-content-center">
        <label for="size" class="form-label me-2">Kích thước trang:</label>
        <select name="size" id="size" class="form-select w-auto" onchange="this.form.submit()">
            <option ${userPage.size == 3 ? 'selected' : ''} value="3">3</option>
            <option ${userPage.size == 5 ? 'selected' : ''} value="5">5</option>
            <option ${userPage.size == 10 ? 'selected' : ''} value="10">10</option>
            <option ${userPage.size == 15 ? 'selected' : ''} value="15">15</option>
            <option ${userPage.size == 20 ? 'selected' : ''} value="20">20</option>
        </select>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.js"></script>
<script>
    function addFriend(userId) {
        fetch('api/sendFriendRequest', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({ userId: userId })
        })
        .then(response => response.json())
        .then(data => {
            alert(data.status === 'success' ? data.message : 'Lỗi: ' + data.message);
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Có lỗi xảy ra khi thêm bạn.');
        });
    }

    function addFollow(userId) {
        fetch('api/followerships/follow', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({ userId: userId })
        })
        .then(response => response.json())
        .then(data => {
            alert(data.status === 'success' ? data.message : 'Lỗi: ' + data.message);
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Có lỗi xảy ra khi theo dõi.');
        });
    }
</script>
