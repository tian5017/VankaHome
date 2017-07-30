$(function () {
    $.base64.utf8encode = true;

    new Quill('#editor-container', {
        modules: {
            formula: true,
            syntax: true,
            toolbar: '#toolbar-container'
        },
        placeholder: '开始编辑文章内容',
        theme: 'snow'
    });
});

function to_submit(){
    var name = $("#title-name").val();
    if(name==null || name==""){
        alert("请输入文章标题！");
        return;
    }
    var author = $("#title-author").val();
    if(author==null || author==""){
        alert("请输入文章作者！");
        return;
    }
    $("#editor-container>.ql-editor img").css("max-width", "100%");
    var quill = new Quill('#editor-container');
    var counts = quill.getContents();
    var count = "";
    if(counts!=null && counts!=""){
        var data = counts.ops;
        if(data!=null && data.length>0){
            count = $.base64.btoa($("#editor-container>.ql-editor").html(), "utf-8");
        }    }else{
        alert("请输入文章内容！");
        return;
    }
    var userId = $("#user_id").val();
    if(userId!=null && userId!=""){
        $.post(project_url+"/article/save", {
            userId:userId,
            articleName:name,
            articleAuthor:author,
            articleCount:count
        }, function(result){
            if(result=="success"){
                alert("保存成功！");
                location.href = project_url + "/article/find";
            }else{
                alert("保存失败，请重试！");
            }
        });
    }
}
