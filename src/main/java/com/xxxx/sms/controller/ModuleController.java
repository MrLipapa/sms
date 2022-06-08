package com.xxxx.sms.controller;

import com.xxxx.sms.annotation.RequirePermission;
import com.xxxx.sms.base.BaseController;
import com.xxxx.sms.base.ResultInfo;
import com.xxxx.sms.model.TreeModule;
import com.xxxx.sms.service.ModuleService;
import com.xxxx.sms.vo.Module;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("module")
public class ModuleController extends BaseController {

    @Resource
    private ModuleService moduleService;

    @RequestMapping("queryAllModules")
    @ResponseBody
    public List<TreeModule> queryAllModules(Integer roleId){
        return moduleService.queryAllModules(roleId);
    }

    //点击菜单管理，到此controller进行页面转发
    @RequestMapping("index")
    public String index(){
        return "module/module";
    }

    //从资源管理页面对应的js代码跳转地址:在页面上显示所有资源模块
    @RequestMapping("list")
    @ResponseBody
    @RequirePermission(code = "502001")
    public Map<String, Object> queryModules(){
        return moduleService.queryModules();
    }

    //头部工具栏打开的添加页面
    @RequestMapping("toAdd")
    public String toAdd(Integer grade, Integer parentId, HttpServletRequest request){
        request.setAttribute("grade",grade);
        request.setAttribute("parentId",parentId);
        //跳转到add添加页面
        return "module/add";
    }

    //添加模块资源
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo moduleAdd(Module module){
        moduleService.moduleAdd(module);
        return success("模块添加成功");
    }

    //打开修改模块资源页面
    @RequestMapping("updateModulePage")
    public String updateModulePage(Integer id,HttpServletRequest request){
        if(id != null){
            Object module = moduleService.selectByPrimaryKey(id);
            request.setAttribute("module",module);
        }

        return "module/update";
    }

    //
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo moduleUpdate(Module module){
        moduleService.moduleUpdate(module);
        return success("资源修改成功");
    }
}
