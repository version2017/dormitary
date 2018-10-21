/**
 * Created by R on 2018/7/11.
 */
var pc_index2 = {
    sectName : "", pageNum : 1,//用于记录查询条件，下次刷新时按条件刷新
    init : function(){
        $("#alterSpan").click(function () {
            $("#alterPassword").modal("show");
        });
        $("#quitSpan").click(function () {
            window.location.href = projectName + "/pcView/login";
        });
        pc_index2.bindClickEventToMainLeft();
        pc_index2.bindClickEventToAlterConfirmBut();
        pc_index2.bindClickEventToQueryBut();
        pc_index2.bindClickEventToPageBut();
        pc_index2.bindClickEventToDetailBut();
        pc_index2.bindClickEventToUpdateConfirm();
        pc_index2.bindClickEventToDeleteBut();
        pc_index2.bindClickEventToAddBut();
        pc_index2.initSectorManagerList();
        pc_index2.bindClickEventToAddConfirmBut();
        pc_index2.bindClickEventToResetBut();
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
            var sectName = $("#sectName").val();
            pc_index2.sectName = sectName;
            pc_index2.refreshSectList(sectName, 1);
        });
    },
    bindClickEventToPageBut : function () {
        $("#prePage").click(function () {
            var active = $(".active");
            if(active.prev().attr("id") == "prePage")
                return false;
            var pageNum = active.prev().children("a").html();
            pc_index2.pageNum = pageNum;
            pc_index2.refreshSectList(pc_index2.sectName, pageNum);
        });
        $("#nextPage").click(function () {
            var active = $(".active");
            if(active.next().attr("id") == "nextPage")
                return false;
            var pageNum = active.next().children("a").html();
            pc_index2.pageNum = pageNum;
            pc_index2.refreshSectList(pc_index2.sectName, pageNum);
        });
        $(".pageLi").click(function () {
            var pageNum = $(this).children("a").html();
            pc_index2.pageNum = pageNum;
            pc_index2.refreshSectList(pc_index2.sectName, pageNum);
        });
    },
    //sectorName是部门名称关键字，以后用做关键字模糊查询，暂时无用。刷新表格数据可以传空字符串进去。
    refreshSectList : function(sectName, pageNum){
        var url = projectName + "/managerAjax/getSectorsByPage";
        var params = {
            "sectName" : sectName,
            "pageNum" : pageNum
        };
        $.post(url, params, function(result){
            if(result && result.success){
                $("#sectTable").empty();
                var htmlCode = "";
                var pageInfo = result.data;
                var sectors = pageInfo.records;
                //将部门数据插入到表格中
                for(var i=0;i<sectors.length;i++){
                    var sector = sectors[i];
                    htmlCode =  "<tr>";
                    htmlCode += "   <td>" + sector.SECT_NAME + "</td>";
                    if(common.isEmpty(sector.NAME))
                        htmlCode += "       <td></td>";
                    else
                        htmlCode += "       <td>" + sector.NAME + "</td>";
                    htmlCode += "   <td>";
                    htmlCode += "       <a class='detailBut' id=" + sector.SECT_ID + " href='javascript:;'>详情</a>";
                    htmlCode += "   </td>";
                    htmlCode += "</tr>";
                    $("#sectTable").append(htmlCode);
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
                pc_index2.bindClickEventToPageBut();
                pc_index2.bindClickEventToDetailBut();
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
            $("#sectorDeail").modal("show");
            var sectorId = $(this).attr("id");
            //将部门id记录下，后面接口有用
            $("#modal-bodyDiv").attr("sectorId", sectorId);

            var url = projectName + "/managerAjax/getSectorDetail";
            var params = {
                "sectorId" : sectorId
            };
            //获取部门详情
            $.post(url, params, function (result) {
                if(result && result.success){
                    $("#sectorForm")[0].reset();
                    var sector = result.data;
                    $("#modal-sectorName").val(sector.SECT_NAME);
                    $("#modal-manager").val(sector.SECT_MANA);
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
            var sectorId = $("#modal-bodyDiv").attr("sectorid");
            var sectorName = $("#modal-sectorName").val();
            var sectorManagerPersonId = $("#modal-manager").val();

            var url = projectName + "/managerAjax/updateSector";
            var params = {
                "sectorId" : sectorId,
                "sectorName" : sectorName,
                "sectorManagerPersonId" : sectorManagerPersonId
            };
            $.post(url, params, function(result){
                if(result && result.success){
                    $("#sectorDeail").modal("hide");
                    alert("操作成功");
                    pc_index2.refreshSectList(pc_index2.sectName, pc_index2.pageNum);
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
            if(window.confirm('删除当前部门？')){
                //alert("确定");
                //不知为何，前面设置的dormitaryId属性会变成sectorid，可能是attr方法的问题
                var sectorId = $("#modal-bodyDiv").attr("sectorid");
                var url = projectName + "/managerAjax/deleteSector";
                var params = {
                    "sectorId" : sectorId
                };
                $.post(url, params, function(result){
                    if(result && result.success){
                        $("#sectorDeail").modal("hide");
                        alert("删除成功");
                        pc_index2.refreshSectList(pc_index2.sectName, pc_index2.pageNum);
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
        $("#addSectorBut").click(function () {
            $("#addSectorForm")[0].reset();
            $("#modal-bodyDiv2").removeAttr("sectorid");
            $("#addSectorModal").modal("show");
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
    initSectorManagerList : function () {
        var url = projectName + "/managerAjax/getSectorManagers";
        var params = {};
        $.post(url, params, function (result) {
            if(result && result.success){
                var sectorManagers = result.data;
                $("#modal-manager").empty();
                $("#modal-manager2").empty();
                var htmlCode = '<option value=""></option>';
                $("#modal-manager").append(htmlCode);
                $("#modal-manager2").append(htmlCode);

                for(var i=0;i<sectorManagers.length;i++){
                    var sectorManager = sectorManagers[i];
                    htmlCode = '<option value="' + sectorManager.emplId + '">' + sectorManager.name + '</option>';
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
    bindClickEventToAddConfirmBut : function () {
        $("#addConfirm").click(function () {
            var url = projectName + "/managerAjax/addSector";
            var sectorName = $("#modal-sectorName2").val();
            var persNum = 0;//该字段暂时无用
            var sectorManagerPersonId = $("#modal-manager2").val();
            var params = {
                "sectorName" : sectorName,
                "persNum" : persNum,
                "sectorManagerPersonId" : sectorManagerPersonId
            };
            $.post(url, params, function (result) {
                if(result && result.success){
                    $("#addSectorModal").modal("hide");
                    alert("新增成功");
                    pc_index2.refreshSectList(pc_index2.sectName, pc_index2.pageNum);
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
            pc_index2.sectName = "";
            pc_index2.pageNum = 1;
            pc_index2.refreshSectList(pc_index2.sectName, pc_index2.pageNum);
        });
    }
};

common.addLoadEvent(pc_index2.init);
