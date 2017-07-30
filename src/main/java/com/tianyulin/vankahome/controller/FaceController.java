package com.tianyulin.vankahome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;

/**
 * Created by tianyulin on 2017/7/27.
 */
@Controller
@RequestMapping("/face")
public class FaceController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, HttpSession session){
        return "face";
    }

}
