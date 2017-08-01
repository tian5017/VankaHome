package com.tianyulin.vankahome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tianyulin on 2017/8/1.
 */
@Controller
@RequestMapping("/")
public class WeixinController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String echostr = request.getParameter("echostr");
        if(echostr!=null && !"".equals(echostr)){
            response.getWriter().write(echostr);
            return echostr;
        }else {
            return "null";
        }
    }

}
