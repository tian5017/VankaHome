/**
 * Created by tianyulin on 2017/6/2.
 */
var project_url_str = $("#project_url").val();
var project_url = project_url_str.substring(0, project_url_str.length-1);

function Map() {
    this.elements = new Array();
    this.put = function(_key, _value) {
        this.elements.push( {
            key : _key,
            value : _value
        });
    }
    this.get = function(_key) {
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    return this.elements[i].value;
                }
            }
        } catch (e) {
            return null;
        }
    }
    this.isEmpty = function() {
        return (this.elements.length < 1);
    }
    this.clear = function() {
        this.elements = new Array();
    }
}

function to_article_main(){
    location.href = project_url + "/user/userInfo";
}

function to_article_list(){
    location.href = project_url + "/article/find";
}

function to_article_add(){
    location.href = project_url + "/article/index";
}

function to_article_face(){
    location.href = project_url + "/face/index";
}
