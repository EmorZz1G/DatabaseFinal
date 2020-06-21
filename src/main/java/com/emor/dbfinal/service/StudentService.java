package com.emor.dbfinal.service;

import com.emor.dbfinal.entity.Exercise;
import com.emor.dbfinal.entity.Student;
import com.emor.dbfinal.entity.User;
import com.emor.dbfinal.exception.ExaminationException;
import com.emor.dbfinal.exception.ExerciseException;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;



public interface StudentService {
    List<Exercise> findExercisesByDate(User user, String date);

    int reserveExercise(User user, Integer id) throws ExerciseException;

    int reserveExamination(User user, Integer id)throws ExaminationException;

    Student findStudentById(Integer id);

    int updateStudent(Student student);

    PageInfo<Student> findNonGraduated(Integer pageNum);
    PageInfo<Student> findNonBinding(Integer pageNum);
}
