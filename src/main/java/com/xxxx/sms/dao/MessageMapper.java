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

}