function show_img(files){
    $(".red-k").hide();
    $("#sb_div").hide();
    var tmpFile = files[0];
    if(tmpFile!=null && tmpFile!=""){
        var reader = new FileReader();
        reader.readAsDataURL(tmpFile);
        reader.onload = function (e) {
            var url = e.target.result;
            $("#img-show>img").attr("src", url);
            $("#imgBase64").val(url);
            $("#img-show").show();
        }
    }else{
        $("#img-show").hide();
    }
}

function to_submit(){
    var inputfile = $("#inputfile").val();
    if (inputfile == null || inputfile == "") {
        $("#myModalCount").html("请先上传一张照片后在提交！");
        $("#myModal").modal("show");
    }else{
        $("#img-form").attr("action", project_url+"/face/sb_face").submit();
    }
}