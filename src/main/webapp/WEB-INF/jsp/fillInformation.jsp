<%--
  Created by IntelliJ IDEA.
  User: R
  Date: 2018/6/15
  Time: 9:06
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
        <title>基本信息完善</title>
        <%@include file="common/commonStyleAndScript.jsp"%>
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/common/jquery-weui.css"/>
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/fillInformation.css"/>
    </head>
    <body>
        <div class="headDiv">
            <div class="headDiv" id="headDiv1"><a href="#">初次登录，请完善基本信息</a></div>
        </div>
        <div class="fill">
            <div class="fillMain weui-cells weui-cells_form">
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input cellInp" type="text" id="fullName" placeholder="请输入姓名" value="${person.name}">
                    </div>
                </div>
                <div class="weui-cell weui-cell_select weui-cell_select-after">
                    <div class="weui-cell__hd"><label class="weui-label">性别</label></div>
                    <div class="weui-cell__bd">
                        <select class="weui-select cellSel" id="sexSel" dir="rtl" name="sex">
                            <option value="1">男</option>
                            <option value="0">女</option>
                        </select>
                    </div>
                </div>
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">员工工号</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input cellInp" type="text" id="emplId" placeholder="请输入员工工号" value="${emplId}">
                    </div>
                </div>
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">手机号</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input cellInp" type="number" id="phoneNum" placeholder="请输入手机号" value="${person.phonNum}">
                    </div>
                </div>
            </div>
            <a href="javascript:;" class="weui-btn weui-btn_primary subBut-family" id="subBut">提交</a>
        </div>
        <script src="${PROJECT_NAME}/resource/js/fillInformation.js" type="text/javascript" charset="utf-8"></script>
    </body>
</html>
