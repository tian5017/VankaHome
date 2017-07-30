package com.tianyulin.vankahome.controller;

import com.tianyulin.vankahome.entity.Article;
import com.tianyulin.vankahome.entity.User;
import com.tianyulin.vankahome.repository.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by tianyulin on 2017/7/21.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleRepository articleRepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, HttpSession session){
        User u = (User) session.getAttribute("LOGIN_USER");
        if(u!=null){
            model.addAttribute("USER_ID", u.getUserId());
            return "addArticle";
        }else{
            return "redirect:/user/index";
        }
    }

    @RequestMapping(value = "/find")
    public String find(Model model){
        List<Article> articleList = articleRepository.findAll();
        model.addAttribute("articleList", articleList);
        return "listArticle";
    }

    @RequestMapping(value = "/findById")
    public String findById(Long articleId, Model model){
        Article article = articleRepository.findById(articleId);
        model.addAttribute("article", article);
        return "articleDetail";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(Article article, Model model){
        String msg = "";
        if(article!=null){
            if(article.getCreateTime()==null || "".equals(article.getCreateTime())){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(new Date());
                article.setCreateTime(dateString);
            }
            articleRepository.save(article);
            msg = "success";
        }else{
            msg = "error";
        }
        return msg;
    }

}
