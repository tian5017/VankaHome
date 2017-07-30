$(function(){
    $.base64.utf8encode = true;

    if(article!=null){
        $("#article-count").html("");
        var count = $.base64.atob(article.articleCount, "utf-8");
        $("#article-count").html(count);
    }
});