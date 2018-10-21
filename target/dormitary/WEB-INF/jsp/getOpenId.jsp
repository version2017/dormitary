<%--
  Created by IntelliJ IDEA.
  User: R
  Date: 2018/9/27
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.merit.constant.Constant"%>
<%@ page import="com.merit.constant.Constant" %>
<!-- 引入jstl -->
<%@include file="common/tag.jsp"%>
<%@include file="inc/constants.inc"%>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <title>获取用户OpenId</title>
        <%@include file="common/commonStyleAndScript.jsp"%>
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/common/jquery-weui.css"/>
        <script type="application/javascript">
            //获取地址栏微信接口带过来的code数据
            var code = common.getParamsOfUrl("code");
            var url = "<%=Constant.SERVER_DOMAIN_PORT%>/dormitary/weChat/postOpenid";
            $.post(url, {"code" : code}, function (result) {
                if(result && result.success){
                    window.location.href = projectName + "/view/isFirstTimeLogin?wechatId=" + result.data;
                }else{
                    if(result.data && result.data.stateInfor)
                        $.toptip(result.data.stateInfor, "error");
                    if(result.error)
                        $.toast("Error:"+result.error, 1000);
                }
            }, "json");
        </script>
    </head>
    <body>
        <%--此页面的作用:因为调用腾讯接口时间过长。显示加载过程，以免用户以为系统死机--%>
        <div class="weui-loadmore">
            <i class="weui-loading"></i>
            <span class="weui-loadmore__tips">正在加载</span>
        </div>
    </body>
</html>
