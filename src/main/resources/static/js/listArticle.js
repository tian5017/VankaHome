function to_detail(articleId){
    if(articleId!=null && articleId!=""){
        location.href = project_url + "/article/findById?articleId="+articleId;
    }
}