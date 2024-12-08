<%--@elvariable id="profile" type="model.User"--%>
<%--@elvariable id="user" type="model.User"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="container-fluid col-sm-12" style="min-height: 80%;">
    <div class="panel panel-default ">
        <div class="panel-heading">
            <strong>${profile.firstName} ${profile.lastName}</strong>
        </div>
        <table class="table table-hover">
            <tr>
                <td class="col-md-4">
                    First name:
                </td>
                <td class="col-md-8">
                    ${profile.firstName}
                </td>
            <tr>
                <td class="col-md-4">
                    Last name:
                </td>
                <td class="col-md-8">
                    ${profile.lastName}
                </td>
            <tr>
                <td class="col-md-4">
                    Email:
                </td>
                <td class="col-md-8">
                        ${profile.email}
                </td>
            <tr>
            <tr>
                <td class="col-md-4">
                    Phone:
                </td>
                <td class="col-md-8">
                        ${profile.phone}
                </td>
            <tr>
                <td class="col-md-4">
                    Sex:
                </td>
                <td class="col-md-8">
                    ${profile.gender}
                </td>
            <tr>
                <td class="col-md-4">
                    Date of birth:
                </td>
                <td class="col-md-8">
                
                ${profile.dateOfBirth}
                </td>
            <tr>
                <td class="col-md-4">
                    Registered at:
                </td>
                <td class="col-md-8">
                	${profile.createdAt}
                </td>
        </table>
    </div>
</div>