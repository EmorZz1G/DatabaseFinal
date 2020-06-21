package com.emor.dbfinal.controller;


import com.emor.dbfinal.entity.Student;
import com.emor.dbfinal.entity.User;
import com.emor.dbfinal.service.CommonService;
import com.emor.dbfinal.service.RegisterService;
import com.emor.dbfinal.service.impl.RegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegisterController {
    @Autowired
    RegisterServiceImpl registerService;
    @Autowired
    CommonService commonService;
    @PostMapping("/doRegister")
    public String doRegister(User user, Model model){
        try {
            registerService.register(user);
        } catch (Exception e) {
            model.addAttribute("msg","用户名已存在");
            return "/register";
        }
        return "/login.html";
    }
    @PostMapping("/setting")
    public String settingStudentInfo(Student student,@RequestParam("tid") Integer id){
        return "/index";
    }
}
