package com.emor.dbfinal.controller;

import com.emor.dbfinal.entity.Exercise;
import com.emor.dbfinal.entity.Student;
import com.emor.dbfinal.entity.User;
import com.emor.dbfinal.exception.ExerciseException;
import com.emor.dbfinal.exception.UserException;
import com.emor.dbfinal.service.ExerciseService;
import com.emor.dbfinal.service.StudentService;
import com.emor.dbfinal.service.TeacherService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
public class ExerciseController {
    @Autowired
    ExerciseService exerciseService;

    @Autowired
    HttpServletRequest request;
    Logger logger = LoggerFactory.getLogger(TeacherController.class);
    final String TEACHER_PREFIX="/tcr";
    final String STUDENT_PREFIX="/stud";
    @Autowired
    private TeacherService teacherService;

    @Autowired
    StudentService studentService;




    @PutMapping(TEACHER_PREFIX+"/exeEdit")
    public String updateExe(Model model){
        String _id = request.getParameter("id");
        Integer id = new Integer(_id);
        String teacherName = request.getParameter("teacherName");
        String studentName = request.getParameter("studentName");
        String exerciseDate = request.getParameter("exerciseDate");
        String _exerciseLength =  request.getParameter("exerciseLength");
        Integer exerciseLength = new Integer(_exerciseLength);
        Boolean present = Boolean.parseBoolean(request.getParameter("present"));
        logger.info("present{}",present);
        try {
            exerciseService.updateExerciseByTSName(id,teacherName,studentName,exerciseDate,exerciseLength,present);
        } catch (UserException e) {
            model.addAttribute("msg",e);
        }
        return "stu/exe";
    }
    @GetMapping(TEACHER_PREFIX+"/exeEdit/{id}")
    public String exeEdit(@PathVariable("id") Integer id,Model model){
        try {
            Exercise exe = exerciseService.findById(id);
            logger.trace("exe{}",exe);
            model.addAttribute("exercise",exe);
            model.addAttribute("msg","查询成功");
        }catch (Exception e){
            logger.trace("exeEdit",e);
            model.addAttribute("msg","没有找到该记录，请重试");
            return "stu/exe";
        }
        return "stu/exeEdit";
    }
    /**
     *
     */
    @ResponseBody
    @GetMapping(TEACHER_PREFIX+"/exeStudent/{id}")
    public Map<String, Object> exeStudent(
            @PathVariable("id") Integer id){
        logger.trace(id+" 查询");
        Student student= teacherService.findStudentById(id);
        logger.trace(student.toString());
        Map<String,Object> map = new HashMap<>();
        map.put("student",student);
        map.put("msg","查询成功！");
        return map;
    }
    @GetMapping(TEACHER_PREFIX+"/students/{pageNum}")//TODO
    public String findStudentByInfo(Student student, @PathVariable("pageNum") Integer pageNum,Model model){
        PageInfo<Student> studentsByExample = teacherService.findStudentsByExample(student, pageNum);
        model.addAttribute("students",studentsByExample);
        logger.trace("studentByExample{}",studentsByExample);
        model.addAttribute("msg","查询成功！");
        return "stu/list";
    }
    @DeleteMapping(TEACHER_PREFIX+"/exe/{id}")
    public String deleteExe(@PathVariable("id") Integer id, Model model){
        logger.trace("删除记录");
        exerciseService.deleteById(id);
        logger.trace("删除了exe id{}"+id);
        model.addAttribute("msg","删除成功！");
        return "redirect:/tcr/exe";
    }
    @GetMapping(TEACHER_PREFIX+"/exe")
    public String toExe(){
        logger.trace("跳转练车管理页面");
        return "stu/exe";
    }
    @GetMapping(TEACHER_PREFIX+"/exeSelect")
    public String exeSelect(@RequestParam("date") String date, Model model){
        User user = (User)request.getSession().getAttribute("loginUser");
        if(user==null){
            model.addAttribute("msg","还没有登录");
            return "/login";
        }
        logger.trace(user.toString());
        List<Exercise> list =  exerciseService.findByDate(user,date);
        model.addAttribute("exe",list);
        return "stu/exe";
    }
    @PostMapping(TEACHER_PREFIX+"/exeDeploy")
    public String exeDeploy(Model model){
        Map<String, String[]> map =  request.getParameterMap();
        String date = "";
        try {
            User user = (User) request.getSession().getAttribute("loginUser");
            date = map.get("date")[0];
            if(date==null){
                model.addAttribute("msg","日期不可以为空！");
                return "stu/exe";
            }
            String[] times = map.get("time");
            String[] lengths = map.get("length");
            logger.trace(date);
            logger.trace(Arrays.asList(times).toString());
            logger.trace(Arrays.asList(lengths).toString());
            if(times.length!=lengths.length){
                throw new Exception("填写有误");
            }
            Integer[] lengthToINT = new Integer[lengths.length];
            for(int i = 0;i<lengths.length;i++){
                lengthToINT[i]=Integer.valueOf(lengths[i]);
            }
            List<String> dates = new ArrayList<>();
            for(int i = 0;i<times.length;i++){
                try {
                    dates.add(date+" "+times[i]);
                }catch (Exception e){
                    model.addAttribute("msg","存储可能有误，请检查");
                    continue;
                }
            }
            exerciseService.addExercise(user,dates,lengthToINT);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            model.addAttribute("msg","出现错误，可能是没有填写日期或填写的时长不正确");
        }
        model.addAttribute("msg","发布成功！日期为"+date);
        return "stu/exe";
    }
}
