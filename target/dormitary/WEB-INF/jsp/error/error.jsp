<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 引入jstl -->
<%@include file="../common/tag.jsp"%>
<%@include file="../inc/constants.inc"%>
<html>
    <body>
        <h2>系统内部出错！</h2>
        <h4>${errorMessage}</h4>
        <p>如上述操作无效，请联系开发者${maintainerName}，帮助完善系统</p>
        <p>电话：${maintainerPhone}</p>
        <p>邮箱：${maintaineEmail}</p>
    </body>
</html>
