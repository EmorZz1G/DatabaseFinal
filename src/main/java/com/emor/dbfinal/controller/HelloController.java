package com.emor.dbfinal.controller;


import com.emor.dbfinal.service.HelloService;
import com.emor.dbfinal.service.impl.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.Contended;

import javax.servlet.http.HttpSession;

@Controller
public class HelloController {
    @Autowired
    HelloServiceImpl helloService;
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        helloService.hello();
        return "hello";
    }

}
