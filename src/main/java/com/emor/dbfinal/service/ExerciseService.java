package com.emor.dbfinal.service;

import com.emor.dbfinal.entity.Exercise;
import com.emor.dbfinal.entity.User;
import com.emor.dbfinal.exception.UserException;
import org.springframework.data.relational.core.sql.In;

import javax.xml.crypto.Data;
import java.util.List;

public interface ExerciseService {
    List<Exercise> findByDate(User user, String date);
    Exercise findById(Integer id);
    int deleteById(Integer id);

    int addExercise(User user, List<String> dates, Integer[] lengthToINT);

    int deleteExercise(Integer id);

    int updateExercise(Exercise exercise);

    int reserveExercise(User user,Exercise exercise);

    int updateExerciseByTSName (Integer id,String teacherName
            , String studentName
            , String exerciseDate
            , Integer exerciseLength,Boolean present)throws UserException;
}
