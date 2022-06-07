package com.xxxx.sms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.sms.base.BaseService;
import com.xxxx.sms.dao.ModuleMapper;
import com.xxxx.sms.dao.PermissionMapper;
import com.xxxx.sms.dao.RoleMapper;
import com.xxxx.sms.query.RoleQuery;
import com.xxxx.sms.utils.AssertUtil;
import com.xxxx.sms.vo.Permission;
import com.xxxx.sms.vo.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RoleService extends BaseService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private ModuleMapper moduleMapper;

    /**
     * 多条件分页查询
     * @param
     * @return
     */
    public Map<String,Object> queryRoleByParams(RoleQuery roleQuery){
        Map<String,Object> map=new HashMap<>();
        //开启分页
        PageHelper.startPage(roleQuery.getPage(),roleQuery.getLimit());
        //多条件查询,
        List<Role> roles = roleMapper.queryRoleByParams(roleQuery);
        //按照分页条件,进行格式化数据
        PageInfo<Role> rolePageInfo =new PageInfo<>(roles);
        //设置 map
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", rolePageInfo.getTotal());
        map.put("data", rolePageInfo.getList());
        return map;
    }

    /*//查询所有角色数据
    public List<Role> queryAllRoles(){
        return roleMapper.queryAllRoles();
    };*/

    /**
     * 添加一条角色数据:
     *      角色名进行判断：非空，并且数据库中不存在
     *      设置默认值
     *      执行添加操作，判断是否成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRole(Role role){
        //判断添加的用户名是否为空
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"角色名不能为空");
        //通过查询数据库判断用户是否存在
        Role temp = roleMapper.queryRoleByRoleName(role.getRoleName());
        AssertUtil.isTrue(temp != null,"角色已存在");

        //设置默认值
        role.setIsValid(1);
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());

        //执行添加操作
        AssertUtil.isTrue(roleMapper.insertSelective(role) < 1,"角色数据添加失败");
    }

    /**
     * 修改一条数据:
     *      待修改的数据不为空并且可以通过主键找到
     *      用户名不能为空
     *      通过角色名查询数据库
     */
    public void updateRole(Role role){
        AssertUtil.isTrue(role.getId() == null || roleMapper.selectByPrimaryKey(role.getId()) == null,"待修改的数据不存在");
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"角色名不能为空");
        /*Role temp = roleMapper.queryRoleByRoleName(role.getRoleName());
        AssertUtil.isTrue(temp == null && role.getId() != temp.getId(),"角色异常");*/

        //设置默认值
        role.setUpdateDate(new Date());

        //执行修改操作，进行判断
        AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective(role)<1,"角色修改失败");
    }

    /**
     * 删除一条角色数据
     *      判断待删除的数据是否存在：非空 数据库可以查询到
     *      设置默认值is_valid = 0
     *      执行删除操作，并判断
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRole(Integer id){
        Role role = (Role) roleMapper.selectByPrimaryKey(id);
        //判断待删除的角色id
        AssertUtil.isTrue(id == null,"待删除的角色id为空");
        //有id后通过数据库查询是否有角色
        AssertUtil.isTrue(role == null,"待删除的角色不存在");
        //设置默认值
        role.setIsValid(0);
        //执行删除操作，并判断
        AssertUtil.isTrue(deleteByPrimaryKey(role) < 1 ,"角色删除失败");
    }

    /**
     *  添加权限
     *      判断角色是否存在
     *
     */
    public void addGrant(Integer roleId,Integer[] mIds){
        //判断角色id和角色
        AssertUtil.isTrue(roleId == null,"待添加权限的角色id异常");
        AssertUtil.isTrue(roleMapper.selectByPrimaryKey(roleId) == null,"待添加权限的角色不存在");

        //判断角色之前是否存在权限模块资源，查询数据库
        Integer count = permissionMapper.countPermissions(roleId);
        if(count > 0){
            //角色之前存在权限模块,并删除所有权限并判断是否删除成功
            //通过删除权限的数量和角色原有权限数量比较
            AssertUtil.isTrue(permissionMapper.deletePermissions(roleId) != count,"角色权限模块删除异常");
        }

        //判断并删除之前所有权限，给角色绑定新的权限模块
        List<Permission> permissions = new ArrayList<>(); //准备容器，存放待添加的permission对象
        for (Integer mId:mIds){
            Permission permission = new Permission();
            // ???????
            permission.setRoleId(roleId);
            permission.setModuleId(mId);
            //permission.setAclValue(moduleMapper.selectByPrimaryKey(mId).g);
            permission.setCreateDate(new Date());
            permission.setUpdateDate(new Date());

            permissions.add(permission);
        }

        //执行批量添加权限操作，绑定多个,并判断
        AssertUtil.isTrue(permissionMapper.insertBatch(permissions) != permissions.size(),"权限模块添加失败");
    }
}
