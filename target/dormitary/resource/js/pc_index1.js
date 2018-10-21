/**
 * Created by R on 2018/7/11.
 */
var pc_index = {
    dormName : "", province : "", pageNum : 1,//用于记录查询条件，下次刷新时按条件刷新
    init : function(){
        $("#alterSpan").click(function () {
            $("#alterPassword").modal("show");
        });
        $("#quitSpan").click(function () {
            window.location.href = projectName + "/pcView/login";
        });
        $('.form_date').datetimepicker({
            language:  'zh-CN',
            weekStart: 1,
            todayBtn:  1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        });
        initRegion("provinceSel", "citySel");
        pc_index.bindClickEventToMainLeft();
        pc_index.bindClickEventToAlterConfirmBut();
        pc_index.bindClickEventToQueryBut();
        pc_index.bindClickEventToPageBut();
        pc_index.bindClickEventToDetailBut();
        pc_index.bindClickEventToUpdateConfirm();
        pc_index.bindClickEventToDeleteBut();
        pc_index.bindClickEventToAddBut();
        pc_index.bindFocusEventToProjectInput();
        pc_index.bindClickEventToChooseProjectQueryBut();
        pc_index.bindClickEventToChooseDormManaQueryBut();
        pc_index.bindClickEventToChooseProjectConfirmBut();
        pc_index.bindClickEventToChooseDormManaConfirmBut();
        pc_index.bindClickEventToAddDormitaryBut();
        pc_index.bindClickEventToResetBut();
        pc_index.bindFocusEventToDormManaInput();
    },
    initProjectList : function () {
        var url = projectName + "/managerAjax/getProjectsByKeyWord";
        var keyWord = $("#projectName").val();

        var params = {
            "keyWord" : keyWord
        };
        $.ajax({
            type : "post",
            url : url,
            data : params,
            async : false,
            dataType : "json",
            success : function(result){
                if(result && result.success){
                    var allProjects = result.data;

                    $("#projectSel").empty();
                    var htmlCode = '<option value="">请选择项目</option>';
                    $("#projectSel").append(htmlCode);
                    for(var i=0;i<allProjects.length;i++){
                        var project = allProjects[i];
                        htmlCode = '<option value="' + project.projId + '">' + project.projName + '</option>';
                        $("#projectSel").append(htmlCode);
                    }
                }else{
                    if(result.data && result.data.stateInfor)
                        alert(result.data.stateInfor);
                    if(result.error)
                        alert(result.error);
                }
            }
        });
    },
    initPersonList : function () {
        var url = projectName + "/managerAjax/getDormManasByName";
        var persName = $("#persName").val();

        var params = {
            "persName" : persName
        };
        $.ajax({
            type : "post",
            url : url,
            data : params,
            async : false,
            dataType : "json",
            success : function(result){
                if(result && result.success){
                    var persons = result.data;
                    $("#personSel").empty();
                    var htmlCode = '<option value="">请选择宿舍管理员</option>';
                    $("#personSel").append(htmlCode);
                    for(var i=0;i<persons.length;i++){
                        var person = persons[i];
                        htmlCode = '<option value="' + person.EMPL_ID + '">' + person.NAME + '</option>';
                        $("#personSel").append(htmlCode);
                    }
                }else{
                    if(result.data && result.data.stateInfor)
                        alert(result.data.stateInfor);
                    if(result.error)
                        alert(result.error);
                }
            }
        });
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
            var dormitaryName = $("#dormitaryName").val();
            var location = $("#location").val();
            pc_index.dormName = dormitaryName;
            pc_index.province = location;
            pc_index.refreshDormList(dormitaryName, location, 1);
        });
    },
    bindClickEventToPageBut : function () {
        $("#prePage").click(function () {
            var active = $(".active");
            if(active.prev().attr("id") == "prePage")
                return false;
            var pageNum = active.prev().children("a").html();
            var dormitaryName = $("#dormitaryName").val();
            var location = $("#location").val();
            pc_index.pageNum = pageNum;
            pc_index.refreshDormList(dormitaryName, location, pageNum);
        });
        $("#nextPage").click(function () {
            var active = $(".active");
            if(active.next().attr("id") == "nextPage")
                return false;
            var pageNum = active.next().children("a").html();
            var dormitaryName = $("#dormitaryName").val();
            var location = $("#location").val();
            pc_index.pageNum = pageNum;
            pc_index.refreshDormList(dormitaryName, location, pageNum);
        });
        $(".pageLi").click(function () {
            var pageNum = $(this).children("a").html();
            var dormitaryName = $("#dormitaryName").val();
            var location = $("#location").val();
            pc_index.pageNum = pageNum;
            pc_index.refreshDormList(dormitaryName, location, pageNum);
        });
    },
    refreshDormList : function (dormitaryName, location, pageNum) {
        var url = projectName + "/managerAjax/getDormitaries"
        var params = {
            "dormitaryName" : dormitaryName,
            "location" : location,
            "pageNum" : pageNum
        };
        $.post(url, params, function (result) {
            if(result && result.success){
                $("#dormTable").empty();
                var htmlCode = "";
                var pageInfo = result.data;
                var dormitaries = pageInfo.records;
                //将宿舍数据插入到表格中
                for(var i=0;i<dormitaries.length;i++){
                    var dormitary = dormitaries[i];
                    htmlCode =  "<tr>";
                    htmlCode += "   <td>" + dormitary.DORM_NAME + "</td>";
                    htmlCode += "   <td>" + dormitary.ADDRESS + "</td>";
                    if(common.isEmpty(dormitary.PROJ_NAME)){
                        htmlCode += "   <td></td>";
                    }else{
                        htmlCode += "   <td>" + dormitary.PROJ_NAME + "</td>";
                    }
                    htmlCode += "   <td>" + dormitary.BED_NUM + "</td>";
                    htmlCode += "   <td>" + dormitary.OCCU_NUM + "</td>";
                    htmlCode += "   <td>";
                    htmlCode += "       <a class='detailBut' id=" + dormitary.DORM_ID + " href='javascript:;'>详情</a>";
                    htmlCode += "   </td>";
                    htmlCode += "</tr>";
                    $("#dormTable").append(htmlCode);
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
                pc_index.bindClickEventToPageBut();
                pc_index.bindClickEventToDetailBut();
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
            $("#dormitaryDeail").modal("show");
            $("#dormitaryForm")[0].reset();
            var dormitaryId = $(this).attr("id");
            //将宿舍id记录下，后面接口有用
            $("#modal-bodyDiv").attr("dormitaryId", dormitaryId);

            var url = projectName + "/managerAjax/getDormitaryDetail";
            var params = {
                "dormitaryId" : dormitaryId
            };
            //获取宿舍详情
            $.post(url, params, function (result) {
                if(result && result.success){
                    $("#dormitaryForm")[0].reset();
                    var dormitary = result.data;
                    $("#modal-dormitaryName").val(dormitary.DORM_NAME);
                    $("#modal-address").val(dormitary.ADDRESS);
                    $("#modal-project").val(dormitary.PROJ_NAME);
                    $("#modal-project").attr("projectid", dormitary.PROJ_ID);
                    $("#modal-dormMana").val(dormitary.NAME);
                    $("#modal-dormMana").attr("emplId", dormitary.EMPL_ID_REF);
                    $("#modal-bedNum").val(dormitary.BED_NUM);
                    $("#modal-occuNum").val(dormitary.OCCU_NUM);
                    if(!common.isEmpty(dormitary.RENT_DATE)){
                        $("#rentTime").val(dormitary.RENT_DATE);
                        $("#dateDiv1").datetimepicker("update");
                    }
                    if(!common.isEmpty(dormitary.RETIRE_TIME)){
                        $("#retireTime").val(dormitary.RETIRE_TIME);
                        $("#dateDiv2").datetimepicker("update");
                    }
                    if(!common.isEmpty(dormitary.REMARKS)){
                        $("#modal-remarks").val(dormitary.REMARKS);
                    }
                }else{
                    if(result.data && result.data.stateInfor)
                        alert(result.data.stateInfor);
                    if(result.error)
                        alert(result.error);
                }
            }, "json");
            //获取入住人员名单
            var url = projectName + "/managerAjax/getCheckInListOfDormitary";
            $.post(url, params, function (result) {
                if(result && result.success){
                    $("#modal-checkInList").empty();
                    var personList = result.data;
                    if(personList!=null && personList.length>0){
                        var htmlCode = "";
                        for(var i=0;i<personList.length;i++){
                            var person = personList[i];
                            htmlCode = "<p>" + person.name + "-" + person.emplId + "-" + person.phonNum + "</p>";
                            $("#modal-checkInList").append(htmlCode);
                        }
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
            //不知为何，前面设置的dormitaryId属性会变成dormitaryid，可能是attr方法的问题
            var dormitaryId = $("#modal-bodyDiv").attr("dormitaryid");
            var dormitaryName = $("#modal-dormitaryName").val();
            var address = $("#modal-address").val();
            var project = $("#modal-project").val();
            var bedNum = $("#modal-bedNum").val();
            var occuNum = $("#modal-occuNum").val();
            var projId = $("#modal-project").attr("projectid");
            var rentTime = $("#rentTime").val();
            var retireTime = $("#retireTime").val();
            var remarks = $("#modal-remarks").val();
            var emplIdRef = $("#modal-dormMana").attr("emplid");

            var url = projectName + "/managerAjax/updateOrAddDormitary";
            var params = {
                "dormitaryId" : dormitaryId,
                "dormitaryName" : dormitaryName,
                "address" : address,
                "project" : project,
                "bedNum" : bedNum,
                "occuNum" : occuNum,
                "remarks" : remarks,
                "emplIdRef" : emplIdRef
            };
            if(!common.isEmpty(projId)){
                params.projId = projId;
            }
            if(!common.isEmpty(rentTime)){
                params.rentTime = rentTime;
            }
            if(!common.isEmpty(retireTime)){
                params.retireTime = retireTime;
            }
            $.post(url, params, function(result){
                if(result && result.success){
                    $("#dormitaryDeail").modal("hide");
                    alert("操作成功");
                    pc_index.refreshDormList(pc_index.dormName, pc_index.province, pc_index.pageNum);
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
            if(window.confirm('删除当前宿舍？')){
                //alert("确定");
                //不知为何，前面设置的dormitaryId属性会变成dormitaryid，可能是attr方法的问题
                var dormitaryId = $("#modal-bodyDiv").attr("dormitaryid");
                var url = projectName + "/managerAjax/deleteDormitary";
                var params = {
                    "dormitaryId" : dormitaryId
                };
                $.post(url, params, function(result){
                    if(result && result.success){
                        $("#dormitaryDeail").modal("hide");
                        alert("删除成功");
                        pc_index.refreshDormList(pc_index.dormName, pc_index.province, pc_index.pageNum);
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
        $("#addDormitaryBut").click(function () {
            $("#addDormitaryForm")[0].reset();
            $("#modal-addProject").removeAttr("projectId");
            $("#addDormitary").modal("show");
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
    bindFocusEventToProjectInput : function () {
        $("#modal-project").focus(function(){
            $("#chooseProject").modal("show");
            $("#projectChooseForm")[0].reset();
            pc_index.initProjectList();
        });
        $("#modal-addProject").focus(function(){
            $("#chooseProject").modal("show");
            $("#projectChooseForm")[0].reset();
            pc_index.initProjectList();
        });
    },
    bindFocusEventToDormManaInput : function () {
        $("#modal-dormMana").focus(function(){
            $("#chooseDormMana").modal("show");
            $("#dormManaChooseForm")[0].reset();
            pc_index.initPersonList();
        });
        $("#modal-addDormMana").focus(function(){
            $("#chooseDormMana").modal("show");
            $("#dormManaChooseForm")[0].reset();
            pc_index.initPersonList();
        });
    },
    bindClickEventToChooseProjectQueryBut : function () {
        $("#queryProjectBut").click(function () {
            var keyWord = $("#projectName").val();
            if(common.isEmpty(keyWord)){
                return false;
            }

            pc_index.initProjectList();
            $("#projectSel")[0].size = $("#projectSel option").size();
            $("#projectSel").click(function () {
                if(1 != $(this)[0].size)
                    $(this)[0].size = 1;
            });
        });
    },
    bindClickEventToChooseDormManaQueryBut : function () {
        $("#queryPersonBut").click(function () {
            var persName = $("#persName").val();
            if(common.isEmpty(persName)){
                return false;
            }

            pc_index.initPersonList();
            $("#personSel")[0].size = $("#personSel option").size();
            $("#personSel").click(function () {
                if(1 != $(this)[0].size)
                    $(this)[0].size = 1;
            });
        });
    },
    bindClickEventToChooseProjectConfirmBut : function () {
        $("#chooseProjectConfirmBut").click(function(){
            var projectSelected = $("#projectSel").val();
            if(common.isEmpty(projectSelected)){
                alert("请选择项目");
                return false;
            }
            var projectName =  $("#projectSel").find("option:selected").text();
            $("#modal-addProject").val(projectName);
            $("#modal-addProject").attr("projectId", projectSelected);
            $("#modal-project").val(projectName);
            $("#modal-project").attr("projectId", projectSelected);
            $("#chooseProject").modal("hide");
        });
    },
    bindClickEventToChooseDormManaConfirmBut : function () {
        $("#chooseDormManaConfirmBut").click(function(){
            var personSelected = $("#personSel").val();
            if(common.isEmpty(personSelected)){
                alert("请选择管理员");
                return false;
            }
            var personName =  $("#personSel").find("option:selected").text();
            $("#modal-addDormMana").val(personName);
            $("#modal-addDormMana").attr("emplId", personSelected);
            $("#modal-dormMana").val(personName);
            $("#modal-dormMana").attr("emplId", personSelected);
            $("#chooseDormMana").modal("hide");
        });
    },
    bindClickEventToAddDormitaryBut : function () {
        $("#addConfirm").click(function () {
            var dormName = $("#modal-addDormitaryName").val();
            var province = $("#provinceSel").val();
            var city = $("#citySel").val();
            var dormAddress = $("#modal-addAddress").val();
            var projId = $("#modal-addProject").attr("projectId");
            var emplId = $("#modal-addDormMana").attr("emplId");
            var projName = $("#modal-addProject").val();
            var bedNum = $("#modal-addBedNum").val();
            var rentTime = $("#rentTime2").val();
            var retireTime = $("#retireTime2").val();
            var remarks = $("#modal-addRemarks").val();
            if(common.isEmpty(dormName)){
                alert("请填写宿舍名");
                return false;
            }
            if(common.isEmpty(province)){
                alert("请选择所在省");
                return false;
            }
            if(common.isEmpty(city)){
                alert("请选择所在市");
                return false;
            }
            if(common.isEmpty(projId)){
                alert("请选择所属项目");
                return false;
            }
            if(common.isEmpty(dormAddress)){
                alert("请填写宿舍详细地址");
                return false;
            }
            if(common.isEmpty(bedNum)){
                alert("请填写宿舍床位数");
                return false;
            }

            var url = projectName + "/managerAjax/addDormitary";
            var params = {
                "dormName" : dormName,
                "province" : province,
                "city" : city,
                "dormAddress" : dormAddress,
                "projId" : projId,
                "projName" : projName,
                "bedNum" : bedNum,
                "rentTime" : rentTime,
                "retireTime" : retireTime,
                "remarks" : remarks,
                "emplId" : emplId
            };
            $.post(url, params, function (result) {
                if(result && result.success){
                    alert("新增成功");
                    $("#addDormitary").modal("hide");
                    pc_index.refreshDormList(pc_index.dormName, pc_index.province, pc_index.pageNum);
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
            pc_index.dormName = "";
            pc_index.province = "";
            pc_index.pageNum = 1;
            pc_index.refreshDormList(pc_index.dormName, pc_index.province, pc_index.pageNum);
        });
    }
};

common.addLoadEvent(pc_index.init);

//给地区选框添加选项(initRegion(province,city)传入两个选框的id)
var list1 = new Array;
var list2 = new Array;
list1[list1.length] = "北京";
list1[list1.length] = "天津";
list1[list1.length] = "河北";
list1[list1.length] = "山西";
list1[list1.length] = "内蒙古";
list1[list1.length] = "辽宁";
list1[list1.length] = "吉林";
list1[list1.length] = "黑龙江";
list1[list1.length] = "上海";
list1[list1.length] = "江苏";
list1[list1.length] = "浙江";
list1[list1.length] = "安徽";
list1[list1.length] = "福建";
list1[list1.length] = "江西";
list1[list1.length] = "山东";
list1[list1.length] = "河南";
list1[list1.length] = "湖北";
list1[list1.length] = "湖南";
list1[list1.length] = "广东";
list1[list1.length] = "广西自治区";
list1[list1.length] = "海南";
list1[list1.length] = "重庆";
list1[list1.length] = "四川";
list1[list1.length] = "贵州";
list1[list1.length] = "云南";
list1[list1.length] = "西藏自治区";
list1[list1.length] = "陕西";
list1[list1.length] = "甘肃";
list1[list1.length] = "青海";
list1[list1.length] = "宁夏回族自治区";
list1[list1.length] = "新疆维吾尔自治区";
list1[list1.length] = "香港特别行政区";
list1[list1.length] = "澳门特别行政区";
list1[list1.length] = "台湾省";
list1[list1.length] = "其它";

list2[list2.length] = new Array("东城区", "西城区", "朝阳区", "丰台区", "石景山区", " 海淀区", "门头沟区", "房山区", "通州区", "顺义区", "昌平区", "大兴区", "怀柔区", "平谷区", "密云县", "延庆县", " 其他");
list2[list2.length] = new Array("和平区", "河东区", "河西区", "南开区","河北区", "红桥区", "东丽区", "西青区", "津南区", "北辰区", "武清区", "宝坻区", "滨海新", "宁河县", "蓟县", "静海县", "其他");
list2[list2.length] = new Array("石家庄市", "唐山市", "秦皇岛市", "邯郸市", "邢台市", "保定市", "张家口市", "承德市", "廊坊市", "衡水市","沧州市", "其他");
list2[list2.length] = new Array("太原市", "朔州市", "大同市", "阳泉市", "长治市", "晋城市", "忻州市", "晋中市", "临汾市","吕梁市", "运城市", "其他");
list2[list2.length] = new Array("呼和浩特市", "包头市", "乌海市", "赤峰市", "通辽市", "鄂尔多斯市", "呼伦贝尔市", "乌兰察布市", "巴彦淖尔市", "兴安盟", "阿拉善盟", "锡林郭勒盟", "其他");
list2[list2.length] = new Array("沈阳市", "大连市", "鞍山市", "抚顺市", "本溪市", "丹东市", "锦州市", "营口市", "阜新市", "辽阳市", "盘锦市", "朝阳市", "铁岭市", "葫芦岛市", "其他");
list2[list2.length] = new Array("长春市", "白城市", "吉林市", "四平市", "辽源市", "通化市", "白山市", "松原市", "延边朝鲜族自治州", "其他");
list2[list2.length] = new Array("哈尔滨市", "齐齐哈尔市", "鸡西市", "鹤岗市", "双鸭山市", "大庆市", "伊春市", "佳木斯市", "七台河市", "牡丹江市", "黑河市", "大兴安岭地区(加格达奇)", "绥化市", "其他");
list2[list2.length] = new Array("黄浦区", "徐汇区", "长宁区", "静安区", "普陀区", "虹口区", "杨浦区", "闵行区", "宝山区", "嘉定区", "浦东新区", "金山区", "松江区", "青浦区", "奉贤区", "崇明县", "其他");
list2[list2.length] = new Array("南京市", "徐州市", "连云港市", "宿迁市", "淮安市", "盐城市", "扬州市", "泰州市","南通市", "镇江市", "常州市", "无锡市", "苏州市", "其他");
list2[list2.length] = new Array("杭州市", "湖州市", "嘉兴市", "舟山市", "宁波市", "绍兴市", "衢州市", "金华市", "台州市", "温州市", "丽水市", "其他");
list2[list2.length] = new Array("合肥市", "宿州市", "淮北市", "亳州市", "阜阳市", "蚌埠市", "淮南市", "滁州市", "马鞍山市", "芜湖市", "铜陵市", "安庆市", "黄山市", "六安市", "池州市", "宣城市", "其他");
list2[list2.length] = new Array("福州市", "南平市", "莆田市", "三明市", "泉州市", "厦门市", "漳州市", "龙岩市", "宁德市", "其他");
list2[list2.length] = new Array("南昌市", "九江市", "景德镇市", "鹰潭市", "新余市", "萍乡市", "赣州市", "上饶市","抚州市", "宜春市", "吉安市", "其他");
list2[list2.length] = new Array("济南市", "聊城市", "德州市", "东营市", "淄博市", "潍坊市", "烟台市", "威海市","青岛市", "日照市", "临沂市", "枣庄市", "济宁市", "泰安市", "莱芜市", "滨州市", "菏泽市", "其他");
list2[list2.length] = new Array("郑州市", "三门峡市", "洛阳市", "焦作市", "新乡市", "鹤壁市", "安阳市", "濮阳市","开封市", "商丘市", "许昌市", "漯河市", "平顶山市", "南阳市", "信阳市", "周口市", "驻马店市", "其他");
list2[list2.length] = new Array("武汉市", "十堰市", "襄樊市", "荆门市", "孝感市", "黄冈市", "鄂州市", "黄石市","咸宁市", "荆州市", "宜昌市", "随州市", "恩施土家族苗族自治州", "仙桃市", "天门市", "潜江市", "神农架林区", "其他");
list2[list2.length] = new Array("长沙市", "张家界市", "常德市", "益阳市", "岳阳市", "株洲市", "湘潭市", "衡阳市","郴州市", "永州市", "邵阳市", "怀化市", "娄底市", "湘西土家族苗族自治州", "其他");
list2[list2.length] = new Array("广州市", "清远市", "韶关市", "河源市", "梅州市", "潮州市", "汕头市", "揭阳市","汕尾市", " 惠州市", "东莞市", "深圳市", "珠海市", "中山市", "江门市", "佛山市", "肇庆市", "云浮市","阳江市", "茂名市", "湛江市", "东沙群岛", " 其他");
list2[list2.length] = new Array("南宁市", "桂林市", "柳州市", "梧州市", "贵港市", "玉林市", "钦州市", "北海市","防城港市", "崇左市", "百色市", "河池市", "来宾市", "贺州市", "其他");
list2[list2.length] = new Array("海口市", "三亚市", "三沙市", "五指山市", "琼海市", "儋州市", "文昌市", "万宁市", "东方市", "定安县", "屯昌县", "澄迈县", "临高县", "白沙黎族自治县", "乐东黎族自治县", "陵水黎族自治县", "保亭苗族自治县", "琼中黎族苗族县", "其他");
list2[list2.length] = new Array("渝中区", "大渡口区", "江北区", "沙坪坝区", "九龙坡区", "南岸区", "北碚区","万盛区", "双桥区", "渝北区", "巴南区", "万州区", "涪陵区", "黔江区", "长寿区", "合川市", "永川市", "江津市", "南川市", "綦江县", "潼南县", "铜梁县", "大足县", "璧山县",
    "垫江县", "武隆县", "丰都县", "城口县", "开县", "巫溪县", "巫山县", "奉节县", "云阳县", "忠县", "石柱土家族自治县", "彭水苗族土家族自治县", "酉阳土家族苗族自治县", "秀山土家族苗族自治县", "其他");
list2[list2.length] = new Array("成都市", "广元市", "绵阳市", "德阳市", "南充市", "广安市", "遂宁市", "内江市", "乐山市", "自贡市", "泸州市", "宜宾市", "攀枝花市", "巴中市", "资阳市", "眉山市", "雅安", "阿坝藏族羌族自治州", "甘孜藏族自治州", "凉山彝族自治州县", "其他");
list2[list2.length] = new Array("贵阳市", "六盘水市", "遵义市", "安顺市", "毕节地区", "铜仁地区", "黔东南苗族侗族自治州", "黔南布依族苗族自治州", "黔西南布依族苗族自治州", "其他");
list2[list2.length] = new Array("昆明市", "曲靖市", "玉溪市", "保山市", "昭通市", "丽江市", "普洱市", "临沧市", "宁德市", "德宏傣族景颇族自治州", "怒江傈僳族自治州", "楚雄彝族自治州", "红河哈尼族彝族自治州", "文山壮族苗族自治州", "大理白族自治州", "迪庆藏族自治州", "西双版纳傣族自治州", "其他");
list2[list2.length] = new Array("拉萨市", "那曲地区", "昌都地区", "林芝地区", "山南地区", "日喀则地区", "阿里地区", "其他");
list2[list2.length] = new Array("西安市", "延安市", "铜川市", "渭南市", "咸阳市", "宝鸡市", "汉中市", "安康市", "商洛市", "其他");
list2[list2.length] = new Array("兰州市 ", "嘉峪关市", "金昌市", "白银市", "天水市", "武威市", "酒泉市", "张掖市", "庆阳市", "平凉市", "定西市", "陇南市", "临夏回族自治州", "甘南藏族自治州", "其他");
list2[list2.length] = new Array("西宁市", "海东地区", "海北藏族自治州", "黄南藏族自治州", "玉树藏族自治州", "海南藏族自治州", "果洛藏族自治州", "海西蒙古族藏族自治州", "其他");
list2[list2.length] = new Array("银川市", "石嘴山市", "吴忠市", "固原市", "中卫市", "其他");
list2[list2.length] = new Array("乌鲁木齐市", "克拉玛依市", "喀什地区", "阿克苏地区", "和田地区", "吐鲁番地区", "哈密地区", "塔城地区", "阿勒泰地区", "克孜勒苏柯尔克孜自治州", "博尔塔拉蒙古自治州", "昌吉回族自治州伊犁哈萨克自治州", "巴音郭楞蒙古自治州", "河子市", "阿拉尔市", "五家渠市", "图木舒克市", "其他");
list2[list2.length] = new Array("香港岛", "新界", "九龙", "其他");
list2[list2.length] = new Array("澳门半岛", "离岛", "其他");
list2[list2.length] = new Array("台北市", "高雄市", "台南市", "台中市", "金门县", "南投县", "基隆市", "新竹市", "嘉义市", "新北市", "宜兰县", "新竹县", "桃源县", "苗栗县", "彰化县", "嘉义县", "云林县", "屏东县", "台东县", "花莲县", "澎湖县", "连江县", "其他");

var provinceEle//省选框节点
var cityEle;//市选框节点
function initRegion(province,city){
    if(!document.getElementById)
        return false;
    if(!document.createElement)
        return false;
    if(!document.createTextNode)
        return false;
    provinceEle = document.getElementById(province);
    cityEle = document.getElementById(city);
    for(var i =0;i<list1.length; i++)
    {
        var option = document.createElement("option");
        option.appendChild(document.createTextNode(list1[i]));
        option.value = list1[i];
        option.setAttribute("listnum",i);
        provinceEle.appendChild(option);
    }
    provinceEle.onchange = initCity;
}
function initCity(){
    var selectedIndex = provinceEle.selectedIndex;
    var listNum = provinceEle.options[selectedIndex].getAttribute("listnum");
    var cities = list2[listNum];
    cityEle.innerHTML = "";//清空所有选项
    var option = document.createElement("option");
    option.appendChild(document.createTextNode("请选择"));
    option.value = "";
    cityEle.appendChild(option);
    for(var i =0;i<cities.length; i++)
    {
        option = document.createElement("option");
        option.appendChild(document.createTextNode(cities[i]));
        option.value = cities[i];
        cityEle.appendChild(option);
    }
}