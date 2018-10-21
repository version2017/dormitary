<%--
  Created by IntelliJ IDEA.
  User: R
  Date: 2018/7/30
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 引入jstl -->
<%@include file="common/tag.jsp"%>
<%@include file="inc/constants.inc"%>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <title>宿舍列表选择</title>
        <%@include file="common/commonStyleAndScript.jsp"%>
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/listChoose.css"/>
    </head>
    <body>
        <div class="listChoose">
            <div class="mui-table-view-cell listChooseDiv">
                <div class="" id="dormitaryListHead">
                    <span class="headSpan">请选择宿舍列表</span>
                </div>
            </div>
            <a href="${PROJECT_NAME}/view/list_sectMana" class="weui-btn weui-btn_primary chooseBut" id="sectorDormBut">部门宿舍</a>
            <a href="${PROJECT_NAME}/view/list_projMana" class="weui-btn weui-btn_primary chooseBut">项目宿舍</a>
            <a href="${PROJECT_NAME}/view/dormitaryList" class="weui-btn weui-btn_primary chooseBut">全部宿舍</a>
            <c:if test="${myDormId != null}">
                <a href="${PROJECT_NAME}/view/dormitaryDetail/${myDormId}" class="weui-btn weui-btn_primary chooseBut">我的宿舍</a>
            </c:if>
        </div>
        <script src="${PROJECT_NAME}/resource/js/listChoose.js" type="text/javascript" charset="utf-8"></script>
    </body>
</html>
