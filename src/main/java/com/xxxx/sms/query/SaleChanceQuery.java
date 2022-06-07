package com.xxxx.sms.query;
import com.xxxx.sms.base.BaseQuery;
/**
 * 学生信息管理多条件查询条件
 */
public class SaleChanceQuery extends BaseQuery {
    private String userClassId;//按班级搜索
    private String userSex; //按性别搜索
    private String userName;//按学生姓名搜索
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserClassId() {
        return userClassId;
    }

    public void setUserClassId(String userClassId) {
        this.userClassId = userClassId;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
}