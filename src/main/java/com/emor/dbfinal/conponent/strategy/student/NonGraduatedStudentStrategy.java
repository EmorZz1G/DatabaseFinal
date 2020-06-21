package com.emor.dbfinal.conponent.strategy.student;

import com.emor.dbfinal.dao.StudentMapper;
import com.emor.dbfinal.entity.Student;
import com.emor.dbfinal.entity.StudentExample;
import com.emor.dbfinal.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.sound.midi.Soundbank;
import java.util.List;

@Component
public class NonGraduatedStudentStrategy implements StudentQueryStrategy ,InitializingBean{
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
        return "查询所有没有毕业的学生";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        StudentStrategyFactory.register("001",this);
    }
}
