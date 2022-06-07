package com.xxxx.sms.controller;

import com.xxxx.sms.base.BaseController;
import com.xxxx.sms.base.ResultInfo;
import com.xxxx.sms.model.CourseModel;
import com.xxxx.sms.query.CourseQuery;
import com.xxxx.sms.service.CourseService;
import com.xxxx.sms.utils.AssertUtil;
import com.xxxx.sms.utils.CookieUtil;
import com.xxxx.sms.utils.LoginUserUtil;
import com.xxxx.sms.vo.Course;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("course")
public class CourseController extends BaseController {

    @Resource
    private CourseService courseService;

    /**
     * 多条件查询课程数据
     * @param
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryCourseByParams(CourseQuery courseQuery){
        return courseService.queryCourseByParams(courseQuery);
    }

    // 跳转到课程管理页面
    @RequestMapping("course")
    public String toCourse(){
        return "course/course";
    }

    /**
     * 添加课程数据
     * @param request
     * @param course
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addCourse(HttpServletRequest request, Course course){
        System.out.println(course.toString());
        //获取ID
        Integer id= LoginUserUtil.releaseUserIdFromCookie(request);

        //调用Service层方法
        courseService.addCourseChance(course);
        return success("课程添加成功!");
    }

    /**
     * 课程添加和更新转发
     * @param
     * @param request
     * @return
     */
    @RequestMapping("saveCourse")
    public String saveCourse(Integer courseId,HttpServletRequest request){
        if(courseId != null){
            //通过ID查询数据
            Course course=courseService.selectByPrimaryKey(courseId);
            //将数据存到作用域中
            request.setAttribute("course",course);
        }
        return "course/add_update";
    }

    /**
     * 更新营销机会数据
     * @param request
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateCourse(HttpServletRequest request,Course course){
       // 更新营销机会的数据
        courseService.updateCourse(course);
        return success("课程数据更新成功！");
    }

    /**
     * 删除营销机会数据
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCourse (Integer[] ids) {
        // 删除营销机会的数据
        courseService.deleteCourse(ids);
        return success("营销机会数据删除成功！");
    }

}
