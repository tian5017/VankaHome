var img_url = "";

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
            $("#img-show").show();
        }
    }else{
        $("#img-show").hide();
    }
}

function to_submit(){
    var inputfile = $("#inputfile").val();
    if(img_url=="" || img_url!=inputfile){
        if(inputfile==null || inputfile==""){
            $("#myModalCount").html("请先上传一张照片后在提交！");
            $("#myModal").modal("show");
        }else{
            img_url = inputfile;
            $("#img-form").ajaxSubmit(function(r) {
                if(r == null){
                    $("#myModalCount").html("解析错误，请重新上传一张照片！");
                    $("#myModal").modal("show");
                }else{
                    if(r.faces==null || r.faces.length==0){
                        $("#myModalCount").html("未检测出任何人脸！");
                        $("#myModal").modal("show");
                    }else{
                        var faces = r.faces;
                        if(faces!=null && faces.length>0){
                            $("#sb_div").html("");
                            $("#sb_div").append("<p>共识别出"+faces.length+"张人脸，信息如下：</p>");
                            for(var i=0; i<faces.length; i++){
                                var face = faces[i];
                                $('#sb_div').append('<ul id="face-ul-'+i+'" class="list-group"></ul>');
                                $('#img-show').append('<div class="red-k" id="red-k-'+i+'"></div>');
                                var face_rectangle = face.face_rectangle; //人脸矩形框的位置
                                //画矩形框
                                $("#red-k-"+i).css("width", face_rectangle.width).css("height", face_rectangle.height).css("top", face_rectangle.top).css("left", face_rectangle.left);
                                $("#red-k-"+i).show();
                                $('#face-ul-'+i).html("");
                                var attributes = face.attributes; //人脸属性
                                var gender = attributes.gender.value=="Male" ? "帅哥" : "美女";
                                $('#face-ul-'+i).append('<li class="list-group-item">一位'+gender+'</li>');
                                $('#face-ul-'+i).append('<li class="list-group-item">'+gender+'的年龄是'+attributes.age.value+'岁</li>');
                                var smile = attributes.smile.threshold < attributes.smile.value ? "笑的很开心" : "没有笑容哦";
                                $('#face-ul-'+i).append('<li class="list-group-item">'+gender+smile+'</li>');
                                var glass = "没有戴眼镜";
                                switch (attributes.glass.value){
                                    case "None" : glass = "没有戴眼镜";break;
                                    case "Dark" : glass = "戴了墨镜";break;
                                    case "Normal" : glass = "戴了普通眼镜";break;
                                }
                                $('#face-ul-'+i).append('<li class="list-group-item">'+gender+glass+'</li>');
                                var headpose = attributes.headpose;
                                var zs_str = "";
                                if(headpose.pitch_angle>0){
                                    zs_str += "抬头"+Math.abs(headpose.pitch_angle)+"度";
                                }else{
                                    zs_str += "低头"+Math.abs(headpose.pitch_angle)+"度";
                                }
                                if(headpose.roll_angle>0){
                                    zs_str += "，左转"+Math.abs(headpose.roll_angle)+"度";
                                }else{
                                    zs_str += "，右转"+Math.abs(headpose.pitch_angle)+"度";
                                }
                                $('#face-ul-'+i).append('<li class="list-group-item">'+gender+'的脸部姿势为'+zs_str+'</li>');
                                var qx_str = "";
                                var emotion = attributes.emotion;
                                if(emotion.anger>70){
                                    qx_str += "很愤怒、";
                                }
                                if(emotion.disgust>70){
                                    qx_str += "很厌恶、";
                                }
                                if(emotion.fear>70){
                                    qx_str += "很恐惧、";
                                }
                                if(emotion.happiness>70){
                                    qx_str += "很高兴、";
                                }
                                if(emotion.neutral>70){
                                    qx_str += "很平静、";
                                }
                                if(emotion.sadness>70){
                                    qx_str += "很伤心、";
                                }
                                if(emotion.surprise>70){
                                    qx_str += "很惊讶、";
                                }
                                $('#face-ul-'+i).append('<li class="list-group-item">'+gender+'的情绪'+qx_str.substring(0, qx_str.length-1)+'</li>');
                                var ethnicity = "";
                                switch (attributes.ethnicity.value){
                                    case "Asian" : ethnicity="亚洲人";break;
                                    case "White" : ethnicity="白人";break;
                                    case "Black" : ethnicity="黑人";break;
                                }
                                $('#face-ul-'+i).append('<li class="list-group-item">'+gender+'是'+ethnicity+'</li>');
                            }
                            $("#sb_div").show();
                        }
                    }
                }
            });
        }
    }else{
        $("#myModalCount").html("请换一张照片吧，不要重复提交噢！");
        $("#myModal").modal("show");
    }
}