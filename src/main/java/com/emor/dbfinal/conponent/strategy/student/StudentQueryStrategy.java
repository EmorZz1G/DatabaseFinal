package com.emor.dbfinal.conponent.strategy.student;

import com.emor.dbfinal.entity.Student;
import com.github.pagehelper.PageInfo;
import lombok.ToString;

public interface StudentQueryStrategy {
    PageInfo<Student> query(Integer pageNum);
    String getStrategyInfo();
    String toString();
}
