var adminLogin = {
	init : function () {
        $('#username').focus().blur(adminLogin.checkName);
        $('#password').blur(adminLogin.checkPassword);
        $('#submitBut').click(adminLogin.login);
    },
    checkName : function () {
        var name = $('#username').val();
        if(common.isEmpty(name)){
            $('#count-msg').html("用户名不能为空");
            return false;
        }
        $('#count-msg').empty();
        return true;
    },
    checkPassword : function () {
        var password = $('#password').val();
        if(common.isEmpty(password)){
            $('#password-msg').html("密码不能为空");
            return false;
        }
        $('#password-msg').empty();
        return true;
    },
	login : function () {
		var username = $("#username").val();
		var password = $("#password").val();

		var url = projectName + "/managerAjax/login"
		var params = {
			"username" : username,
			"password" : password
		};
		$.post(url, params, function (result) {
			if(result && result.success){
				window.location.href = projectName + "/pcView/index";
			}else{
				if(result.data.stateInfor)
					alert(result.data.stateInfor);
				if(result.error)
                    alert(result.error);
			}
        }, "json");
    }
};

common.addLoadEvent(adminLogin.init);