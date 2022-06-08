package com.xxxx.sms.controller;

import com.xxxx.sms.annotation.RequirePermission;
import com.xxxx.sms.base.BaseController;
import com.xxxx.sms.base.ResultInfo;
import com.xxxx.sms.query.RoleQuery;
import com.xxxx.sms.service.RoleService;
import com.xxxx.sms.vo.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
    @Resource
    private RoleService roleService;

    //从主页面点击后进行跳转，到role.ftl页面
    @RequestMapping("index")
    public String index(){
        return "role/role";
    }

    /*//查询所有角色数据
    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Role> queryAllRoles(){
        return roleService.queryAllRoles();
    };*/

    /**
     * 多条件分页查询角色数据
     * @param roleQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    @RequirePermission(code = "501001")
    public Map<String,Object> queryRoleByParams(RoleQuery roleQuery){
        return roleService.queryRoleByParams(roleQuery);
    }

    /**
     *  从前台发送请求到controller，根据是否有id值进入修改或者添加页面
     */
    @RequestMapping("addOrUpdateRolePage")
    public String addOrUpdateRolePage(Integer id, HttpServletRequest request){
        //这里要进行判断，看到底打开的是修改还是添加页面
        if(id != null){
            request.setAttribute("role",roleService.selectByPrimaryKey(id)); //???????
        }
        return "role/add_update"; //打开添加/修改页面
    };

    /**
     *  添加角色信息的内容
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addRole(Role role){
        roleService.addRole(role);
        return success("角色添加成功");
    }

    /**
     *  修改内容
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateRole(Role role){
        roleService.updateRole(role);
        return success("角色修改成功");
    }

    /**
     *  删除一条数据
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteRole(Integer id){
        roleService.deleteRole(id);
        return success("角色删除成功");
    }

    /**
     *  角色页面点击授权后，通过方法中给定的url跳转到controller指定位置
     */
    @RequestMapping("toAddGrantPage")
    public String toAddGrantPage(Integer roleId,HttpServletRequest request){
        request.setAttribute("roleId",roleId);
        return "role/grant";
    }

    /**
     *  接收前台发送的请求,准备给角色添加权限
     */
    @RequestMapping("addGrant")
    @ResponseBody
    //mIds代表权限数组
    public ResultInfo addGrant(Integer roleId,Integer[] mIds){
        roleService.addGrant(roleId,mIds);
        return success("添加权限成功");
    }
}
