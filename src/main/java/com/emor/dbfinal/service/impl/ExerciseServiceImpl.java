package com.emor.dbfinal.service.impl;

import com.emor.dbfinal.controller.TeacherController;
import com.emor.dbfinal.dao.*;
import com.emor.dbfinal.entity.*;
import com.emor.dbfinal.exception.UserException;
import com.emor.dbfinal.service.ExerciseService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.TabableView;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    ExerciseMapper exerciseMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TSExerciseMapper tsExerciseMapper;

    @Override
    public int updateExerciseByTSName(Integer id,String teacherName, String studentName, String exerciseDate, Integer exerciseLength,Boolean present) throws UserException{
        Integer sid;
        try{
            sid = studentMapper.selectOneByName(studentName).getId();
        }catch (Exception e){
            throw new UserException("该学生不存在");
        }
        Integer tid;
        try {
            tid = teacherMapper.selectOneByName(teacherName).getId();
        }catch (Exception e){
            throw new UserException("该老师不存在");
        }
        Exercise exercise = new Exercise();
        exercise.setId(id);
        exercise.setSid(sid);
        exercise.setTid(tid);
        exercise.setExerciseDate(exerciseDate);
        exercise.setExerciseLength(exerciseLength);
        exercise.setPresent(present);
        return exerciseMapper.updateByPrimaryKeySelective(exercise);
    }

    @Override
    public Exercise findById(Integer id) {
        return exerciseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int reserveExercise(User user, Exercise exercise) {
        ExerciseExample example =new ExerciseExample();
        ExerciseExample.Criteria criteria = example.createCriteria();
        criteria.andTidEqualTo(user.getFid());
        return exerciseMapper.updateByExampleSelective(exercise,example);
    }

    @Override
    public int updateExercise(Exercise exercise) {
        return exerciseMapper.updateByPrimaryKeySelective(exercise);
    }
    @Override
    public int deleteExercise(Integer id) {
        return exerciseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int addExercise(User user, List<String> dates, Integer[] lengthToINT) {
        List<Exercise> exercises = new ArrayList<>();
        for(int i = 0;i<dates.size();i++){
            Exercise exercise = new Exercise();
            exercise.setExerciseDate(dates.get(i));
            exercise.setTid(user.getFid());
            exercise.setExerciseLength(lengthToINT[i]);
            exercises.add(exercise);
        }
        return exerciseMapper.insertSelectives( exercises);
    }

    @Override
    public int deleteById(Integer id) {
        return  exerciseMapper.deleteByPrimaryKey(id);
    }



    @Override
    public List<Exercise> findByDate(User user, String date) {
        System.out.println(date);
        ExerciseExample exerciseExample= new ExerciseExample();
        ExerciseExample.Criteria criteria = exerciseExample.createCriteria();
        criteria.andTidEqualTo(user.getFid());
        criteria.andExerciseDateLike("%"+date+"%");
        List<Exercise> exercises = exerciseMapper.selectByExample(exerciseExample);
        System.out.println(exercises);
        return exercises;
    }
}
