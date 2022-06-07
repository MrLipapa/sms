package com.xxxx.sms.dao;

import com.xxxx.sms.base.BaseMapper;
import com.xxxx.sms.query.HomeworkQuery;
import com.xxxx.sms.vo.Homework;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HomeworkMapper extends BaseMapper<Homework,Integer> {
    //多条件查询
    List<Homework> queryHomeworkByParams(HomeworkQuery homeworkQuery);

    //根据作业id查询上传作业的学生的姓名
    public String queryUserNameOfHomework(Integer id);

    //根据作业id查询作业班级
    String queryClassNameByHomeworkId(Integer id);

    //根据作业id查询课程名称
    String queryCourseName(Integer id);

    //根据课程名,查询课程id
    Integer querycourseId(String courseName);

    //跟据班级名查询班级id
    Integer queryclassId(String className);

    //根据作业名查询作业是否存在
    String queryhomeworkName(String homeworkName);

    //添加作业
    Integer addHomework(@Param("homework") Homework homework);

    //修改作业
    Integer updateHomework(@Param("homework") Homework homework);

    //批量作业
    Integer deleteUsers(Integer[] ids);

    //查询所有课程
    public List<Map<String,Object>> queryAllCourse();

    //查询所有班级
    public List<Map<String,Object>> queryAllClass();
}
