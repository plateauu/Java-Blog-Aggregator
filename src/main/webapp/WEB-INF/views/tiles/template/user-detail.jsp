
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layouts/taglib.jsp" %>

<h1>${user.name}</h1>

<jstl:forEach items="${user.blogs}" var="blog">

    <h1>${blog.name}</h1>
    <p>${blog.url}</p>

    <table>
        <thead>
        <th>Title</th>
        <th>Link</th>
        </thead>
        <tbody>
        <jstl:forEach items="${blog.items}" var="item">
            <tr>
                <td>${item.title}</td>
                <td>${item.link}</td>
            </tr>
        </jstl:forEach>
        </tbody>

    </table>

</jstl:forEach>