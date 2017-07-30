function register_submit(){
    var userName = $("input#userName").val();
    var password1 = $("input#password1").val();
    var password2 = $("input#password2").val();
    var phone = $("input#phone").val();
    if(userName==null || userName==""){
        $("#myModalCount").html("请输入用户名！");
        $("#myModal").modal("show");
    }else if(phone==null || phone==""){
        $("#myModalCount").html("请输入手机号码！");
        $("#myModal").modal("show");
    }else if(password1==null || password1==""){
        $("#myModalCount").html("请输入密码！");
        $("#myModal").modal("show");
    }else if(password1!=password2){
        $("#myModalCount").html("两次输入的密码不一致！");
        $("#myModal").modal("show");
    }else{
        if(!(/^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0-9]))\d{8}$/.test(phone))){
            $("#myModalCount").html("请输入正确的手机号码！");
            $("#myModal").modal("show");
        }else{
            $.post(project_url+"/user/save", {
                userName:userName,
                phone:phone,
                password:password1
            }, function(result){
                if("chongfu"==result){
                    $("#myModalCount").html("此用户名已经存在，请重新输入（用户名不能重复）！");
                    $("#myModal").modal("show");
                }
                if("success"==result){
                    $("#myModalCount").html("注册成功！");
                    $("#myModal").modal("show");
                    setTimeout(function(){
                        location.href = project_url + "/article/find";
                    }, 1000);
                }
                if("error"==result){
                    $("#myModalCount").html("注册失败，请稍后重试！");
                    $("#myModal").modal("show");
                }
            });
        }
    }
}