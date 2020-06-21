package com.emor.dbfinal.service.impl;

import com.emor.dbfinal.controller.TeacherController;
import com.emor.dbfinal.dao.ExaminationMapper;
import com.emor.dbfinal.dao.StudentMapper;
import com.emor.dbfinal.dao.TeacherMapper;
import com.emor.dbfinal.entity.*;
import com.emor.dbfinal.exception.ExaminationException;
import com.emor.dbfinal.service.ExaminationService;
import com.emor.dbfinal.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExaminationServiceImpl implements ExaminationService {

    Logger logger = LoggerFactory.getLogger(ExaminationServiceImpl.class);
    @Autowired
    ExaminationMapper examinationMapper;
    private static ExaminationExample examinationExample = new ExaminationExample();
    private ExaminationExample.Criteria criteriaForExam;
    @Autowired
    private StudentMapper studentMapper;

    void initExamExample(){
        examinationExample.clear();
        criteriaForExam = examinationExample.createCriteria();
    }
    @Autowired
    TeacherMapper teacherMapper;
    private TeacherExample teacherExample;
    private TeacherExample.Criteria criteriaForTeacher;

    @Autowired
    TeacherService teacherService;
    void initTeacherExample(){
        teacherExample = new TeacherExample();
        criteriaForTeacher = teacherExample.createCriteria();
    }

    @Override
    public List<Examination> findByDateForStudent(User user, String date) {
        initExamExample();
        Student studentById = teacherService.findStudentById(user.getFid());
        logger.info("student{}", studentById);
        criteriaForExam.andTidEqualTo(studentById.getTid());
        criteriaForExam.andExaminationDateLike(date);
        return examinationMapper.selectByExample(examinationExample);
    }

    /**
     * 修改考试信息
     * 教练的名字是可选的，可以传入null，
     */
    @Override
    public int updateExaminationByTSName(Integer id, String teacherName, String studentName, String examinationDate, Double score) {
        Integer sid=null;
        try {
            Student student = studentMapper.selectOneByName(studentName);
            logger.info("studentName-get-student {}",student);
            sid = student.getId();
        }catch (Exception e){
            logger.error("exp {}",e);
            e.printStackTrace();
        }
        Integer tid=null;
        if(teacherName!=null){
            tid = teacherMapper.selectOneByName(teacherName).getId();
        }
        Examination examination = new Examination();
        examination.setId(id);
        examination.setExaminationDate(examinationDate);
        if(score!=null){
            examination.setScore(score);
        }
        if(sid!=null){
            examination.setSid(sid);
        }
        if(tid!=null){
            examination.setTid(tid);
        }
        return examinationMapper.updateByPrimaryKeySelective(examination);
    }

    @Override
    public int deleteExamRecord(Integer id) {
        initExamExample();
        return examinationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Examination findById(Integer id) {
        return examinationMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Examination> findByDateForTeacher(User user, String date) {
        initExamExample();
        criteriaForExam.andExaminationDateLike("%"+date+"%");
        criteriaForExam.andTidEqualTo(user.getFid());
        return examinationMapper.selectByExample(examinationExample);
    }

    @Override
    @Transactional
    public int addExamination(User user, String date,String course, Integer num) {
        Examination examination = new Examination();
        examination.setTid(user.getFid());
        examination.setExaminationDate(date);
        examination.setCourse(course);
        int i = 0;
        for(;i<num;i++){
            examinationMapper.insertSelective(examination);
        }
        return i;
    }

    @Override
    public int reserveExamination(User user, Integer id) throws ExaminationException {

        Examination examination = examinationMapper.selectByPrimaryKey(id);
        if(examination.getSid()!=null){
            throw new ExaminationException("该考试已经被预约了！");
        }
        examination = new Examination();
        examination.setId(id);
        examination.setSid(user.getFid());
        return examinationMapper.updateByPrimaryKeySelective(examination);
    }

    @Override
    public List<Examination> findByDateForTeacher(String teacherName, String date) {

        criteriaForTeacher.andNameLike("%"+teacherName+"%");
        List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
        List<Examination> list =new ArrayList<>();
        ExaminationExample examinationExample = new ExaminationExample();
        ExaminationExample.Criteria criteria1 = examinationExample.createCriteria();
        criteria1.andExaminationDateGreaterThanOrEqualTo(date);
        int len = teachers.size();
        for ( int i = 0;i<len;i++){
            List<Examination> list1 = examinationMapper.selectByExample(examinationExample);
            list.addAll(list1);
        }
        return list;
    }

}
