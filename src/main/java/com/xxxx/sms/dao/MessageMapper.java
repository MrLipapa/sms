package com.xxxx.sms.dao;

import com.xxxx.sms.base.BaseMapper;
import com.xxxx.sms.query.MessageQuery;
import com.xxxx.sms.vo.Message;

import java.util.List;
import java.util.Map;

public interface MessageMapper extends BaseMapper<Message,Integer> {

    //多条件分页查询数据
    public List<Message> queryByParams(MessageQuery query);

    //查询所有销售人员数据
    public List<Map<String,Object>> queryAllSales();

    //根据备注id查询班级名
    String queryClassName(Integer id);

    //查询所有班级信息
    List<Map<String, Object>> queryAllClass();

    //根据目标对象名称 查找目标对象id
    Integer queryTargetId(String userName);
}