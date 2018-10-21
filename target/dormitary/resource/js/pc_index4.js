/**
 * Created by R on 2018/7/11.
 */
var pc_index4 = {
    persName : "", emplId : "", role : "", pageNum : 1,//用于记录查询条件，下次刷新时按条件刷新
    init : function(){
        $("#alterSpan").click(function () {
            $("#alterPassword").modal("show");
        });
        $("#quitSpan").click(function () {
            window.location.href = projectName + "/pcView/login";
        });
        pc_index4.bindClickEventToMainLeft();
        pc_index4.bindClickEventToAlterConfirmBut();
        pc_index4.bindClickEventToQueryBut();
        pc_index4.bindClickEventToPageBut();
        pc_index4.bindClickEventToDetailBut();
        pc_index4.bindClickEventToUpdateConfirm();
        pc_index4.bindClickEventToDeleteBut();
        pc_index4.bindClickEventToAddBut();
        pc_index4.initSectorList();
        pc_index4.initDormitaryList();
        // pc_index4.initRoleList();
        pc_index4.bindClickEventToAddConfirmBut();
        pc_index4.bindChangeEventToPositionSelect();
        pc_index4.bindClickEventToResetBut();
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
            var persName = $("#personName").val();
            var emplId = $("#employNum").val();
            var role = $("#role").val();
            pc_index4.persName = persName;
            pc_index4.emplId = emplId;
            pc_index4.role = role;
            pc_index4.refreshPersonList(1, persName, emplId, role);
        });
    },
    bindClickEventToPageBut : function () {
        $("#prePage").click(function () {
            var active = $(".active");
            if(active.prev().attr("id") == "prePage")
                return false;
            var pageNum = active.prev().children("a").html();
            pc_index4.pageNum = pageNum;
            pc_index4.refreshPersonList(pageNum, pc_index4.persName, pc_index4.emplId, pc_index4.role);
        });
        $("#nextPage").click(function () {
            var active = $(".active");
            if(active.next().attr("id") == "nextPage")
                return false;
            var pageNum = active.next().children("a").html();
            pc_index4.pageNum = pageNum;
            pc_index4.refreshPersonList(pageNum, pc_index4.persName, pc_index4.emplId, pc_index4.role);
        });
        $(".pageLi").click(function () {
            var pageNum = $(this).children("a").html();
            pc_index4.pageNum = pageNum;
            pc_index4.refreshPersonList(pageNum, pc_index4.persName, pc_index4.emplId, pc_index4.role);
        });
    },
    refreshPersonList : function(pageNum, personName, employId, role){
        var url = projectName + "/managerAjax/getPersonsByParamAndPage";
        var params = {
            "pageNum" : pageNum
        };
        if(!common.isEmpty(personName)){
            params.personName = personName;
        }
        if(!common.isEmpty(employId)){
            params.emplId = employId;
        }
        if(!common.isEmpty(role)){
            params.role = role;
        }

        $.post(url, params, function(result){
            if(result && result.success){
                $("#personTable").empty();
                var htmlCode = "";
                var pageInfo = result.data;
                var persons = pageInfo.records;
                if(persons.length != 0){
                    //将人员数据插入到表格中
                    for(var i=0;i<persons.length;i++){
                        var person = persons[i];
                        htmlCode =  "<tr>";
                        htmlCode += "   <td>" + person.NAME + "</td>";
                        htmlCode += "   <td>" + person.EMPL_ID + "</td>";
                        if(common.isEmpty(person.SECT_NAME))
                            htmlCode += "   <td></td>";
                        else
                            htmlCode += "   <td>" + person.SECT_NAME + "</td>";
                        htmlCode += "   <td>" + person.PHON_NUM + "</td>";
                        if(common.isEmpty(person.DORM_NAME))
                            htmlCode += "   <td></td>";
                        else
                            htmlCode += "   <td>" + person.DORM_NAME + "</td>";
                        if(common.isEmpty(person.ROLE_NAME))
                            htmlCode += "   <td></td>";
                        else
                            htmlCode += "   <td>" + person.ROLE_NAME + "</td>";
                        htmlCode += "   <td>";
                        htmlCode += "       <a class='detailBut' id=" + person.EMPL_ID + " href='javascript:;'>详情</a>";
                        htmlCode += "   </td>";
                        htmlCode += "</tr>";
                        $("#personTable").append(htmlCode);
                    }
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
                pc_index4.bindClickEventToPageBut();
                pc_index4.bindClickEventToDetailBut();
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
            $("#personDeail").modal("show");
            var emplId = $(this).attr("id");
            //将人员id记录下，后面接口有用
            $("#modal-bodyDiv").attr("emplId", emplId);

            var url = projectName + "/managerAjax/getPersonDetail";
            var params = {
                "emplId" : emplId
            };
            //获取人员详情
            $.post(url, params, function (result) {
                if(result && result.success){
                    $("#personForm")[0].reset();
                    var person = result.data;
                    $("#modal-personName").val(person.NAME);
                    $("#modal-employNum").val(person.EMPL_ID);
                    $("#modal-phoneNum").val(person.PHON_NUM);
                    $("#modal-sex").val(person.SEX);
                    $("#modal-sector").val(person.SECT_ID);
                    $("#modal-position").val(person.ROLE_NAME);
                    if(1 == person.IS_LEADER){
                        $("#manaDormitarySelect").show();
                        $("#manaDormitary").val(person.MANA_DORM);
                    }
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
            var emplId = $("#modal-bodyDiv").attr("emplId");
            var personName = $("#modal-personName").val();
            var phoneNum = $("#modal-phoneNum").val();
            var sex = $("#modal-sex").val();
            var sectorId = $("#modal-sector").val();
            var role = $("#modal-position").val();
            var manaDormitary = $("#manaDormitary").val();
            if(common.isEmpty(emplId)){
                alert("无法获得员工工号");
                return false;
            }
            if(common.isEmpty(personName)){
                alert("请输入姓名");
                return false;
            }
            if(common.isEmpty(sectorId)){
                alert("请选择部门");
                return false;
            }

            var url = projectName + "/managerAjax/updatePerson";
            var params = {
                "emplId" : emplId,
                "personName" : personName,
                "phoneNum" : phoneNum,
                "sex" : sex,
                "sectorId" : sectorId,
                "role" : role
            };
            if(!common.isEmpty(manaDormitary))
                params.manaDormitary = manaDormitary;

            $.post(url, params, function(result){
                if(result && result.success){
                    $("#personDeail").modal("hide");
                    alert("操作成功");
                    pc_index4.refreshPersonList(pc_index4.pageNum, pc_index4.persName, pc_index4.emplId, pc_index4.role);
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
            if(window.confirm('删除当前员工？')){
                //alert("确定");
                //不知为何，前面设置的dormitaryId属性会变成personid，可能是attr方法的问题
                var emplId = $("#modal-bodyDiv").attr("emplid");
                var url = projectName + "/managerAjax/deletePerson";
                var params = {
                    "emplId" : emplId
                };
                $.post(url, params, function(result){
                    if(result && result.success){
                        $("#personDeail").modal("hide");
                        alert("删除成功");
                        pc_index4.refreshPersonList(pc_index4.pageNum, pc_index4.persName, pc_index4.emplId, pc_index4.role);
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
        $("#addPersonBut").click(function () {
            $("#addPersonForm")[0].reset();
            $("#modal-bodyDiv2").removeAttr("personid");
            $("#addPersonModal").modal("show");
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
    initSectorList : function () {
        var url = projectName + "/managerAjax/getAllSectors";
        var params = {};
        $.post(url, params, function (result) {
            if(result && result.success){
                var sectors = result.data;
                $("#modal-sector").empty();
                $("#modal-sector2").empty();
                var htmlCode = '<option value="">请选择所属部门</option>';
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
    initDormitaryList : function () {
        var url = projectName + "/managerAjax/listAllDormitary";
        var params = {};
        $.post(url, params, function(result){
            if(result && result.success){
                var dormitaries = result.data;
                $("#manaDormitary").empty();
                $("#manaDormitary2").empty();
                var htmlCode = '<option value="">请选择要管理的宿舍</option>';
                $("#manaDormitary").append(htmlCode);
                $("#manaDormitary2").append(htmlCode);

                for(var i=0;i<dormitaries.length;i++){
                    var dormitary = dormitaries[i];
                    htmlCode = '<option value="' + dormitary.dormId + '">' + dormitary.dormName + '</option>';
                    $("#manaDormitary").append(htmlCode);
                    $("#manaDormitary2").append(htmlCode);
                }
            }else{
                if(result.data && result.data.stateInfor)
                    alert(result.data.stateInfor);
                if(result.error)
                    alert(result.error);
            }
        }, "json");
    },
    initRoleList : function () {
        var url = projectName + "/managerAjax/listAllRoles";
        var params = {};
        $.post(url, params, function(result){
            if(result && result.success){
                var roles = result.data;
                $("#role").empty();
                $("#modal-position").empty();
                $("#modal-position2").empty();
                var htmlCode = '<option value="">请选择职位</option>';
                $("#role").append(htmlCode);
                $("#modal-position").append(htmlCode);
                $("#modal-position2").append(htmlCode);

                for(var i=0;i<roles.length;i++){
                    var role = roles[i];
                    htmlCode = '<option value="' + role.roleName + '">' + role.roleName + '</option>';
                    $("#role").append(htmlCode);
                    $("#modal-position").append(htmlCode);
                    $("#modal-position2").append(htmlCode);
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
        $("#addConfirmBut").click(function () {
            var url = projectName + "/managerAjax/addPerson";
            var employId = $("#modal-employNum2").val();
            var personName = $("#modal-personName2").val();
            var sex = $("#modal-sex2").val();
            var phoneNum = $("#modal-phoneNum2").val();
            var sector = $("#modal-sector2").val();
            var role = $("#modal-position2").val();
            var manaDormitary = $("#manaDormitary2").val();
            if(common.isEmpty(employId) || common.isEmpty(personName)){
                alert("请输入工号和姓名");
                return false;
            }
            if(common.isEmpty(sector)){
                alert("请选择部门");
                return false;
            }
            var params = {
                "employId" : employId,
                "personName" : personName,
                "sex" : sex,
                "phoneNum" : phoneNum,
                "sector" : sector,
                "role" : role
            };
            if(!common.isEmpty(manaDormitary))
                params.manaDormitary = manaDormitary;

            $.post(url, params, function (result) {
                if(result && result.success){
                    $("#addPersonModal").modal("hide");
                    alert("新增成功");
                    pc_index4.refreshPersonList(pc_index4.pageNum, pc_index4.persName, pc_index4.emplId, pc_index4.role);
                }else{
                    if(result.data && result.data.stateInfor)
                        alert(result.data.stateInfor);
                    if(result.error)
                        alert(result.error);
                }
            }, "json");
        });
    },
    bindChangeEventToPositionSelect : function () {
        $("#modal-position").change(function () {
            var position = $("#modal-position").val();
            if(position == 1){
                $("#manaDormitarySelect").show();
            }else{
                $("#manaDormitarySelect").hide();
            }
        });
        $("#modal-position2").change(function () {
            var position = $("#modal-position2").val();
            if(position == 1){
                $("#manaDormitarySelect2").show();
            }else{
                $("#manaDormitarySelect2").hide();
            }
        });
    },
    bindClickEventToResetBut : function () {
        $("#resetBut").click(function () {
            $("#queryForm")[0].reset();
            pc_index4.persName = "";
            pc_index4.emplId = "";
            pc_index4.role = "";
            pc_index4.pageNum = 1;
            pc_index4.refreshPersonList(pc_index4.pageNum, pc_index4.persName, pc_index4.emplId, pc_index4.role);
        });
    }
};

common.addLoadEvent(pc_index4.init);
