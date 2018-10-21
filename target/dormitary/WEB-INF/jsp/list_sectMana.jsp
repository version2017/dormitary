<%--
  Created by IntelliJ IDEA.
  User: R
  Date: 2018/8/10
  Time: 10:05
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
        <title>部门宿舍列表</title>
        <%@include file="common/commonStyleAndScript.jsp"%>
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/mui.min.css"/>
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/common/jquery-weui.css"/>
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/list_sectMana.css"/>
    </head>
    <body>
        <div class="dormitaryList">
            <div class="mui-table-view-cell dormitaryListDiv">
                <div id="dormitaryListHead">
                    <span class="headSpan">——您的部门宿舍——</span>
                </div>
            </div>
            <ul class="mui-table-view" id="dormitaryListUl">
                <c:forEach var="dormitary" items="${dormitaries}">
                    <li class="mui-table-view-cell mui-media" id="${dormitary.DORM_ID}">
                        <a href="javascript:;">
                            <img class="mui-media-object mui-pull-left dormitaryIcon" src="${PROJECT_NAME}/resource/image/dormitary.jpg"/>
                            <div class="mui-media-body dormitaryInfo">
                                <p class="dormitaryName">${dormitary.DORM_NAME}</p>
                                <p>该宿舍共有${dormitary.BED_NUM}个床位，已入住${dormitary.OCCU_NUM}人</p>
                                <span>宿舍管理员：${dormitary.NAME}</span>
                                <span>
                                    <img src="${PROJECT_NAME}/resource/image/phone.png" class="phoneIcon" />
                                    <span>${dormitary.PHON_NUM}</span>
                                </span>
                                <p>所属项目：${dormitary.PROJ_NAME}</p>
                            </div>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>
