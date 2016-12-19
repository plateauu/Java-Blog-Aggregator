<%--
  Created by IntelliJ IDEA.
  User: plateauu
  Date: 11/16/16
  Time: 11:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layouts/taglib.jsp" %>

<table class="table table-bordered table-hover table-striped">
    <thead>
    <tr>
        <th>User List</th>
        <th>Operations</th>
    </tr>

    </thead>
    <tbody>
    <jstl:forEach items="${users}" var="user">
        <tr>
            <td>
                <a href="<spring:url value="/users/${user.id}.html"/> ">
                        ${user.name}
                </a>
            </td>
            <td>
                <a href="<spring:url value="/users/remove/${user.id}.html"/>" class="btn btn-danger btn-xs">Remove</a>
            </td>
        </tr>

    </jstl:forEach>

    </tbody>
</table>
