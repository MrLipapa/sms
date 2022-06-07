package com.xxxx.sms.dao;

import com.xxxx.sms.base.BaseMapper;
import com.xxxx.sms.query.ClazzQuery;
import com.xxxx.sms.vo.Clazz;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClazzMapper extends BaseMapper<Clazz,Integer> {
    //多条件分页查询数据
    public List<Clazz> queryByParams(ClazzQuery query);

    //根据班级名称查找班级
    Integer selectClazzByName(String className);

    //添加班级
    Integer addClazz(@Param("clazz") Clazz clazz);

    //修改班级
    Integer updateClazz(@Param("clazz") Clazz clazz);

    //批量作业
    Integer deleteUsers(Integer[] ids);
}