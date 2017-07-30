function to_login(){
    var userName = $("input#userName").val();
    var password = $("input#password").val();
    if(userName==null || userName==""){
        $("#myModalCount").html("请输入用户名！");
        $("#myModal").modal("show");
    }else if(password==null || password==""){
        $("#myModalCount").html("请输入密码！");
        $("#myModal").modal("show");
    }else{
        $.post(project_url+"/user/login", {
            userName:userName,
            password:password
        }, function(result){
            if(result=="success"){
                location.href = project_url + "/article/find";
            }
            if(result=="error"){
                $("#myModalCount").html("用户名或密码错误（或您还未注册）！");
                $("#myModal").modal("show");
            }
            if(result=="fail"){
                $("#myModalCount").html("登录失败，请稍后重试！");
                $("#myModal").modal("show");
            }
        });
    }
}

function to_register(){
    location.href = project_url + "/user/register"
}