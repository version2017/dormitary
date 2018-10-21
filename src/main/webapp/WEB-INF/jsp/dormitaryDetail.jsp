<%--
  Created by IntelliJ IDEA.
  User: R
  Date: 2018/6/15
  Time: 9:11
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
        <title>宿舍详情</title>
        <%@include file="common/commonStyleAndScript.jsp"%>
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/mui.min.css"/>
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/mui.picker.min.css"/>
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/common/jquery-weui.css"/>
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/dormitaryDetail.css"/>
        <script type="text/javascript">
            var isCheckIn = ${isCheckIn};
            var dormitaryId = ${dormitary.DORM_ID};
            var dormitaryName = "${dormitary.DORM_NAME}";
            var dormitaryAddress = "${dormitary.ADDRESS}";
            var province = "${dormitary.PROVINCE}";
            var isManagerOfDormitary = ${isManagerOfDormitary};
        </script>
    </head>
    <body>
        <div class="headDiv">
            <div class="headDiv" id="headDiv1">
                <span class="mui-icon mui-icon-back" id="backIcon"></span>
                <a href="#">${dormitary.DORM_NAME}</a>
            </div>
        </div>
        <div class="detail">
            <div class="detailMain weui-cells weui-cells_form">
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label" id="addressLabel">地址</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input cellInp" type="text" id="addressInput" value="${dormitary.ADDRESS}" disabled="disabled">
                    </div>
                    <div class="weui-cell__ft">
                        <i class="weui-icon-info-circle" id="addressDetail"></i>
                    </div>
                </div>
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">所属项目</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input cellInp" type="text" id="projInp" value="${dormitary.PROJ_NAME}">
                    </div>
                </div>
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">床位数</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input cellInp" type="number" id="bedNumInput" value="${dormitary.BED_NUM}" disabled="disabled">
                    </div>
                </div>
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">入住人数</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input cellInp" type="number" id="occuNumInput" value="${dormitary.OCCU_NUM}" disabled="disabled">
                    </div>
                </div>
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">已入住</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input cellInp" type="text" value="${names}" disabled="disabled">
                    </div>
                    <div class="weui-cell__ft">
                        <i class="weui-icon-info-circle" id="alreadyCheckIn"></i>
                    </div>
                </div>
            </div>
            <div id="checkInDetailInfor">
                <c:forEach var="person" items="${persons}">
                    <p class="detailInforP">
                        <span class="persNameSpan">${person.name}</span>
                        <span class="phoneNumSpan">-${person.phonNum}</span>
                        <span class="xSpan">&nbsp;&nbsp;&nbsp;&nbsp;
                            <span class="kickOutBut" id="${person.emplId}">X</span>
                        </span>
                    </p>
                </c:forEach>
            </div>
            <a href="javascript:;" class="weui-btn weui-btn_primary subBut-family" id="saveBut">保存</a>
            <a href="javascript:;" class="weui-btn weui-btn_warn subBut-family" id="leave">离开</a>
            <a href="javascript:;" class="weui-btn weui-btn_primary subBut-family" id="checkIn">入住</a>
            <a href="javascript:;" class="weui-btn weui-btn_primary subBut-family" id="inviteBut">邀请</a>
            <a href="javascript:;" class="weui-btn weui-btn_primary subBut-family" id="createQRCode">生成二维码</a>
        </div>
        <script src="${PROJECT_NAME}/resource/js/mui.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="${PROJECT_NAME}/resource/js/mui.picker.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="${PROJECT_NAME}/resource/js/dormitaryDetail.js" type="text/javascript" charset="utf-8"></script>
    </body>
</html>
