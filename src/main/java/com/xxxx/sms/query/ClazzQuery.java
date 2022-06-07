package com.xxxx.sms.query;

import com.xxxx.sms.base.BaseQuery;

public class ClazzQuery extends BaseQuery {
    //查询条件中的班级名称
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
