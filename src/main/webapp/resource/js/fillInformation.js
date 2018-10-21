/**
 * Created by R on 2018/6/15.
 */
var fillInfor = {
    init : function () {
        fillInfor.bindClickEventToSubBut();
    },
    bindClickEventToSubBut : function () {
        $("#subBut").click(function () {
            var emplId = $("#emplId").val();
            var fullName = $("#fullName").val();
            var sexSel = $("#sexSel").val();
            var phoneNum = $("#phoneNum").val();
            if(common.isEmpty(emplId) || common.isEmpty(fullName) || common.isEmpty(sexSel) || common.isEmpty(phoneNum)){
                $.toast("请完善所有信息", 1000);
                return false;
            }
            if(phoneNum.length != 11){
                $.toast("请输入正确的手机号", 1000);
                return false;
            }

            var url = projectName + "/ajax/saveUserInfor";
            var params = {
                "emplId" : emplId,
                "fullName" : fullName,
                "sexSel" : sexSel,
                "phoneNum" : phoneNum,
            };
            $.post(url, params, function (result) {
                if(result && result.success){
                    $.toptip(result.data.stateInfor, 500, 'success');
                    setTimeout(function () {
                        window.location.href = projectName + "/view/identification";
                    }, 1000);
                }else{
                    if(result.data.stateInfor)
                        $.toptip(result.data.stateInfor, "error");
                    if(result.error)
                        $.toast("Error:"+result.error, 1000);
                }
            }, "json");
        });
    }
};

common.addLoadEvent(fillInfor.init);