/**
 * Created by R on 2018/7/11.
 */
var pc_index3 = {
    projName : "", pageNum : 1,//用于记录查询条件，下次刷新时按条件刷新
    init : function(){
        $("#alterSpan").click(function () {
            $("#alterPassword").modal("show");
        });
        $("#quitSpan").click(function () {
            window.location.href = projectName + "/pcView/login";
        });
        pc_index3.bindClickEventToMainLeft();
        pc_index3.bindClickEventToAlterConfirmBut();
        pc_index3.bindClickEventToQueryBut();
        pc_index3.bindClickEventToPageBut();
        pc_index3.bindClickEventToDetailBut();
        pc_index3.bindClickEventToUpdateConfirm();
        pc_index3.bindClickEventToDeleteBut();
        pc_index3.bindClickEventToAddBut();
        pc_index3.initProjectManagerList();
        pc_index3.initSectorList();
        pc_index3.bindClickEventToAddConfirmBut();
        pc_index3.bindClickEventToResetBut();
    },
    bindClickEventToAlterConfirmBut : function () {
        $("#alterConfirm").click(function () {
            var oldPassword = $("#oldPassword").val();
            var newPassword1 = $("#newPassword1").val();
            var newPassword2 = $("#newPassword2").val();
            if(common.isEmpty(oldPassword)){
                alert("请输入原密码");
                return false;
            }
            if(common.isEmpty(newPassword1)){
                alert("请输入新密码")
                return false;
            }
            if(common.isEmpty(newPassword2)){
                alert("请输入确认密码")
                return false;
            }
            if(newPassword1 != newPassword2){
                alert("两次输入的新密码不同，请确认");
                return false;
            }

            var url = projectName + "/managerAjax/alterPassword";
            var params = {
                "oldPassword" : oldPassword,
                "newPassword1" : newPassword1,
                "newPassword2" : newPassword2
            };
            $.post(url, params, function (result) {
                if(result && result.success){
                    alert("密码修改成功");
                    $("#alterPassword").modal("hide");
                }else{
                    if(result.data && result.data.stateInfor)
                        alert(result.data.stateInfor);
                    if(result.error)
                        alert(result.error);
                }
            }, "json");
        });
    },
    bindClickEventToQueryBut : function () {
        $("#queryBut").click(function () {
            var projName = $("#projectName").val();
            pc_index3.projName = projName;
            pc_index3.refreshProjectList(projName, 1);
        });
    },
    bindClickEventToPageBut : function () {
        $("#prePage").click(function () {
            var active = $(".active");
            if(active.prev().attr("id") == "prePage")
                return false;
            var pageNum = active.prev().children("a").html();
            var projectName = $("#projectName").val();
            pc_index3.pageNum = pageNum;
            pc_index3.refreshProjectList(projectName, pageNum);
        });
        $("#nextPage").click(function () {
            var active = $(".active");
            if(active.next().attr("id") == "nextPage")
                return false;
            var pageNum = active.next().children("a").html();
            var projectName = $("#projectName").val();
            pc_index3.pageNum = pageNum;
            pc_index3.refreshProjectList(projectName, pageNum);
        });
        $(".pageLi").click(function () {
            var pageNum = $(this).children("a").html();
            var projectName = $("#projectName").val();
            pc_index3.pageNum = pageNum;
            pc_index3.refreshProjectList(projectName, pageNum);
        });
    },
    //projectName是项目名称关键字，以后用做关键字模糊查询，暂时无用。刷新表格数据可以传空字符串进去。
    refreshProjectList : function(project_Name, pageNum){
        var url = projectName + "/managerAjax/getProjectsByPage";
        var params = {
            "projectName" : project_Name,
            "pageNum" : pageNum
        };
        $.post(url, params, function(result){
            if(result && result.success){
                $("#projectTable").empty();
                var htmlCode = "";
                var pageInfo = result.data;
                var projects = pageInfo.records;
                //将项目数据插入到表格中
                for(var i=0;i<projects.length;i++){
                    var project = projects[i];
                    htmlCode =  "<tr>";
                    htmlCode += "   <td>" + project.PROJ_NAME + "</td>";
                    if(project.NAME)
                        htmlCode += "   <td>" + project.NAME + "</td>";
                    else
                        htmlCode += "   <td></td>";
                    if(project.SECT_NAME)
                        htmlCode += "   <td>" + project.SECT_NAME + "</td>";
                    else
                        htmlCode += "   <td></td>";
                    htmlCode += "   <td>";
                    htmlCode += "       <a class='detailBut' id=" + project.PROJ_ID + " href='javascript:;'>详情</a>";
                    htmlCode += "   </td>";
                    htmlCode += "</tr>";
                    $("#projectTable").append(htmlCode);
                }
                //重置分页页码
                $("#paginationUl").empty();
                htmlCode = '<li class="prePage" id="prePage"><a href="#">&laquo;</a></li>';
                $("#paginationUl").append(htmlCode);
                var totalPage = pageInfo.totalPage;
                var currentPage = pageInfo.currentPage;
                for(var i=1; i<=totalPage; i++){
                    if(i == 1){
                        htmlCode = '<li class="pageLi"><a href="#">' + i + '</a></li>';
                    }else{
                        htmlCode = '<li class="pageLi"><a href="#">' + i + '</a></li>';
                    }
                    $("#paginationUl").append(htmlCode);
                }
                $(".pageLi:eq(" + (currentPage-1) + ")").addClass("active");
                htmlCode = '<li class="nextPage" id="nextPage"><a href="#">&raquo;</a></li>';
                $("#paginationUl").append(htmlCode);
                pc_index3.bindClickEventToPageBut();
                pc_index3.bindClickEventToDetailBut();
            }else{
                if(result.data && result.data.stateInfor)
                    alert(result.data.stateInfor);
                if(result.error)
                    alert(result.error);
            }
        }, "json");
    },
    bindClickEventToDetailBut : function () {
        $(".detailBut").click(function () {
            $("#projectDeail").modal("show");
            var projectId = $(this).attr("id");
            //将项目id记录下，后面接口有用
            $("#modal-bodyDiv").attr("projectId", projectId);

            var url = projectName + "/managerAjax/getProjectDetail";
            var params = {
                "projectId" : projectId
            };
            //获取项目详情
            $.post(url, params, function (result) {
                if(result && result.success){
                    $("#projectForm")[0].reset();
                    var project = result.data;
                    $("#modal-projectName").val(project.PROJ_NAME);
                    $("#modal-manager").val(project.PROJ_MANA);
                    $("#modal-sector").val(project.SECT_ID);
                }else{
                    if(result.data && result.data.stateInfor)
                        alert(result.data.stateInfor);
                    if(result.error)
                        alert(result.error);
                }
            }, "json");
        });
    },
    bindClickEventToUpdateConfirm : function () {
        $("#updateConfirm").click(function () {
            var projectId = $("#modal-bodyDiv").attr("projectid");
            var project_Name = $("#modal-projectName").val();
            var projectManagerPersonId = $("#modal-manager").val();
            var sectorId = $("#modal-sector").val();

            var url = projectName + "/managerAjax/updateProject";
            var params = {
                "projectId" : projectId,
                "projectName" : project_Name,
                "projectManagerPersonId" : projectManagerPersonId,
                "sectorId" : sectorId
            };
            $.post(url, params, function(result){
                if(result && result.success){
                    $("#projectDeail").modal("hide");
                    alert("操作成功");
                    pc_index3.refreshProjectList(pc_index3.projName, pc_index3.pageNum);
                }else{
                    if(result.data && result.data.stateInfor)
                        alert(result.data.stateInfor);
                    if(result.error)
                        alert(result.error);
                }
            }, "json");
        });
    },
    bindClickEventToDeleteBut : function () {
        $("#deleteBut").click(function () {
            if(window.confirm('删除当前项目？')){
                //alert("确定");
                //不知为何，前面设置的dormitaryId属性会变成projectid，可能是attr方法的问题
                var projectId = $("#modal-bodyDiv").attr("projectid");
                var url = projectName + "/managerAjax/deleteProject";
                var params = {
                    "projectId" : projectId
                };
                $.post(url, params, function(result){
                    if(result && result.success){
                        $("#projectDeail").modal("hide");
                        alert("删除成功");
                        pc_index3.refreshProjectList(pc_index3.projName, pc_index3.pageNum);
                    }else{
                        if(result.data && result.data.stateInfor)
                            alert(result.data.stateInfor);
                        if(result.error)
                            alert(result.error);
                    }
                }, "json");
            }else{
                //alert("取消");
                return false;
            }
        });
    },
    bindClickEventToAddBut : function () {
        $("#addProjectBut").click(function () {
            $("#addProjectForm")[0].reset();
            $("#modal-bodyDiv2").removeAttr("projectid");
            $("#addProjectModal").modal("show");
        });
    },
    bindClickEventToMainLeft : function () {
        $(".mainLeftDiv").click(function () {
            if($(this).attr("id") == "mainLeftDiv1"){
                window.location.href = projectName + "/pcView/index";
            }
            if($(this).attr("id") == "mainLeftDiv2"){
                window.location.href = projectName + "/pcView/index2";
            }
            if($(this).attr("id") == "mainLeftDiv3"){
                window.location.href = projectName + "/pcView/index3";
            }
            if($(this).attr("id") == "mainLeftDiv4"){
                window.location.href = projectName + "/pcView/index4";
            }

        });
    },
    initProjectManagerList : function () {
        var url = projectName + "/managerAjax/getProjectManagers";
        var params = {};
        $.post(url, params, function (result) {
            if(result && result.success){
                var projectManagers = result.data;
                $("#modal-manager").empty();
                $("#modal-manager2").empty();
                var htmlCode = '<option value=""></option>';
                $("#modal-manager").append(htmlCode);
                $("#modal-manager2").append(htmlCode);

                for(var i=0;i<projectManagers.length;i++){
                    var projectManager = projectManagers[i];
                    htmlCode = '<option value="' + projectManager.emplId + '">' + projectManager.name + '</option>';
                    $("#modal-manager").append(htmlCode);
                    $("#modal-manager2").append(htmlCode);
                }
            }else{
                if(result.data && result.data.stateInfor)
                    alert(result.data.stateInfor);
                if(result.error)
                    alert(result.error);
            }
        }, "json");
    },
    initSectorList : function () {
        var url = projectName + "/managerAjax/getAllSectors";
        var params = {};
        $.post(url, params, function (result) {
            if(result && result.success){
                var sectors = result.data;
                $("#modal-sector").empty();
                $("#modal-sector2").empty();
                var htmlCode = '<option value=""></option>';
                $("#modal-sector").append(htmlCode);
                $("#modal-sector2").append(htmlCode);

                for(var i=0;i<sectors.length;i++){
                    var sector = sectors[i];
                    htmlCode = '<option value="' + sector.SECT_ID + '">' + sector.SECT_NAME + '</option>';
                    $("#modal-sector").append(htmlCode);
                    $("#modal-sector2").append(htmlCode);
                }
            }else{
                if(result.data && result.data.stateInfor)
                    alert(result.data.stateInfor);
                if(result.error)
                    alert(result.error);
            }
        }, "json");
    },
    bindClickEventToAddConfirmBut : function () {
        $("#addConfirm").click(function () {
            var url = projectName + "/managerAjax/addProject";
            var project_Name = $("#modal-projectName2").val();
            var projectManagerPersonId = $("#modal-manager2").val();
            var sectorId = $("#modal-sector2").val();
            var params = {
                "projectName" : project_Name,
                "projectManagerPersonId" : projectManagerPersonId,
                "sectorId" : sectorId
            };
            $.post(url, params, function (result) {
                if(result && result.success){
                    $("#addProjectModal").modal("hide");
                    alert("新增成功");
                    pc_index3.refreshProjectList(pc_index3.projName, pc_index3.pageNum);
                }else{
                    if(result.data && result.data.stateInfor)
                        alert(result.data.stateInfor);
                    if(result.error)
                        alert(result.error);
                }
            }, "json");
        });
    },
    bindClickEventToResetBut : function () {
        $("#resetBut").click(function () {
            $("#queryForm")[0].reset();
            pc_index3.projName = "";
            pc_index3.pageNum = 1;
            pc_index3.refreshProjectList(pc_index3.projName, pc_index3.pageNum);
        });
    }
};

common.addLoadEvent(pc_index3.init);
