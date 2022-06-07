package com.xxxx.sms.dao;

import com.xxxx.sms.base.BaseMapper;
import com.xxxx.sms.vo.User;

public interface UserMapper extends BaseMapper<User,Integer> {
    //通过用户名查询数据
    public User queryUserByName(String name);
}
