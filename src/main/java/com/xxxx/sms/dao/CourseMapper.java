package com.xxxx.sms.dao;

import com.xxxx.sms.base.BaseMapper;
import com.xxxx.sms.query.CourseQuery;
import com.xxxx.sms.vo.Course;

import java.util.List;

public interface CourseMapper extends BaseMapper<Course,Integer> {

    //多条件查询
    public List<Course> selectByParams(CourseQuery query);

}