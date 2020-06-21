package com.emor.dbfinal.controller;

import com.emor.dbfinal.entity.Examination;
import com.emor.dbfinal.entity.User;
import com.emor.dbfinal.service.ExaminationService;
import com.emor.dbfinal.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class ExaminationController {
    @Autowired
    ExaminationService examinationService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    HttpServletRequest request;
    private User user;

    public void getUser(){
         user = (User)request.getSession().getAttribute("loginUser");
    }
    Logger logger = LoggerFactory.getLogger(ExaminationController.class);
    final String TEACHER_PREFIX="/tcr";
    final String STUDENT_PREFIX="/stud";
    final String redirect_uri = "redirect:/stu/exam";
    @DeleteMapping(TEACHER_PREFIX+"/exam/{id}")//TODO
    public String deleteExam(@PathVariable("id") Integer id,Model model){
        examinationService.deleteExamRecord(id);
        model.addAttribute("msg","删除成功！id："+id);
        logger.info("delete exam id {}", id);
        return "stu/exam";
    }
    @PutMapping(TEACHER_PREFIX+"/exam")
    public String examEdit(Model model, @RequestParam Map<String,String> reqMap){
        try {
            Integer id = Integer.valueOf(reqMap.get("id"));
            String studentName =  reqMap.get("studentName");
            if (studentName.trim().equals("")){
                studentName=null;
            }
            String date = reqMap.get("date");
            Double score = Double.valueOf(reqMap.get("score")!=null?reqMap.get("score"):"");
            logger.info("id {},studentName {},date {},score {}",id,studentName,date,score);
            examinationService.updateExaminationByTSName(id,null,studentName,date,score);
            model.addAttribute("msg","查询成功");
        }catch (Exception e){
            logger.trace("exeEdit",e);
            model.addAttribute("msg","没有找到该记录，请重试");
            return "stu/exam";
        }
        return "redirect:/tcr/exam";
    }
    @GetMapping(TEACHER_PREFIX+"/exam/{id}")
    public String toExamEdit(@PathVariable("id") Integer id,Model model){
        Examination exam = examinationService.findById(id);
        model.addAttribute("exam",exam);
        return "stu/examEdit";
    }
    @GetMapping(TEACHER_PREFIX+"/exam")
    public String toExamPage(){
        System.out.println("转发成功");
        return "stu/exam";
    }
    @PostMapping(TEACHER_PREFIX+"/exam")
    public String deployExam(Model model, @RequestParam("date") String date
                            ,@RequestParam("num") Integer num,@RequestParam("course") String course){
        if(date==null){
            model.addAttribute("msg","日期不可以为空！");
            return "stu/exam";
        }
        getUser();
        int i = examinationService.addExamination(user, date,course, num);
        model.addAttribute("msg","发布成功！日期为"+date+"，数量为"+i+"个");
        return "stu/exam";
    }
    @PostMapping(TEACHER_PREFIX+"/exam/query")
    public String checkExam(@RequestParam("date") String date,Model model){
        getUser();
        List<Examination> exams = examinationService.findByDateForTeacher(user, date);
        model.addAttribute("exams",exams);
        model.addAttribute("msg","查询成功！");
        return "stu/exam";
    }
}
