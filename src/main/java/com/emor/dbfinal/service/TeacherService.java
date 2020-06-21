package com.emor.dbfinal.service;

import com.emor.dbfinal.entity.Student;
import com.emor.dbfinal.entity.Teacher;
import com.github.pagehelper.PageInfo;
import org.springframework.data.relational.core.sql.In;

import javax.swing.*;
import java.util.List;


public interface TeacherService {
    int updateTeacher(Teacher teacher);
    PageInfo<Student> findStudents(Integer pageNum);
    List<Student> findStudentsByTeacherId(Integer tid);
    PageInfo<Student> findStudentsByExample(Student student,Integer pageNum);
    PageInfo<Student> findAllVIPStudent(Integer pageNum);
    Student findStudentById(Integer id);

    /**
     * 通过教练ID找教练
     * @param id
     * @return
     */
    Teacher findTeacherById(Integer id);
    Student addStudent(Student student);

    Student updateStudent(Student student);

    int deleteStudent(Integer id);
}
