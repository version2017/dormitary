<%--
  Created by IntelliJ IDEA.
  User: R
  Date: 2018/8/8
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.merit.constant.Constant"%>
<!-- 引入jstl -->
<%@include file="common/tag.jsp"%>
<%@include file="inc/constants.inc"%>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <title>宿舍首页</title>
        <%@include file="common/commonStyleAndScript.jsp"%>
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/common/jquery-weui.css"/>
        <script type="application/javascript">
            /*调用网页授权接口：为了能在微信中打开系统的网页而不出现警告*/
            var url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=<%=Constant.APP_ID%>&redirect_uri=<%=Constant.INDEX_URL_ENCODED%>&response_type=code&scope=snsapi_base&agentid=<%=Constant.AGENT_ID%>#wechat_redirect";
            window.location.href = url;
        </script>
    </head>
    <body>

    </body>
</html>
