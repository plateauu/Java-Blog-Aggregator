<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layouts/taglib.jsp" %>


<h1>${user.name}</h1>

<br/><br/>

<script type="text/javascript">

    $(document).ready(function () {
        $('.nav-tabs a:first').tab('show') // Select first tab
        $('.triggerRemove').click(function (e) {
            e.preventDefault();
            $("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
            $("#modalRemove").modal();
        })
    })
    ;

</script>

<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <jstl:forEach items="${user.blogs}" var="blog">
            <li><a href="#blog_${blog.id}" role="tab"
                   data-toggle="tab">${blog.name}</a></li>
        </jstl:forEach>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <jstl:forEach items="${user.blogs}" var="blog">
            <div role="tabpanel" class="tab-pane active" id="blog_${blog.id}">
                <h1><jstl:out value="${blog.name}"/></h1>

                <a href="<spring:url value="/blog/remove/${blog.id}.html"/>" class="btn btn-danger triggerRemove">Delete
                    blog</a>
                <br/><br/>
                <p><jstl:out value="${blog.url}"/></p>

                <table class="table table-bordered table-hover table-striped">
                    <thead>
                    <th>Title</th>
                    <th>Link</th>
                    </thead>
                    <tbody>
                    <jstl:forEach items="${blog.items}" var="item">
                        <tr>
                            <td><jstl:out value="${item.title}"/></td>
                            <td><jstl:out value="${item.link}"/></td>
                        </tr>
                    </jstl:forEach>
                    </tbody>

                </table>


            </div>
        </jstl:forEach>
    </div>

</div>


<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Delete blog?</h4>
            </div>
            <div class="modal-body">
                Really want to delete?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <a href="" class="btn btn-danger removeBtn">Remove</a>
            </div>
        </div>
    </div>
</div>