package com.emor.dbfinal.service.impl;

import com.emor.dbfinal.dao.StudentMapper;
import com.emor.dbfinal.dao.TeacherMapper;
import com.emor.dbfinal.entity.Student;

import com.emor.dbfinal.entity.StudentExample;
import com.emor.dbfinal.entity.Teacher;
import com.emor.dbfinal.service.TeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.util.List;

@Service
@CacheConfig(cacheNames = "student")
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TeacherMapper teacherMapper;
    @Override
    /**
     * 通过教练ID找教练
     */
    public Teacher findTeacherById(Integer id) {
        return teacherMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Student> findStudentsByExample(Student student,Integer pageNum) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(student.getEnterDate())){
            criteria.andEnterDateLike("%"+student.getEnterDate()+"%");
        }
        if(!StringUtils.isEmpty(student.getName())){
            criteria.andNameLike("%"+student.getName()+"%");
        }
        if(!StringUtils.isEmpty(student.getPhone())){
            criteria.andPhoneLike("%"+student.getPhone()+"%");
        }
        if(!StringUtils.isEmpty(student.getSex())){
            criteria.andSexEqualTo(student.getSex());
        }
        PageHelper.startPage(pageNum,10);
        List<Student> students = studentMapper.selectByExample(example);
        return new PageInfo<>(students);
    }

    @Override
    public PageInfo<Student> findAllVIPStudent(Integer pageNum) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andVipEqualTo("1");
        PageHelper.startPage(pageNum,10);
        List<Student> students = studentMapper.selectByExample(example);
        return new PageInfo<>(students);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        return teacherMapper.insertSelective(teacher);
    }

    @Override
    public PageInfo<Student> findStudents(Integer PageNum) {
        PageHelper.startPage(PageNum,10);
        List<Student> students = studentMapper.selectByExample(null);
        PageInfo<Student> studentPageInfo = new PageInfo<>(students);
        return studentPageInfo;
    }

    @Override
    public List<Student> findStudentsByTeacherId(Integer tid) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andTidEqualTo(tid);
        return studentMapper.selectByExample(example);
    }

    @Override
    @Cacheable(key = "#id")
    /**
     * 通过学生ID查找学生
     */
    public Student findStudentById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    @CachePut(key = "#result.id")
    public Student addStudent(Student student) {
        studentMapper.insertSelective(student);
        return student;
    }

    @Override
   @CacheEvict(key = "#student.id")
    public Student updateStudent(Student student) {
        studentMapper.updateByPrimaryKeySelective(student);
        System.out.println(student+"updateStudent");
        return student;
    }

    @Override
    @CacheEvict(key = "#id")
    public int deleteStudent(Integer id) {
        return studentMapper.deleteByPrimaryKey(id);
    }

}
