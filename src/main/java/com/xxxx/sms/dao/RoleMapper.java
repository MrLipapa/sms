package com.xxxx.sms.dao;

import com.xxxx.sms.base.BaseMapper;
import com.xxxx.sms.query.RoleQuery;
import com.xxxx.sms.vo.Role;
import org.springframework.context.annotation.Primary;

import java.util.List;
@Primary
public interface RoleMapper extends BaseMapper {
    //分页查询数据库角色信息
    public List<Role> queryRoleByParams(RoleQuery roleQuery);

    //判断数据库是否存在角色名
    public Role queryRoleByRoleName(String roleName);

}