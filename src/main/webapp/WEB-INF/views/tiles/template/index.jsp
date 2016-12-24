<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layouts/taglib.jsp" %>

<h1>Welcome to Java World:</h1>
<table class="table table-bordered table-hover table-striped">
    <thead>
    <th>Date</th>
    <th>Item</th>
    </thead>
    <tbody>
    <jstl:forEach items="${items}" var="item">

        <tr>
            <td><jstl:out value="${item.publishedDate}"/>
                    <br/>
                    <jstl:out value="${item.blog.name}"/>
            </td>
            <td>
                <strong>
                    <a href="<jstl:out value='${item.link}'/>" target="_blank">
                        <jstl:out value="${item.title}"/>
                    </a>
                </strong>
                <br/>
                <jstl:out value="${item.description}"/>

            </td>
        </tr>
    </jstl:forEach>
    </tbody>

</table>