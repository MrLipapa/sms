package com.xxxx.sms.query;

import com.xxxx.sms.base.BaseQuery;

public class HomeworkQuery extends BaseQuery {
    //查询条件中的作业名
    private String homeworkName;
    //查询条件中的课程名
    private String courseName;
    //查询条件中的班级名
    private String className;

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
