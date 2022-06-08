package com.xxxx.sms.dao;

import com.xxxx.sms.base.BaseMapper;

import java.util.List;

public interface PermissionMapper extends BaseMapper {
    //判断当前角色是否存在权限,并用count接收权限数量
    Integer countPermissions(Integer roleId);

    //记录删除权限的数量
    Integer deletePermissions(Integer roleId);

    //查询当前角色的权限
    List<Integer> selectPermissionByRoleId(Integer roleId);

    //查询当前用户的权限码
    List<String> selectUserRoleAclValue(Integer roleId);

}
