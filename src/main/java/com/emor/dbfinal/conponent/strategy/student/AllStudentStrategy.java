package com.emor.dbfinal.conponent.strategy.student;

import com.emor.dbfinal.entity.Student;
import com.emor.dbfinal.service.StudentService;
import com.emor.dbfinal.service.TeacherService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AllStudentStrategy implements StudentQueryStrategy ,InitializingBean{
    @Autowired
    TeacherService teacherService;
    @Override
    public PageInfo<Student> query(Integer pageNum) {
        return teacherService.findStudents(pageNum);
    }
    public String toString(){
        return getStrategyInfo();
    }
    @Override
    public String getStrategyInfo() {
        return "查询所有学员";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        StudentStrategyFactory.register("q005",this);
    }
}
