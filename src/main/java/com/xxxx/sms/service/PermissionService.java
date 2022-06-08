package com.xxxx.sms.service;

import com.xxxx.sms.base.BaseService;
import com.xxxx.sms.dao.PermissionMapper;
import com.xxxx.sms.vo.Permission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionService extends BaseService<Permission,Integer> {
    @Resource
    private PermissionMapper permissionMapper;

    //查询当前用户的权限码
    public List<String> selectUserRoleAvlValue(Integer id){
        return permissionMapper.selectUserRoleAclValue(id);
    }
}
