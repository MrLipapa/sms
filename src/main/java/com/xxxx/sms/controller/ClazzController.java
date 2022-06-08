package com.xxxx.sms.controller;

import com.xxxx.sms.annotation.RequirePermission;
import com.xxxx.sms.base.BaseController;
import com.xxxx.sms.base.ResultInfo;
import com.xxxx.sms.query.ClazzQuery;
import com.xxxx.sms.service.ClazzService;
import com.xxxx.sms.vo.Clazz;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("clazz")
public class ClazzController extends BaseController {
    @Resource
    private ClazzService clazzService;
    /**
     * 多条件分页查询数据
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    @RequirePermission(code = "6010")
    public Map<String, Object> queryByParams(ClazzQuery clazzQuery) {
        return clazzService.queryByParams(clazzQuery);
    }
    /**
     * 打开班级管理页面
     * @return
     */
    @RequestMapping("clazz")
    public String index(){
        return "clazz/clazz";
    }

    @RequestMapping("toAddUpdatePage")
    public String toAddOrUpdate(Integer id, HttpServletRequest request){
        if (id != null) {
            Clazz clazz = clazzService.selectByPrimaryKey(id);
            request.setAttribute("clazz",clazz);
        }
        return "clazz/add_update";
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo addClazz(Clazz clazz){
        clazzService.addClazz(clazz);
        return success();
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateClazz(Clazz clazz){
        clazzService.updateClazz(clazz);
        return success();
    }

    //批量删除班级
    @RequestMapping("deleteBatch")
    @ResponseBody
    public ResultInfo deleteBatch(Integer[]  ids){
        clazzService.deleteUsers(ids);
        return success();
    }

}
