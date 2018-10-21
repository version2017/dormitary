/**
 * Created by R on 2018/6/24.
 */
var dormitaryDetail = {
    picker : undefined,
    init : function () {
       dormitaryDetail.bindClickEventToIcon();
       dormitaryDetail.bindClickEventToButtons();
       dormitaryDetail.isCheckIn();
       dormitaryDetail.isDormitaryManager();
       // dormitaryDetail.bindClickEventToSaveBut();
    },
    bindClickEventToIcon : function () {
        $("#addressDetail").click(function(){
            $.alert($("#addressInput").val(), "详细地址");
        });
        //注：这里是将#checkInDetailInfor中的html内容弹出，所以需弹出后绑定事件，否则是只绑定在#checkInDetailInfor这个隐藏div上
        $("#alreadyCheckIn").click(function(){
            $.alert($("#checkInDetailInfor").html(), "详细信息");
            dormitaryDetail.bindClickEventToKickOutSpan();
        });
        $("#backIcon").click(function(){
            window.location.href = projectName + "/view/dormitaryList?province=" + province;
        });
    },
    bindClickEventToButtons : function () {
        $("#checkIn").click(function () {
            var dormitaryId = dormitaryDetail.getDormitaryIdFromUrl();
            var url = projectName + "/ajax/checkIn";
            var params = {
                "dormitaryId" : dormitaryId
            };
            $.post(url, params, function (result) {
                if(result && result.success){
                    $.toast("入住成功", 300);
                    isCheckIn = true;
                    setTimeout(function () {
                        window.location.reload();
                    }, 1000);
                }else{
                    if(result.data && result.data.stateInfor)
                        $.toptip(result.data.stateInfor, "error");
                    if(result.error)
                        $.toast(result.error, 1000);
                }
            }, "json");
        });
        $("#leave").click(function () {
            var dormitaryId = dormitaryDetail.getDormitaryIdFromUrl();
            var url = projectName + "/ajax/leave";
            var params = {
                "dormitaryId" : dormitaryId
            };
            $.post(url, params, function (result) {
                if(result && result.success){
                    $.toast("退房成功", 300);
                    isCheckIn = false;
                    setTimeout(function () {
                        window.location.reload();
                    }, 1000);
                }else{
                    if(result.data && result.data.stateInfor)
                        $.toptip(result.data.stateInfor, "error");
                    if(result.error)
                        $.toast(result.error, 1000);
                }
            }, "json");
        });
        $("#createQRCode").click(function () {
            var url = projectName + "/ajax/createQRCode";
            var params = {
                "dormitaryId" : dormitaryId,
                "dormitaryName" : dormitaryName
            };
            $.post(url, params, function (result) {
                if(result && result.success){
                    $.toast("生成成功", 300);
                    setTimeout(function () {
                        window.location.href = result.data.codeUri;
                    }, 600);
                }else{
                    if(result.data && result.data.stateInfor)
                        $.toptip(result.data.stateInfor, "error");
                    if(result.error)
                        $.toast(result.error, 1000);
                }
            }, "json");
        });
        $("#saveBut").click(function(){
            var url = projectName + "/ajax/updateDormitary";
            var dormAddress = $("#addressInput").val();
            var projId = $("#projInp").attr("projId");
            var bedNum = $("#bedNumInput").val();
            var occuNum = $("#occuNumInput").val();

            var params = {
                "dormitaryId" : dormitaryId,
                "dormAddress" : dormAddress,
                "projId" : projId,
                "bedNum" : bedNum,
                "occuNum" : occuNum
            };
            $.post(url, params, function (result) {
                if(result && result.success){
                    $.toast("更新成功", 1000);
                    $("#saveBut").css("display", "none");
                    $("#addressInput").val(dormAddress);
                    dormitaryAddress = dormAddress;
                }else{
                    if(result.data && result.data.stateInfor)
                        $.toptip(result.data.stateInfor, "error");
                    if(result.error)
                        $.toast(result.error, 1000);
                }
            }, "json");
        });
        dormitaryDetail.bindClickEventToInviteBut();
    },
    isCheckIn : function () {
        if(isCheckIn){
            $("#checkIn").css("display","none");
            $("#leave").css("display","block");
        }else{
            $("#leave").css("display","none");
            $("#checkIn").css("display","block");
        }
    },
    getDormitaryIdFromUrl : function () {
        var temp = document.URL.split('/');
        var tempLen = temp.length;
        return temp[tempLen-1];
    },
    isDormitaryManager : function(){
        if(isManagerOfDormitary){
            $("#addressInput").removeAttr("disabled");
            $("#bedNumInput").removeAttr("disabled");
            $("#occuNumInput").removeAttr("disabled");
            $("#addressInput").click(function () {
                $("#addressInput").focus();
                $("#saveBut").css("display", "block");
            });
            $("#bedNumInput").click(function () {
                $("#bedNumInput").focus();
                $("#saveBut").css("display", "block");
            });
            $("#occuNumInput").click(function () {
                $("#occuNumInput").focus();
                $("#saveBut").css("display", "block");
            });
            $(".xSpan").css("display", "inline");
            $("#inviteBut").css("display", "block");
            dormitaryDetail.bindClickEventToProjChoose();
        }else{
            $("#projInp").attr("disabled", "disabled");
        }
    },
    bindClickEventToProjChoose : function () {
        $("#projInp").click(function () {
            $("#saveBut").css("display", "block");
            $("#projInp").trigger("blur");
            //模态框打开之后执行该方法
            var onOpen = function(){
                $("#searchBut").click(function(){
                    var url = projectName + "/managerAjax/getProjectsByKeyWord";
                    var keyWord = $("#projName").val();
                    if(common.isEmpty(keyWord)){
                        $.toast("请输入关键词", 1000);
                        return false;
                    }

                    var params = {
                        "keyWord" : keyWord
                    };
                    $.ajax({
                        type : "post",
                        url : url,
                        data : params,
                        dataType : "json",
                        success : function(result){
                            if(result && result.success){
                                var allProjects = result.data;
                                if(allProjects.length == 0){
                                    $.toast("未找到包含该关键词的项目", 1000);
                                    return false;
                                }

                                dormitaryDetail.picker = new mui.PopPicker();
                                var options = new Array();
                                for(var i=0;i<allProjects.length;i++){
                                    var project = allProjects[i];
                                    var option = {};
                                    option.text = project.projName;
                                    option.value = project.projId;
                                    options.push(option);
                                }
                                dormitaryDetail.picker.setData(options);
                                //回调函数是在选中元素后点击确定的时候执行的
                                dormitaryDetail.picker.show(function (selectItems) {
                                    onConfirm(selectItems[0].text, selectItems[0].value);
                                })
                            }else{
                                if(result.data && result.data.stateInfor)
                                    $.toptip(result.data.stateInfor, "error");
                                if(result.error)
                                    $.toast(result.error, 1000);
                            }
                        }
                    });
                });
                $("#projName").focus(function () {
                    if(undefined != dormitaryDetail.picker){
                        dormitaryDetail.picker.hide();
                    }
                });
            };
            var onConfirm = function (text, val) {
                $("#projInp").attr("projId", val);
                $("#projInp").val(text);
                $.closeModal();
            };
            var onCancel = function () {
                $("#saveBut").css("display", "none");
                $.closeModal();
            };

            $.modal({
                title: "选择项目",
                text:   '<input type="text" class="weui-input weui-prompt-input" id="projName" placeholder="请输入项目名关键词" />' +
                        '<a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary" id="searchBut">搜索</a>',
                buttons: [
                    { text: "取消", className: "default", onClick: function(){ onCancel(); } },
                ],
                autoClose : false
            }, onOpen);
        });
    },
    bindClickEventToKickOutSpan : function () {
        $(".kickOutBut").click(function () {
            var emplId = $(this).attr("id");
            var persName = $(this).parent().prev().prev().html();
            $.confirm({
                title: '确认移出',
                text: '确定要将' + persName + '移出当前宿舍吗？',
                onOK: function () {
                    //点击确认
                    var url = projectName + "/ajax/kickOut";

                    var params = {
                        "emplId" : emplId,
                        "dormitaryId" : dormitaryId
                    };
                    $.post(url, params, function (result) {
                        if(result && result.success){
                            window.location.reload();
                        }else{
                            if(result.data && result.data.stateInfor)
                                $.toptip(result.data.stateInfor, "error");
                            if(result.error)
                                $.toast(result.error, 1000);
                        }
                    }, "json");
                },
                onCancel: function () {

                }
            });
        });
    },
    bindClickEventToInviteBut : function () {
        $("#inviteBut").click(function(){
            //模态框打开之后执行该方法
            var onOpen = function(){
                $("#searchBut2").click(function(){
                    var url = projectName + "/ajax/getPersonsByNameKeyWord";
                    var keyWord = $("#persName").val();
                    if(common.isEmpty(keyWord)){
                        $.toast("请输入被邀请人的姓名", 1000);
                        return false;
                    }

                    var params = {
                        "keyWord" : keyWord
                    };
                    $.ajax({
                        type : "post",
                        url : url,
                        data : params,
                        dataType : "json",
                        success : function(result){
                            if(result && result.success){
                                var persons = result.data;
                                if(persons.length == 0){
                                    $.toast("未找到该员工", 1000);
                                    return false;
                                }

                                dormitaryDetail.picker = new mui.PopPicker();
                                var options = new Array();
                                for(var i=0;i<persons.length;i++){
                                    var person = persons[i];
                                    var option = {};
                                    option.text = person.name;
                                    option.value = person.emplId;
                                    options.push(option);
                                }
                                dormitaryDetail.picker.setData(options);
                                //回调函数是在选择后点击确定的时候执行的
                                dormitaryDetail.picker.show(function(selectItems){
                                    onConfirm(selectItems[0].text, selectItems[0].value);
                                });
                            }else{
                                if(result.data && result.data.stateInfor)
                                    $.toptip(result.data.stateInfor, "error");
                                if(result.error)
                                    $.toast(result.error, 1000);
                            }
                        }
                    });
                });
                $("#persName").focus(function () {
                    if(undefined != dormitaryDetail.picker){
                        dormitaryDetail.picker.hide();
                    }
                });
            };
            var onConfirm = function (text, val) {
                var url = projectName + "/ajax/invite";
                var params = {
                    "dormitaryId" : dormitaryId,
                    "emplId" : val
                };

                $.post(url, params, function (result) {
                    if(result && result.success){
                        $.closeModal();
                        window.location.reload();
                    }else{
                        if(result.data && result.data.stateInfor)
                            $.toptip(result.data.stateInfor, "error");
                        if(result.error)
                            $.toast(result.error, 1000);
                    }
                }, "json");
            };
            var onCancel = function () {
                $.closeModal();
            };

            $.modal({
                title: "邀请入住",
                text:   '<input type="text" class="weui-input weui-prompt-input" id="persName" placeholder="请输入姓名关键字" />' +
                '<a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary" id="searchBut2">搜索</a>',
                buttons: [
                    { text: "取消", className: "default", onClick: function(){ onCancel(); } },
                ],
                autoClose : false
            }, onOpen);
        });
    }
};

common.addLoadEvent(dormitaryDetail.init);