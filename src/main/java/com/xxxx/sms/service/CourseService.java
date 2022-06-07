package com.xxxx.sms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mchange.lang.IntegerUtils;
import com.xxxx.sms.base.BaseService;
import com.xxxx.sms.dao.CourseMapper;
import com.xxxx.sms.query.CourseQuery;
import com.xxxx.sms.utils.AssertUtil;
import com.xxxx.sms.vo.Course;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



@Service
public class CourseService extends BaseService<Course,Integer>{

    @Resource
    private CourseMapper courseMapper;


    /**
     * 多条件查询
     * @param courseQuery
     * @return
     */
    public Map<String,Object> queryCourseByParams(CourseQuery courseQuery){
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(courseQuery.getPage(), courseQuery.getLimit());
        PageInfo<Course> pageInfo = new PageInfo<>(courseMapper.selectByParams(courseQuery));
        map.put("code",0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    /**
     * 课程参数添加
     * @param course
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCourseChance(Course course){

        //参数校验,
        checkParams(course.getCourseName(),course.getClassId());

        //设置相关参数

        course.setCreatDate(new Date());
        course.setUodateDate(new Date());
        course.setIsVaild(1);

        //执行添加校验结果
        AssertUtil.isTrue(insertSelective(course) !=1 ,"课程数据添加失败");

    }

    /**
     * 基本参数校验
     * @param courseName

     * @param classId
     */
    private void checkParams(String courseName,Integer classId) {
        AssertUtil.isTrue(StringUtils.isBlank(courseName),"请输入课程名!");

        AssertUtil.isTrue(null == classId,"请输入教室名称!");

    }

    /**
     * 课程更新
     * @param course
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCourse(Course course) {
        //通过Id查询记录
        Course temp = courseMapper.selectByPrimaryKey(course.getId());
        //判断是否为空
        AssertUtil.isTrue(null == temp, "待更新记录不在");
        //校验基础参数
        checkParams(course.getCourseName(), course.getClassId());


        //执行更新,判断结果
        AssertUtil.isTrue(courseMapper.updateByPrimaryKeySelective(course) != 1,"课程更新失败!");

    }


    /**
     * 课程数据删除
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCourse (Integer[] ids) {
        // 判断要删除的id是否为空
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择需要删除的数据！");
        // 删除数据
        AssertUtil.isTrue(courseMapper.deleteBatch(ids) <0, "课程数据删除失败！");
    }



}
