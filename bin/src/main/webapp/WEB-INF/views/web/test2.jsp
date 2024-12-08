<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<a href="${pageContext.request.contextPath}/home ">Back Home</a>

<form class="d-inline form-inline w-100 px-4" action="/searchpaginated"
	method="post">
	<div class="input-group">
		<input type="text" class="form-control search-input"
			placeholder="Search for people, companies, events and more..."
			aria-label="Search" aria-describedby="search-addon">
		<div class="input-group-append">
			<button class="btn search-button" type="submit">
				submit <i class='bx bx-search'></i>
			</button>
		</div>
	</div>
</form>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Images</th>
		<th>User name</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listuser}" var="user" varStatus="STT">
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
			<td>
				<form class="d-inline form-inline w-100 px-4"
					action="/search-user" method="post">
					<button class="btn search-button" type="submit">
						submit <i class='bx bx-search'></i>
					</button>
					</div>
					</div>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>