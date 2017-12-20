function login(account, password) {
    if (!account || account == "" || !password || password == "") {
        alert("请填写账号");
    }
    $.ajax({
        url : "/ss/user/login.do",
        type : "POST",
        data : {
            username : account,
            password : password
        },
        dataType : "json",
        success: function(data) {
            if (data.code == 0) {
                alert("登录成功");
            } else {
                alert(data.msg);
            }
        },
        error : function() {
            alert("登录异常");
        }
    })
}

$("#login-submit").click(function() {
    var account = $("#name").val();
    var password = $("#password").val();
    login(account, password);
})

// 注册用户
function regiser(account, password) {
    $.ajax({
        url : "/ss/user/register.do",
        type : "POST",
        data : {
            name : account,
            password : password
        },
        dataType : "json",
        success: function(data) {
            if (data.code == 0) {
                alert("注册成功");
            } else {
                alert(data.msg);
            }
        },
        error : function() {
            alert("注册异常");
        }
    })
}
$("#register-submit").click(function() {
    var name = $("#reg-name").val();
    var password = $("#reg-password").val();
    var repassword = $("#reg-repassword").val();
    if (password !== repassword) {
        alert("两次密码不相同");
        return ;
    }
    if (name && password) {
        regiser(name, password);
    }
})