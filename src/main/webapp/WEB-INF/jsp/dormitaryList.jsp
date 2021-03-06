<%--
  Created by IntelliJ IDEA.
  User: R
  Date: 2018/6/15
  Time: 9:17
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
        <title>宿舍列表</title>
        <%@include file="common/commonStyleAndScript.jsp"%>
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/mui.min.css"/>
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/common/jquery-weui.css"/>
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/dormitaryList.css"/>
        <script type="text/javascript">
            var province = "${province}";
        </script>
    </head>
    <body>
        <div class="dormitaryList">
            <div class="mui-table-view-cell dormitaryListDiv">
                <div class="mui-navigate-right" id="dormitaryListHead">
                    <span class="mui-icon mui-icon-back" id="backIcon"></span>
                    <span class="headSpan">——请选择地点——</span>
                </div>
                <select class="sel" name="placeSel" id="placeSelected">
                    <option value="">——请选择地点——</option>
                    <c:forEach var="place" items="${places}">
                        <option value="${place}">${place}</option>
                    </c:forEach>
                </select>
            </div>
            <ul class="mui-table-view" id="dormitaryListUl">
                <%--<li class="mui-table-view-cell mui-media">--%>
                    <%--<a href="javascript:;">--%>
                        <%--<img class="mui-media-object mui-pull-left dormitaryIcon" src="${PROJECT_NAME}/resource/image/dormitary.jpg"/>--%>
                        <%--<div class="mui-media-body dormitaryInfo">--%>
                            <%--<p class="dormitaryName">杭州萧山</p>--%>
                            <%--<p>该宿舍共有4个床位，已入住2人</p>--%>
                            <%--<span>宿舍管理员：吴伟杰</span>--%>
                            <%--<span>--%>
                                <%--<img src="${PROJECT_NAME}/resource/image/phone.png" class="phoneIcon" />--%>
                                <%--<span>17826804928</span>--%>
                            <%--</span>--%>
                            <%--<p>所属项目：航天恒嘉</p>--%>
                        <%--</div>--%>
                    <%--</a>--%>
                <%--</li>--%>
            </ul>
        </div>
        <script src="${PROJECT_NAME}/resource/js/dormitaryList.js" type="text/javascript" charset="utf-8"></script>
    </body>
</html>
