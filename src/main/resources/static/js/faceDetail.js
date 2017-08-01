$(function(){
    if(imgUrl!=null && imgUrl!=""){
        $("#img-show>img").attr("src", imgUrl);
        $("#temp-img").attr("src", imgUrl);
        $("#img-show").show();
    }else{
        $("#img-show").hide();
    }

    if(faceRequestVo==null || faceRequestVo==""){
        $("#sb_div").html("<p>解析错误，请重新上传一张照片！</p>");
    }else{
        var faceObj = eval("("+faceRequestVo+")");
        var faces = faceObj.faces;
        if(faces==null || faces.length==0){
            $("#sb_div").html("<p>未检测出任何人脸！</p>");
        }else{
            $("#sb_div").html("");
            $("#sb_div").append("<p>共识别出"+faces.length+"张人脸，信息如下：</p>");
            for(var i=0; i<faces.length; i++){
                var face = faces[i];
                $('#sb_div').append('<ul id="face-ul-'+i+'" class="list-group"></ul>');
                $('#img-show').append('<div class="red-k" id="red-k-'+i+'">'+i+'</div>');
                var face_rectangle = face.face_rectangle; //人脸矩形框的位置
                //画矩形框
                hk_face(face_rectangle, i);
                $('#face-ul-'+i).html("");
                var attributes = face.attributes; //人脸属性
                var gender = attributes.gender.value=="Male" ? "帅哥" : "美女";
                $('#face-ul-'+i).append('<li class="list-group-item">'+i+'号是一位'+gender+'</li>');
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
                var qx_str = "";
                var emotion = attributes.emotion;
                if(emotion.anger>50){
                    qx_str += "很愤怒、";
                }
                if(emotion.disgust>50){
                    qx_str += "很厌恶、";
                }
                if(emotion.fear>50){
                    qx_str += "很恐惧、";
                }
                if(emotion.happiness>50){
                    qx_str += "很高兴、";
                }
                if(emotion.neutral>50){
                    qx_str += "很平静、";
                }
                if(emotion.sadness>50){
                    qx_str += "很伤心、";
                }
                if(emotion.surprise>50){
                    qx_str += "很惊讶、";
                }
                if(qx_str==""){
                    qx_str = "隐藏的很深、";
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
});

function hk_face(face_rectangle, i){
    var sW = $("#img-show>img").width();
    var sH = $("#img-show>img").height();
    var mW = $("#temp-img").width();
    var mH = $("#temp-img").height();
    var imgW1 = face_rectangle.width;
    var imgH1 = face_rectangle.height;
    var imgT1 = face_rectangle.top;
    var imgL1 = face_rectangle.left;
    var imgW2 = sW * imgW1 / mW;
    var imgH2 = sH * imgH1 / mH;
    var imgT2 = sH * imgT1 / mH;
    var imgL2 = sW * imgL1 / mW;
    $("#red-k-"+i).css("width", imgW2+"px").css("height", imgH2+"px").css("top", imgT2+"px").css("left", imgL2+"px").css("line-height", imgH2+"px");
    $("#red-k-"+i).show();
}