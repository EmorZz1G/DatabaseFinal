package com.emor.dbfinal.conponent.strategy.student;

import com.emor.dbfinal.entity.Student;
import com.emor.dbfinal.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraduatedStudentStrategy implements StudentQueryStrategy ,InitializingBean{
    @Autowired
    StudentService studentService;
    @Override
    public PageInfo<Student> query(Integer pageNum) {
        return studentService.findNonGraduated(pageNum);
    }
    public String toString(){
        return getStrategyInfo();
    }
    @Override
    public String getStrategyInfo() {
        return "查询所有毕业了的学生";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        StudentStrategyFactory.register("003",this);
    }
}
