package com.xxxx.sms.query;

import com.xxxx.sms.base.BaseQuery;

public class MessageQuery extends BaseQuery {

    private String targetId; // 接收人id
    private String userName; // 接收人姓名
    private String classId; // 源对象班级id
    private String className; // 班级名

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
