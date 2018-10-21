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
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/bootstrap-datetimepicker.min.css"/>
        <link rel="stylesheet" type="text/css" href="${PROJECT_NAME}/resource/css/pc_index1.css"/>
        <%--<script type="text/javascript">--%>
            <%--var allProjects = [];--%>
            <%--var projectObj;--%>
            <%--//将Model中传来的projects转换为json数组，由于格式原因（键值对中间是等号）无法直接转换--%>
            <%--<c:forEach var="project" items="${projects}">--%>
                <%--projectObj = new Object();--%>
                <%--projectObj.projId = ${project.projId};--%>
                <%--projectObj.projName = "${project.projName}";--%>
                <%--projectObj.sectId = "${project.sectId}";--%>
                <%--projectObj.projMana = "${project.projMana}";--%>
                <%--allProjects.push(projectObj);--%>
            <%--</c:forEach>--%>
        <%--</script>--%>
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
                <div class="mainLeftDiv mainLeftActive" id="mainLeftDiv1">
                    <span class="glyphicon glyphicon-home iconSpan"></span>
                    <div>宿舍管理</div>
                </div>
                <div class="mainLeftDiv" id="mainLeftDiv2">
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
            <div class="mainRight" id="mainRight1">
                <div class="conditionDiv">
                    <form class="form-horizontal" role="form" id="queryForm">
                        <div class="form-group" id="first-form-group">
                            <label class="col-sm-1 control-label">宿舍名</label>
                            <div class="col-sm-3">
                                <input class="form-control" id="dormitaryName" type="text" placeholder="请输入宿舍名关键字">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">地区</label>
                            <div class=" col-sm-3">
                                <select class="form-control" id="location">
                                    <option value="">请选择地区</option>
                                    <c:forEach var="place" items="${places}">
                                        <option value="${place}">${place}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" id="last-form-group">
                            <button type="button" class="btn btn-success" id="queryBut">查询</button>
                            <button type="button" class="btn btn-primary" id="resetBut">重置</button>
                        </div>
                    </form>
                </div>
                <div class="buttonDiv">
                    <button type="button" class="btn btn-success" id="addDormitaryBut">
                        <span class="glyphicon glyphicon-plus-sign"></span>
                        <span>新增宿舍</span>
                    </button>
                </div>
                <div class="listDiv">
                    <table class="table table-bordered">
                        <caption>宿舍列表</caption>
                        <thead>
                            <tr>
                                <th>宿舍名</th>
                                <th>地址</th>
                                <th>所属项目</th>
                                <th>床位数</th>
                                <th>入住人数</th>
                                <%--<th>是否退租</th>--%>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody id="dormTable">
                            <C:forEach var="dormitary" items="${pageInfo.records}">
                                <tr>
                                    <td>${dormitary.DORM_NAME}</td>
                                    <td>${dormitary.ADDRESS}</td>
                                    <td>${dormitary.PROJ_NAME}</td>
                                    <td>${dormitary.BED_NUM}</td>
                                    <td>${dormitary.OCCU_NUM}</td>
                                    <%--<c:choose>--%>
                                        <%--<c:when test="${dormitary.isRetire == 0}">--%>
                                            <%--<td>否</td>--%>
                                        <%--</c:when>--%>
                                        <%--<C:otherwise>--%>
                                            <%--<td>是</td>--%>
                                        <%--</C:otherwise>--%>
                                    <%--</c:choose>--%>
                                    <td>
                                        <a class="detailBut" id="${dormitary.DORM_ID}" href="javascript:;">详情</a>
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
                <!-- 宿舍详情模态框（Modal） -->
                <div class="modal fade" id="dormitaryDeail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title">宿舍详情</h4>
                            </div>
                            <div class="modal-body form-horizontal" id="modal-bodyDiv">
                                <form id="dormitaryForm">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">宿舍名</label>
                                        <div class="col-sm-7">
                                            <input class="form-control" type="text" id="modal-dormitaryName">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">宿舍地址</label>
                                        <div class="col-sm-7">
                                            <input class="form-control" type="text" id="modal-address">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">所属项目</label>
                                        <div class="col-sm-7">
                                            <input class="form-control" type="text" id="modal-project">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">管理员</label>
                                        <div class="col-sm-7">
                                            <input class="form-control" type="text" id="modal-dormMana">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">床位数</label>
                                        <div class="col-sm-7">
                                            <input class="form-control" type="number" id="modal-bedNum">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">入住人数</label>
                                        <div class="col-sm-7">
                                            <input class="form-control" type="number" id="modal-occuNum">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">入住名单</label>
                                        <div class="col-sm-7">
                                            <div id="modal-checkInList"></div>
                                        </div>
                                    </div>
                                    <div class="form-group date-form-group">
                                        <label for="dtp_input1" class="col-sm-2 control-label">起租时间</label>
                                        <div class="input-group date form_date col-sm-6 dateDiv" id="dateDiv1" data-date="" data-date-format="yyyy-MM-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                            <input class="form-control"  id="rentTime" size="16" type="text" value="" readonly>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                        <input type="hidden" id="dtp_input1" value="" /><br/>
                                    </div>
                                    <div class="form-group date-form-group">
                                        <label for="dtp_input2" class="col-sm-2 control-label">退租时间</label>
                                        <div class="input-group date form_date col-sm-6 dateDiv" id="dateDiv2" data-date="" data-date-format="yyyy-MM-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                            <input class="form-control" id="retireTime" size="16" type="text" value="" readonly>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                        <input type="hidden" id="dtp_input2" value="" /><br/>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">备注</label>
                                        <div class="col-sm-7">
                                            <input class="form-control" type="text" id="modal-remarks">
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
            </div>
        </div>
        <!-- 新增宿舍模态框（Modal） -->
        <div class="modal fade" id="addDormitary" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">新增宿舍</h4>
                    </div>
                    <div class="modal-body form-horizontal" id="modal-addBodyDiv">
                        <form id="addDormitaryForm">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">宿舍名</label>
                                <div class="col-sm-7">
                                    <input class="form-control" type="text" id="modal-addDormitaryName">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">所在省</label>
                                <div class=" col-sm-7">
                                    <select class="form-control" id="provinceSel">
                                        <option value="">请选择所在省</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">所在市</label>
                                <div class=" col-sm-7">
                                    <select class="form-control" id="citySel">
                                        <option value="">请选择所在市</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">宿舍地址</label>
                                <div class="col-sm-7">
                                    <input class="form-control" type="text" id="modal-addAddress">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">所属项目</label>
                                <div class="col-sm-7">
                                    <input class="form-control" type="text" id="modal-addProject">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">管理员</label>
                                <div class="col-sm-7">
                                    <input class="form-control" type="text" id="modal-addDormMana">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">床位数</label>
                                <div class="col-sm-7">
                                    <input class="form-control" type="number" id="modal-addBedNum">
                                </div>
                            </div>
                            <div class="form-group date-form-group">
                                <label for="dtp_input1" class="col-sm-2 control-label">起租时间</label>
                                <div class="input-group date form_date col-sm-6 dateDiv" id="dateDiv3" data-date="" data-date-format="yyyy-MM-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                    <input class="form-control"  id="rentTime2" size="16" type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                                <input type="hidden" id="dtp_input3" value="" /><br/>
                            </div>
                            <div class="form-group date-form-group">
                                <label for="dtp_input2" class="col-sm-2 control-label">退租时间</label>
                                <div class="input-group date form_date col-sm-6 dateDiv" id="dateDiv4" data-date="" data-date-format="yyyy-MM-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                    <input class="form-control" id="retireTime2" size="16" type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                                <input type="hidden" id="dtp_input4" value="" /><br/>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-7">
                                    <input class="form-control" type="text" id="modal-addRemarks">
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
        <!-- 选择项目模态框（Modal） -->
        <div class="modal fade" id="chooseProject" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">选择项目</h4>
                    </div>
                    <div class="modal-body form-horizontal">
                        <form class="form-horizontal" role="form" id="projectChooseForm">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">项目名</label>
                                <div class="col-sm-8">
                                    <input class="form-control" id="projectName" type="text" placeholder="请输入项目名关键字">
                                </div>
                                <span class="col-sm-2">
                                    <button type="button" class="btn btn-success" id="queryProjectBut">搜索</button>
                                </span>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">项目</label>
                                <div class=" col-sm-8">
                                    <select class="form-control" id="projectSel">
                                        <option value="">请选择项目</option>
                                    </select>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" id="chooseProjectConfirmBut">确定</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
        <!-- 选择宿舍管理员模态框（Modal） -->
        <div class="modal fade" id="chooseDormMana" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">选择宿舍管理员</h4>
                    </div>
                    <div class="modal-body form-horizontal">
                        <form class="form-horizontal" role="form" id="dormManaChooseForm">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-8">
                                    <input class="form-control" id="persName" type="text" placeholder="请输入管理员姓名">
                                </div>
                                <span class="col-sm-2">
                                    <button type="button" class="btn btn-success" id="queryPersonBut">搜索</button>
                                </span>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">管理员</label>
                                <div class=" col-sm-8">
                                    <select class="form-control" id="personSel">
                                        <option value="">请选择宿舍管理员</option>
                                    </select>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" id="chooseDormManaConfirmBut">确定</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
        <script type="text/javascript" src="${PROJECT_NAME}/resource/js/common/bootstrap.js"></script>
        <script type="text/javascript" src="${PROJECT_NAME}/resource/js/bootstrap-datetimepicker.js"></script>
        <script type="text/javascript" src="${PROJECT_NAME}/resource/js/bootstrap-datetimepicker.fr.js"></script>
        <script type="text/javascript" src="${PROJECT_NAME}/resource/js/pc_index1.js"></script>
    </body>
</html>

