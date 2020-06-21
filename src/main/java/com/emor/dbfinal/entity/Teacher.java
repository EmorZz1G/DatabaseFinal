package com.emor.dbfinal.entity;


import lombok.ToString;

import java.io.Serializable;

@ToString
public class Teacher implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.id
     *
     * @mbggenerated Sun Jun 14 09:01:37 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.name
     *
     * @mbggenerated Sun Jun 14 09:01:37 CST 2020
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.sex
     *
     * @mbggenerated Sun Jun 14 09:01:37 CST 2020
     */
    private String sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.course
     *
     * @mbggenerated Sun Jun 14 09:01:37 CST 2020
     */
    private String course;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.phone
     *
     * @mbggenerated Sun Jun 14 09:01:37 CST 2020
     */
    private String phone;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.id
     *
     * @return the value of teacher.id
     *
     * @mbggenerated Sun Jun 14 09:01:37 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.id
     *
     * @param id the value for teacher.id
     *
     * @mbggenerated Sun Jun 14 09:01:37 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.name
     *
     * @return the value of teacher.name
     *
     * @mbggenerated Sun Jun 14 09:01:37 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.name
     *
     * @param name the value for teacher.name
     *
     * @mbggenerated Sun Jun 14 09:01:37 CST 2020
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.sex
     *
     * @return the value of teacher.sex
     *
     * @mbggenerated Sun Jun 14 09:01:37 CST 2020
     */
    public String getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.sex
     *
     * @param sex the value for teacher.sex
     *
     * @mbggenerated Sun Jun 14 09:01:37 CST 2020
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.course
     *
     * @return the value of teacher.course
     *
     * @mbggenerated Sun Jun 14 09:01:37 CST 2020
     */
    public String getCourse() {
        return course;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.course
     *
     * @param course the value for teacher.course
     *
     * @mbggenerated Sun Jun 14 09:01:37 CST 2020
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.phone
     *
     * @return the value of teacher.phone
     *
     * @mbggenerated Sun Jun 14 09:01:37 CST 2020
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.phone
     *
     * @param phone the value for teacher.phone
     *
     * @mbggenerated Sun Jun 14 09:01:37 CST 2020
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}