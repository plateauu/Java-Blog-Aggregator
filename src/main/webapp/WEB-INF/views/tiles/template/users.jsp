<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: plateauu
  Date: 11/16/16
  Time: 11:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layouts/taglib.jsp" %>


<script type="text/javascript">

    $(document).ready(function () {
        $('.triggerRemove').click(function (e) {
            e.preventDefault();
            $("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
            $("#modalRemove").modal();
        })
    })
    ;

</script>

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
                    <jstl:out value="${user.name}"/>
                </a>
            </td>
            <td>
                <a href="<spring:url value="/users/remove/${user.id}.html"/>" class="btn btn-danger btn-xs triggerRemove">Remove</a>
            </td>
        </tr>

    </jstl:forEach>

    </tbody>
</table>

<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Delete user?</h4>
            </div>
            <div class="modal-body">
                Really want to remove user?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <a href="" class="btn btn-danger removeBtn">Remove</a>
            </div>
        </div>
    </div>
</div>