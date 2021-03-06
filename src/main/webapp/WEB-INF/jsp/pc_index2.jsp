<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: R
  Date: 2018/7/10
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 引入jstl -->
<%@include file="common/tag.jsp"%>
<%@include file="inc/constants.inc"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>管理端首页</title>
        <%@include file="common/commonStyleAndScript.jsp"%>
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/pc_index2.css"/>
    </head>
    <body>
    <div class="headDiv">
        <img id="logo" src="${PROJECT_NAME}/resource/image/logo.png"/>
        <span id="titleSpan">宿舍管理平台</span>
        <div class="rightHeadDiv">
                    <span class="buttonSpan">
                        <img class="headIcons" src="${PROJECT_NAME}/resource/image/person.png"/>
                        <span>欢迎您，管理员</span>
                    </span>
            <span class="buttonSpan" id="alterSpan">
                        <img class="headIcons" id="keyLogo" src="${PROJECT_NAME}/resource/image/key.png"/>
                        <span>修改密码</span>
                    </span>
            <span class="buttonSpan" id="quitSpan">
                        <img class="headIcons" id="logoutLogo" src="${PROJECT_NAME}/resource/image/logout.png"/>
                        <span>退出</span>
                    </span>
        </div>
    </div>
    <div class="mainDiv">
        <div class="mainLeft">
            <div class="mainLeftDiv" id="mainLeftDiv1">
                <span class="glyphicon glyphicon-home iconSpan"></span>
                <div>宿舍管理</div>
            </div>
            <div class="mainLeftDiv mainLeftActive" id="mainLeftDiv2">
                <span class="glyphicon glyphicon-th-list iconSpan"></span>
                <div>部门管理</div>
            </div>
            <div class="mainLeftDiv" id="mainLeftDiv3">
                <span class="glyphicon glyphicon-tasks iconSpan"></span>
                <div>项目管理</div>
            </div>
            <div class="mainLeftDiv" id="mainLeftDiv4">
                <span class="glyphicon glyphicon-user iconSpan"></span>
                <div>用户管理</div>
            </div>
        </div>
        <div class="mainRight" id="mainRight2">
            <div class="conditionDiv">
                <form class="form-horizontal" role="form" id="queryForm">
                    <div class="form-group" id="first-form-group">
                        <label class="col-sm-1 control-label">部门名称</label>
                        <div class="col-sm-3">
                            <input class="form-control" id="sectName" type="text" placeholder="请输入部门名称关键字">
                        </div>
                    </div>
                    <div class="form-group" id="last-form-group">
                        <button type="button" class="btn btn-success" id="queryBut">查询</button>
                        <button type="button" class="btn btn-primary" id="resetBut">重置</button>
                    </div>
                </form>
            </div>
            <div class="buttonDiv">
                <button type="button" class="btn btn-success" id="addSectorBut">
                    <span class="glyphicon glyphicon-plus-sign"></span>
                    <span>新增部门</span>
                </button>
            </div>
            <div class="listDiv">
                <table class="table table-bordered">
                    <caption>部门列表</caption>
                    <thead>
                    <tr>
                        <th>部门名称</th>
                        <th>部门经理</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="sectTable">
                        <C:forEach var="sector" items="${pageInfo.records}">
                            <tr>
                                <td>${sector.SECT_NAME}</td>
                                <td>

                                    <c:choose>
                                        <c:when test="${sector.NAME == undefined}">

                                        </c:when>
                                        <C:otherwise>
                                            ${sector.NAME}
                                        </C:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a class="detailBut" id="${sector.SECT_ID}" href="javascript:;">详情</a>
                                </td>
                            </tr>
                        </C:forEach>
                    </tbody>
                </table>
                <div class="paginationDiv">
                    <ul class="pagination" id="paginationUl">
                        <li class="prePage" id="prePage"><a href="#">&laquo;</a></li>
                        <c:forEach var="i" begin="1" end="${pageInfo.totalPage}" step="1">
                            <c:choose>
                                <c:when test="${i==1}">
                                    <li class="pageLi active"><a href="#">${i}</a></li>
                                </c:when>
                                <C:otherwise>
                                    <li class="pageLi"><a href="#">${i}</a></li>
                                </C:otherwise>
                            </c:choose>
                        </c:forEach>
                        <li class="nextPage" id="nextPage"><a href="#">&raquo;</a></li>
                    </ul>
                </div>
            </div>
            <!-- 部门详情模态框（Modal） -->
            <div class="modal fade" id="sectorDeail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">部门详情</h4>
                        </div>
                        <div class="modal-body form-horizontal" id="modal-bodyDiv">
                            <form id="sectorForm">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">部门名称</label>
                                    <div class="col-sm-7">
                                        <input class="form-control" type="text" id="modal-sectorName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">部门经理</label>
                                    <div class="col-sm-7">
                                        <select class="form-control" id="modal-manager">
                                            <option value="">请选择部门经理</option>
                                        </select>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" id="deleteBut">删除</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" id="updateConfirm">确定</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>
            <!-- 新增部门模态框（Modal） -->
            <div class="modal fade" id="addSectorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">新增部门</h4>
                        </div>
                        <div class="modal-body form-horizontal" id="modal-bodyDiv2">
                            <form id="addSectorForm">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">部门名称</label>
                                    <div class="col-sm-7">
                                        <input class="form-control" type="text" id="modal-sectorName2">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">部门经理</label>
                                    <div class="col-sm-7">
                                        <select class="form-control" id="modal-manager2">
                                            <option value="">请选择部门经理</option>
                                        </select>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" id="addConfirm">确定</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>
        </div>
    </div>
    <!-- 修改密码模态框（Modal） -->
    <div class="modal fade" id="alterPassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">修改密码</h4>
                </div>
                <div class="modal-body form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">原密码</label>
                        <div class="col-sm-7">
                            <input class="form-control" type="password" id="oldPassword">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">新密码</label>
                        <div class="col-sm-7">
                            <input class="form-control" type="password" id="newPassword1">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">确认密码</label>
                        <div class="col-sm-7">
                            <input class="form-control" type="password" id="newPassword2">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="alterConfirm">确定</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

    <script type="text/javascript" src="${PROJECT_NAME}/resource/js/common/bootstrap.js"></script>
    <script type="text/javascript" src="${PROJECT_NAME}/resource/js/pc_index2.js"></script>
    </body>
</html>


