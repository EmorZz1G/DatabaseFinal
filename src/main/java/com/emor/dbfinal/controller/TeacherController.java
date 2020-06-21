package com.emor.dbfinal.controller;


import com.emor.dbfinal.conponent.strategy.student.StudentQueryStrategy;
import com.emor.dbfinal.conponent.strategy.student.StudentStrategyFactory;
import com.emor.dbfinal.entity.Student;
import com.emor.dbfinal.entity.Teacher;
import com.emor.dbfinal.entity.User;
import com.emor.dbfinal.service.TeacherService;
import com.github.pagehelper.PageInfo;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@Slf4j
public class TeacherController {
    final String TEACHER_PREFIX ="/tcr";
    @Autowired
    TeacherService teacherService;

    @Autowired
    HttpServletRequest request;

    Logger logger = LoggerFactory.getLogger(TeacherController.class);
//    @ResponseBody

    User user = null;
    @GetMapping(TEACHER_PREFIX +"/student")
    public String toAddPage(){
        logger.trace("转发到添加页面");
        return "stu/add";
    }
    @ResponseBody
    @GetMapping(TEACHER_PREFIX +"/teacher")
    public Teacher getTeacherInfo(){
        getUser();
        Teacher teacherById = teacherService.findTeacherById(user.getFid());
        logger.trace("teacherById {}",teacherById);
        return teacherById;
    }

    private void getUser() {
        user = (User) request.getSession().getAttribute("loginUser");
    }

    @GetMapping(TEACHER_PREFIX +"/student/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        findStudentAndGoPage(id, model);
        return "stu/add";
    }

    private void findStudentAndGoPage(@PathVariable("id") Integer id, Model model) {
        logger.trace(id+" 查询");
        Student student= teacherService.findStudentById(id);
        logger.trace("查询结果student{}",student);
        model.addAttribute("student", student);
        model.addAttribute("msg","查询成功！");
    }

    @GetMapping(TEACHER_PREFIX +"/admin/student/{id}")
    public String toAdminEditPage(@PathVariable("id") Integer id,Model model){
        findStudentAndGoPage(id, model);
        return "stu/admin_add";
    }

    @GetMapping(TEACHER_PREFIX +"/admin/student")
    public String toAdminEditPage(){
        return "stu/admin_add";
    }
    @PostMapping(TEACHER_PREFIX +"/student")
    public String addStudent(Student student){
        logger.trace("student{} 准备添加",student);
        teacherService.addStudent(student);
        logger.trace("修改成功");
        return "redirect:"+ TEACHER_PREFIX +"/students/1";
    }

    @PutMapping(TEACHER_PREFIX +"/student")
    public String updateStudent(Student student,Model model){
        commonUpdateStudent(student, model);
        return "redirect:"+TEACHER_PREFIX+"/students";
    }

    private void commonUpdateStudent(Student student, Model model) {
        logger.trace("student {}准备修改",student);
        teacherService.updateStudent(student);
        model.addAttribute("msg","修改成功！");
        logger.trace("修改成功");
    }

    @PutMapping(TEACHER_PREFIX +"/admin/student")
    public String updateStudentForAdmin(Student student,Model model){
        commonUpdateStudent(student, model);
        return "stu/admin_list";
    }
    @DeleteMapping(TEACHER_PREFIX +"/student/{id}")
    public String deleteStudent(@PathVariable("id") Integer id,Model model){
        logger.trace("id "+id+" 的学生 准备删除");
        teacherService.deleteStudent(id);
        logger.trace("删除成功");

        return "redirect:"+ TEACHER_PREFIX +"/students/1";
    }
    //@GetMapping(TEACHER_PREFIX +"/students/{pageNum}")
    @Deprecated//Exe Controller 取代
    public String students(@PathVariable("pageNum") Integer pageNum, Model model){
        HashMap<String,Object> resMap = new HashMap<>();
        logger.trace(request.getRemoteAddr()+request.getRemoteUser()+" 查询了所有学生");
        PageInfo<Student> students = teacherService.findStudents(pageNum);
        logger.trace(students.toString());
        model.addAttribute("students",students);
        logger.info(resMap.toString());
        return "stu/list";
    }

    @GetMapping(TEACHER_PREFIX+"/admin_list")
    public String toAdminPage(){
        return "stu/admin_list";
    }
    @PostMapping(TEACHER_PREFIX+"/students/query")
    public String superQuery(@RequestParam("qid") String qid,@RequestParam("pageNum") Integer pageNum,Model model){
        logger.info("qid {}, pageNum {}",qid,pageNum);

        StudentQueryStrategy strategy = StudentStrategyFactory.getStrategy(qid);
        System.out.println(strategy);
        System.out.println(strategy.getStrategyInfo());
        PageInfo<Student> query = strategy.query(pageNum);
        model.addAttribute("students",query);
        model.addAttribute("qid",qid);
        return "stu/admin_list";
    }

    @ResponseBody
    @GetMapping("/tcr/students/queries")
    public HashMap<String, StudentQueryStrategy> getQueries(){
        return StudentStrategyFactory.getQueries();
    }
}