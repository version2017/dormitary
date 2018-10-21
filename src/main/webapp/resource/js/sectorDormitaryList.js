/**
 * Created by R on 2018/6/24.
 */
var dormitaryList = {
    init : function () {
        dormitaryList.initSectorDormitaryList();
    },
    initSectorDormitaryList : function () {
        var url = projectName + "/ajax/getSectorDormitaryList";
        var params = {};
        $.post(url, params, function (result) {
            if(result && result.success){
                var dormitaries = result.data;
                $("#sectorDormitaryList").empty();
                var htmlCode = "";
                for(var i=0;i<dormitaries.length;i++){
                    htmlCode += '<li class="mui-table-view-cell mui-media dormitaryLi" id="' + dormitaries[i].DORM_ID + '">';
                    htmlCode += '   <a href="javascript:;">';
                    htmlCode += '       <img class="mui-media-object mui-pull-left dormitaryIcon" src="' + projectName +'/resource/image/dormitary.jpg"/>';
                    htmlCode += '       <div class="mui-media-body dormitaryInfo">';
                    htmlCode += '           <p class="dormitaryName">' + dormitaries[i].DORM_NAME + '</p>';
                    htmlCode += '           <p>该宿舍共有' + dormitaries[i].BED_NUM + '个床位，已入住' + dormitaries[i].OCCU_NUM + '人</p>';
                    if(dormitaries[i].NAME != undefined){
                        htmlCode += '           <span>宿舍管理员：' + dormitaries[i].NAME + '</span>';
                    }else{
                        htmlCode += '           <span>宿舍管理员：</span>';
                    }
                    htmlCode += '           <span>';
                    htmlCode += '               <img src="' + projectName + '/resource/image/phone.png" class="phoneIcon" />';
                    if(dormitaries[i].PHON_NUM != undefined){
                        htmlCode += '               <span>' + dormitaries[i].PHON_NUM + '</span>';
                    }else{
                        htmlCode += '               <span></span>';
                    }
                    htmlCode += '           </span>';
                    htmlCode += '       </div>';
                    htmlCode += '   </a>';
                    htmlCode += '</li>';
                }
                $("#sectorDormitaryList").append(htmlCode);
                dormitaryList.bindClickEventToLi();
            }else{
                if(result.data && result.data.stateInfor)
                    $.toptip(result.data.stateInfor, "error");
                if(result.error)
                    $.toast("Error:"+result.error, 1000);
            }
        }, "json");
    },
    bindClickEventToLi : function () {
        $(".dormitaryLi").click(function(){
            var dormitaryId = $(this).attr("id");
            window.location.href = projectName + "/view/dormitaryDetail/" + dormitaryId;
        });
    }
};

common.addLoadEvent(dormitaryList.init);