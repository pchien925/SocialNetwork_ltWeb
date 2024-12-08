<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!-- Hiển thông báo -->
<c:if test="${message != null}">
	<i>${message}</i>
</c:if>
<!-- Hêt thông báo -->
<form action="/searchpaginated">
	<input type="text" name="name" id="name"
		placeholder="Nhập từ khóa để tìm" />
	<button>Search</button>
</form>
<c:if test="${!userPage.hasContent()}">
No user
</c:if>
<c:if test="${userPage.hasContent()}">
	<table border="1" >
		<tr>
			<th>STT</th>
			<th>Avatar</th>
			<th>User name</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${userPage.content}" var="user" varStatus="STT">
			<tr>
				<td>${STT.index+1 }</td>
				<c:if test="${user.avatar.substring(0,5) == 'https' }">
				<c:url value="${user.avatar}" var="imgUrl"></c:url>
			</c:if>
			<c:if test="${user.avatar.substring(0,5) != 'https' }">
				<c:url value="/image?fname=${user.avatar}" var="imgUrl"></c:url>
			</c:if>
			<c:if test="${user.avatar == Null }">
				<c:url value="https://as1.ftcdn.net/v2/jpg/03/53/11/00/1000_F_353110097_nbpmfn9iHlxef4EDIhXB1tdTD0lcWhG9.jpg"
				 var="imgUrl"></c:url>
			</c:if>
			<td><img height="150" width="200" src="${imgUrl}" /></td>
				<td>${user.firstName}  ${user.lastName}</td>
				<td><a href="/admin/categories/edit/${user.id}">add friend</a> <a
					href="/admin/categories/delete/${user.id}">follow</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<form action="">
	Page size: <select name="size" id="size" onchange="this.form.submit()">
		<option ${userPage.size == 3 ? 'selected':''} value="3">3</option>
		<option ${userPage.size == 5 ? 'selected':''} value="5">5</option>
		<option ${userPage.size == 10 ? 'selected':''} value="10">10</option>
		<option ${userPage.size == 15 ? 'selected':''} value="15">15</option>
		<option ${userPage.size == 20 ? 'selected':''} value="20">20</option>
	</select>
</form>
<c:if test="${userPage.totalPages > 0}">
	<ul>
		<c:forEach items="${pageNumbers}" var="pageNumber">
			<c:if test="${userPage.totalPages > 1}">
				<li
					class="${pageNumber == userPage.number + 1 ? 'page-item active' : 'page-item'}">
					<a
					href="<c:url value='/searchpaginated?name=${name}&size=${userPage.size}&page=${pageNumber}'/>">${pageNumber}</a>
				</li>
			</c:if>
		</c:forEach>
	</ul>
</c:if>