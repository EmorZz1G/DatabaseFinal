    package com.emor.dbfinal.controller;


import com.emor.dbfinal.entity.Student;
import com.emor.dbfinal.entity.Teacher;
import com.emor.dbfinal.entity.User;
import com.emor.dbfinal.service.CommonService;
import com.emor.dbfinal.service.UserService;
import com.emor.dbfinal.service.StudentService;
import com.emor.dbfinal.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    @Controller
public class CommonController {
    static Logger log = LoggerFactory.getLogger(CommonController.class);
    @Autowired
    CommonService commonService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    UserService userService;
    User user;
    public void getUser(){
        user = (User) request.getSession().getAttribute("loginUser");
    }
    @ResponseBody
    @GetMapping("/teachers")
    public List<Teacher> getTeachers(){
        return commonService.findAllTeachers();
    }
    @ResponseBody
    @GetMapping("/user")
    public Map<String,Object> getUserInfo(){
        User user = (User) request.getSession().getAttribute("loginUser");
        Map<String,Object> map = new HashMap<>();
        if(user!=null){
            map.put("user",user);
            if(user.getFid()!=null){
                Student studentById = studentService.findStudentById(user.getFid());
                map.put("student",studentById);
            }
        }
        return map;
    }
    @GetMapping("/index")
    public  String toIndex(Model model){
        getUser();
        if(user!=null){
            model.addAttribute("user",user);
        }
        return "/index";
    }
    @PutMapping("/user/student")
    @Transactional
    public String updateUserAndStu(Student student ,Model model){
        log.info("updateStudent {}", student);
        studentService.updateStudent(student);
        getUser();
        user.setFid(student.getId());
        userService.updateUser(user);
        model.addAttribute("msg","修改成功！");
        return "/index";
    }
    @PutMapping("/user/teacher")
    @Transactional
    public String updateUserAndTcr(Teacher teacher ,Model model){
        log.info("updateTeacher {}", teacher);
        teacherService.updateTeacher(teacher);
        user.setFid(teacher.getId());
        userService.updateUser(user);
        model.addAttribute("msg","修改成功！");
        return "/index";
    }
    @ResponseBody
    @GetMapping("/tcr/{tid}/student")
    public List<Student> getTeachersStudent(@PathVariable("tid") Integer tid){
        return teacherService.findStudentsByTeacherId(tid);
    }
    @GetMapping("/index/bind")
    public String toBingPage(){
        return "stu/binding";
    }

}
