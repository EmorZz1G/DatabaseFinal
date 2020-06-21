package com.emor.dbfinal.service;

import com.emor.dbfinal.entity.Examination;
import com.emor.dbfinal.entity.User;
import com.emor.dbfinal.exception.ExaminationException;

import java.util.List;

public interface ExaminationService {
    int addExamination(User user, String date,String course, Integer num);
    int reserveExamination(User user,Integer id) throws ExaminationException;
    List<Examination> findByDateForTeacher(User user, String date);
    List<Examination> findByDateForTeacher(String teacherName, String date);
    List<Examination> findByDateForStudent(User user,String date);
    Examination findById(Integer id);
    int updateExaminationByTSName(Integer id,String teacherName, String studentName, String examinationDate, Double score);

    int deleteExamRecord(Integer id);
}
