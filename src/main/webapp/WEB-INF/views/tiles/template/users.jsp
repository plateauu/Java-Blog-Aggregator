<%--
  Created by IntelliJ IDEA.
  User: plateauu
  Date: 11/16/16
  Time: 11:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table table-bordered table-hover table-striped">
    <thead>
    <tr>
        <th>
            User List
        </th>
    </tr>

    </thead>
    <tbody>
    <jstl:forEach items="${users}" var="user">
        <tr>
            <td>
                    ${user.name}
            </td>
        </tr>

    </jstl:forEach>

    </tbody>
</table>
