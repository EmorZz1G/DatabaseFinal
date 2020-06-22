package com.emor.dbfinal.conponent.strategy.student;

import com.emor.dbfinal.entity.Student;
import com.emor.dbfinal.service.StudentService;
import com.emor.dbfinal.service.TeacherService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NonBindingTeacherStudentStrategy implements StudentQueryStrategy ,InitializingBean{
    @Autowired
    StudentService studentService;
    @Override
    public PageInfo<Student> query(Integer pageNum) {
        return studentService.findNonTeacherStudents(pageNum);
    }
    public String toString(){
        return getStrategyInfo();
    }
    @Override
    public String getStrategyInfo() {
        return "查询还没有绑定教练的学员";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        StudentStrategyFactory.register("q006",this);
    }
}
