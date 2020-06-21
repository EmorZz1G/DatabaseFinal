package com.emor.dbfinal.service.impl;

import com.emor.dbfinal.dao.TeacherMapper;
import com.emor.dbfinal.entity.Teacher;
import com.emor.dbfinal.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    TeacherMapper teacherMapper;

    public List<Teacher> findAllTeachers() {
        return teacherMapper.selectByExample(null);
    }
}
