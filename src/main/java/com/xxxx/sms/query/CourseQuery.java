package com.xxxx.sms.query;

import com.xxxx.sms.base.BaseQuery;

public class CourseQuery extends BaseQuery {

    private Integer id;//课程id

    private String courseName;//课程名

    private Integer classId;//授课班级id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}
