
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layouts/taglib.jsp" %>



<h1>${user.name}</h1>

<form:form commandName="blog" cssClass="form-horizontal">
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  New blog
</button>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">New blog</h4>
            </div>
            <div class="modal-body">

                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">Name:</label>
                    <div class="col-sm-10">
                        <form:input path="name" cssClass="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">URL:</label>
                    <div class="col-sm-10">
                        <form:input path="url" cssClass="form-control"/>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <input type="submit" class="btn btn-primary" value="Save"/>
            </div>
        </div>
    </div>
</div>
</form:form>

<jstl:forEach items="${user.blogs}" var="blog">

    <h1>${blog.name}</h1>
    <p>${blog.url}</p>

    <table class="table table-bordered table-hover table-striped">
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