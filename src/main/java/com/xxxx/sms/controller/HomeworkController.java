package com.xxxx.sms.controller;

import com.xxxx.sms.annotation.RequirePermission;
import com.xxxx.sms.base.BaseController;
import com.xxxx.sms.base.ResultInfo;
import com.xxxx.sms.query.HomeworkQuery;
import com.xxxx.sms.service.HomeworkService;
import com.xxxx.sms.utils.AssertUtil;
import com.xxxx.sms.utils.LoginUserUtil;
import com.xxxx.sms.vo.Homework;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("homework")
public class HomeworkController extends BaseController {
    @Resource
    private HomeworkService homeworkService;

    @RequestMapping("homework")
    public String toHomework(){
        return "homework/homework";
    }

    @RequestMapping("toAddUpdatePage")
    public String toadd(Integer id, HttpServletRequest request){
        //如果是修改操作 需要将待修改的数据映射到前台
        if(id!=null){
            Homework homework = homeworkService.selectByPrimaryKey(id);
            String className = homeworkService.queryClassNameByHomeworkId(id);
            String courseName = homeworkService.queryCourseName(id);
            AssertUtil.isTrue(homework==null, "数据异常请重试!");
            AssertUtil.isTrue(className==null, "数据异常请重试!");
            request.setAttribute("homework", homework);
            request.setAttribute("className", className);
            request.setAttribute("courseName", courseName);
        }
        return "homework/add_update";
    }

    /**
     * 多条件查询作业
     * @param homeworkQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    @RequirePermission(code = "201001")
    public Map<String,Object> queryHomeworkByParams(HttpServletRequest request,HomeworkQuery homeworkQuery){
        //根据Cookie查询当前用户id
        int id = LoginUserUtil.releaseUserIdFromCookie(request);
        return homeworkService.queryHomeworkByParams(homeworkQuery,id);
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo addHomework(Homework homework,HttpServletRequest request){
        Integer id = LoginUserUtil.releaseUserIdFromCookie(request);
        AssertUtil.isTrue(id==0 || id==null,"没有从获取到cookie");
        System.out.println(id);
        homework.setTargetTeacherId(id);
        homeworkService.addHomework(homework);
        return success();
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateHomework(Homework homework){
        homeworkService.updateHomework(homework);
        return success();
    }

    //批量删除作业
    @RequestMapping("deleteBatch")
    @ResponseBody
    public ResultInfo deleteBatch(Integer[]  ids){
        homeworkService.deleteUsers(ids);
        return success();
    }

    /**
     * 查询所有课程
     */
    @RequestMapping("queryAllCourse")
    @ResponseBody
    public List<Map<String,Object>> queryAllCourse(){
        return homeworkService.queryAllCourse();
    }

    /**
     * 查询所有课程
     */
    @RequestMapping("queryAllClass")
    @ResponseBody
    public List<Map<String,Object>> queryAllClass(){
        return homeworkService.queryAllClass();
    }
}
