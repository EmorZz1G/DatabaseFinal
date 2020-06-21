package com.emor.dbfinal.controller;


import com.emor.dbfinal.entity.Examination;
import com.emor.dbfinal.entity.Exercise;
import com.emor.dbfinal.entity.User;
import com.emor.dbfinal.exception.ExaminationException;
import com.emor.dbfinal.exception.ExerciseException;
import com.emor.dbfinal.service.ExaminationService;
import com.emor.dbfinal.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StudentController {
    final String STUDENT_PREFIX="/stud";

    Logger logger = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    StudentService studentService;
    @Autowired
    HttpServletRequest request;

    @Autowired
    ExaminationService examinationService;
    User user = null;
    void getUser(){
        user = (User) request.getSession().getAttribute("loginUser");
    }
    @PutMapping(STUDENT_PREFIX+"/reserve/{id}")
    public String reserveExe(@PathVariable("id") Integer id, Model model){
        getUser();
        try {
            studentService.reserveExercise(user,id);
        } catch (ExerciseException e) {
            model.addAttribute("msg",e.getMessage());
            return "stu/rexe";
        }
        model.addAttribute("msg","预约成功！");
        return "stu/rexe";
    }
    @PutMapping(STUDENT_PREFIX+"/exam/reserve/{id}")
    public String reserveExam(@PathVariable("id") Integer id,Model model){
        getUser();
        logger.info("预约的考试ID {}",id);
        try {
            studentService.reserveExamination(user,id);
        } catch (ExaminationException e) {
            model.addAttribute("msg",e.getMessage());
            return "stu/rexam";
        }
        model.addAttribute("msg","预约成功！");
        return "stu/rexam";
    }
    @GetMapping(STUDENT_PREFIX+"/exe")
    public String toReservePage(){
        return "stu/rexe";
    }
    @GetMapping(STUDENT_PREFIX+"/exams")
    public String findExam(@RequestParam("date") String date,Model model){
        getUser();
        List<Examination> exams = examinationService.findByDateForStudent(user, date);
        logger.info("exams{}", exams);
        model.addAttribute("exams",exams);
        return "stu/rexam";
    }
    @GetMapping(STUDENT_PREFIX+"/exam")
    public String toExamPage(){
        return "stu/rexam";
    }
    @GetMapping(STUDENT_PREFIX+"/reserve")
    public String findExe(@RequestParam("date") String date, Model model){
        getUser();
        List<Exercise> exercisesByDate = studentService.findExercisesByDate(user, date);
        logger.trace("exe list{}",exercisesByDate);
        model.addAttribute("exe",exercisesByDate);
        model.addAttribute("msg","查询成功");
        return "stu/rexe";
    }

}
