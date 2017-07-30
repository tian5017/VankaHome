package com.tianyulin.vankahome.controller;

import com.tianyulin.vankahome.entity.User;
import com.tianyulin.vankahome.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by tianyulin on 2017/5/23.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserRepository userRepository;

    @RequestMapping(value = "/index")
    public String toIndex(Model model, HttpSession session){
        User u = (User) session.getAttribute("LOGIN_USER");
        if(u == null){
            return "login";
        }else{
            model.addAttribute("user", u);
            return "userInfo";
        }
    }

    @RequestMapping(value = "/register")
    public String toRegister(Model model){
        return "register";
    }

    @RequestMapping(value = "/userInfo")
    public String toUserInfo(Model model, HttpSession session){
        User u = (User) session.getAttribute("LOGIN_USER");
        if(u == null){
            return "redirect:/user/index";
        }else{
            model.addAttribute("user", u);
            return "userInfo";
        }
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(User user, HttpSession session){
        String msg;
        if(user!=null){
            User u = userRepository.findUserByName(user.getUserName());
            if(u!=null){
                msg = "chongfu";
            }else{
                user.setUserRole("user");
                userRepository.save(user);
                session.setAttribute("LOGIN_USER", user);
                msg = "success";
            }
        }else{
            msg = "error";
        }
        return msg;
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public String toLogin(User user, HttpSession session){
        String msg;
        if(user!=null){
            String userName = user.getUserName();
            String password = user.getPassword();
            User u = userRepository.findUser(userName, password);
            if(u!=null){
                session.setAttribute("LOGIN_USER", u);
                msg = "success";
            }else{
                msg = "error";
            }
        }else{
            msg = "fail";
        }
        return msg;
    }

    @RequestMapping(value = "/find")
    public String find(Model model){
        List<User> users = userRepository.findAll();
        model.addAttribute("userList", users);
        return "userList";
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String deleteUser(User user, HttpSession session){
        User u = (User) session.getAttribute("LOGIN_USER");
        String msg;
        if(u!=null){
            String userRole = u.getUserRole();
            if(userRole!=null && "admin".equals(userRole)){
                userRepository.delete(user);
                msg = "success";
            }else{
                msg = "error";
            }
        }else{
            msg = "login";
        }
        return msg;
    }
}
