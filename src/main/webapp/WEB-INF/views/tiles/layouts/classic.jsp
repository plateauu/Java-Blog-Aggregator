<%--
  Created by IntelliJ IDEA.
  User: plateauu
  Date: 11/16/16
  Time: 11:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head>
    <title><tiles:getAsString name="title"/></title>
</head>
<body>
    <tiles:insertAttribute name="body"/>
<center>
    <tiles:insertAttribute name="footer"/>
</center>

</body>


</html>
