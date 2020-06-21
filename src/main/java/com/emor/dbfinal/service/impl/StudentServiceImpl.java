package com.emor.dbfinal.service.impl;


import com.emor.dbfinal.dao.ExaminationMapper;
import com.emor.dbfinal.dao.ExerciseMapper;
import com.emor.dbfinal.dao.StudentMapper;
import com.emor.dbfinal.dao.UserMapper;
import com.emor.dbfinal.entity.*;
import com.emor.dbfinal.exception.ExaminationException;
import com.emor.dbfinal.exception.ExerciseException;
import com.emor.dbfinal.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ExerciseMapper exerciseMapper;
    @Autowired
    ExaminationMapper examinationMapper;
    Student student;
    Teacher teacher;
    public void initExe(){
        exerciseExample.clear();
        criteriaForExe = exerciseExample.createCriteria();
    }
    public void initExam(){
        examinationExample.clear();
        criteriaForExam = examinationExample.createCriteria();
    }
    void findStudentAndTeacherByUser(User user){
        student = userMapper.findStudentByFid(user.getFid());
        teacher = studentMapper.findTeacherByStudentTid(student.getTid());
    }
    private static ExerciseExample exerciseExample =new ExerciseExample();
    private static  ExerciseExample.Criteria criteriaForExe;
    private static ExaminationExample examinationExample =new ExaminationExample();
    private static  ExaminationExample.Criteria criteriaForExam;
    @Override
    @Transactional
    public int reserveExamination(User user, Integer id) throws ExaminationException {
        initExam();
        criteriaForExam.andIdEqualTo(id);
        criteriaForExam.andSidIsNull();
        List<Examination> examinations = examinationMapper.selectByExample(examinationExample);
        logger.info("exams{}",examinations);
        Examination examination;
        if (examinations!=null&&examinations.size()>0&&(examination=examinations.get(0))!=null){
            logger.info("exam{}", examination);
            if(examination.getSid()!=null){
                throw new ExaminationException("已经被预约");
            }else {
                examination.setSid(user.getFid());
                return examinationMapper.updateByPrimaryKeySelective(examination);
            }
        }
        return 0;
    }

    @Override
    public Student findStudentById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateStudent(Student student) {
        return studentMapper.insertSelective(student);
    }

    @Override
    public PageInfo<Student> findNonGraduated(Integer pageNum) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andGraduatedEqualTo(false);
        PageHelper.startPage(pageNum,10);
        System.out.println(studentMapper);
        List<Student> students = studentMapper.selectByExample(example);
        return new PageInfo<>(students);
    }

    @Override
    public PageInfo<Student> findNonBinding(Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Student> students = studentMapper.findNonBindingStudents();
        return new PageInfo<>(students);
    }

    @Override
    @Transactional
    public int reserveExercise(User user, Integer id) throws ExerciseException{
        initExe();
        criteriaForExe.andIdEqualTo(id);
        criteriaForExe.andSidIsNull();
        List<Exercise> exercises = exerciseMapper.selectByExample(exerciseExample);
        Exercise exercise;
        logger.info("exams{}",exercises);
        if (exercises!=null&&exercises.size()>0&&(exercise=exercises.get(0))!=null){
            logger.info("exam{}", exercise);
            if(exercise.getSid()!=null){
                throw new ExerciseException("已经被预约");
            }else {
                exercise.setSid(user.getFid());
                return exerciseMapper.updateByPrimaryKeySelective(exercise);
            }
        }

        return 0;
//        findStudentAndTeacherByUser(user);
//        initExe();
//        criteriaForExe.andTidEqualTo(teacher.getId());
//        criteriaForExe.andIdEqualTo(id);
//        exercise = new Exercise();
//        exercise.setSid(user.getFid());
//        return exerciseMapper.updateByExampleSelective(exercise,exerciseExample);
    }

    @Override
    public List<Exercise> findExercisesByDate(User user, String date) {
        findStudentAndTeacherByUser(user);
        initExe();
        criteriaForExe.andTidEqualTo(teacher.getId());
        criteriaForExe.andExerciseDateLike("%"+date+"%");
        return exerciseMapper.selectByExample(exerciseExample);
    }
}
