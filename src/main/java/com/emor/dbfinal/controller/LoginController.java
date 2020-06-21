package com.emor.dbfinal.controller;

import com.emor.dbfinal.entity.User;
import com.emor.dbfinal.service.impl.LoginServiceImpl;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    LoginServiceImpl loginService;
    @Autowired
    HttpServletRequest request;


    Logger logger = LoggerFactory.getLogger(LoginController.class);
    @PostMapping("/login")
    public String doLogin(User user, Model model){
        System.out.println("有用户准备登录");
        Map<String, Object> resMap=new HashMap<>();
        if(loginService.login(user,resMap)){
            System.out.println("-----------");
            logger.trace(resMap.get("user")!=null?resMap.get("user").toString():"");
            request.getSession().setAttribute("loginUser",resMap.get("user"));
            return "redirect:/index";
        }else {
            logger.trace(resMap.get("user")!=null?resMap.get("user").toString():"");
            model.addAttribute("msg",resMap.get("msg"));
            return "/login";
        }
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

}