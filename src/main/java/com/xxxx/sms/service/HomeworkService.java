package com.xxxx.sms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.sms.base.BaseService;
import com.xxxx.sms.dao.HomeworkMapper;
import com.xxxx.sms.query.HomeworkQuery;
import com.xxxx.sms.utils.AssertUtil;
import com.xxxx.sms.vo.Homework;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeworkService extends BaseService<Homework,Integer> {
    @Resource
    private HomeworkMapper homeworkMapper;

    /**
     * 多条件分页查询作业信息
     * 1. 开启分页
     * 2. 多条件查询
     * 3. 按照分页条件,进行格式化数据
     * 4. 设置 map
     * @param homeworkQuery
     */
    public Map<String,Object> queryHomeworkByParams(HomeworkQuery homeworkQuery){
        Map<String,Object> map=new HashMap<>();
        //1. 开启分页
        PageHelper.startPage(homeworkQuery.getPage(), homeworkQuery.getLimit());
        //2. 多条件查询
        List<Homework> homeworks = homeworkMapper.queryHomeworkByParams(homeworkQuery);
        //3. 按照分页条件,进行格式化数据
        PageInfo<Homework> homeworkPageInfo = new PageInfo<>(homeworks);
        //4.设置map
        map.put("code",0);
        map.put("msg","");
        map.put("count",homeworkPageInfo.getTotal());
        map.put("data",homeworkPageInfo.getList());
        return map;
    }

    //根据作业id查询上传作业的学生的姓名
    public String queryUserNameOfHomework(Integer id) {
        return homeworkMapper.queryUserNameOfHomework(id);
    }

    //根据作业id查询作业班级
    public String queryClassNameByHomeworkId(Integer id){
        return homeworkMapper.queryClassNameByHomeworkId(id);
    }

    public String queryCourseName(Integer id) {
        return homeworkMapper.queryCourseName(id);
    }

    /**
     * 添加作业
     *      1. 校验参数 设置参数
     *          courseName: 非空
     *          className: 非空
     *          homeworkName: 非空
     *          context: 非空
     *          设置默认值:
     *              is_valid: 1
     *              create_date: new Date();
     *              update_date: new Date();
     *      2. 执行添加操作,判断添加是否成功
     */
    public void addHomework(Homework homework) {

        // 校验参数
        checkParam(homework.getCourseId(),homework.getClassId(),homework.getHomeworkName(), homework.getContext());
        //校验外键数据是否存在
/*        Integer courseId = homeworkMapper.querycourseId(homework.getCourseName());
        AssertUtil.isTrue(courseId==null, "课程不存在!");
        homework.setCourseId(courseId);
        Integer classId = homeworkMapper.queryclassId(homework.getClassName());
        AssertUtil.isTrue(classId==null, "班级不存在!");
        homework.setClassId(classId);
*/
        //校验作业是否已存在
        String hName = homeworkMapper.queryhomeworkName(homework.getHomeworkName());
        AssertUtil.isTrue(hName!=null, "作业已存在!");

        //设置默认值
        homework.setIsValid(1);
        homework.setCreateDate(new Date());
        homework.setUpdateDate(new Date());

        //添加作业 判断添加是否成功
        AssertUtil.isTrue(homeworkMapper.addHomework(homework) < 1, "添加失败!");
    }

    //校验参数
    private void checkParam(Integer courseName, Integer className, String homeworkName, String context) {
        AssertUtil.isTrue(courseName==null, "课程名不能为空!");
        AssertUtil.isTrue(className==null, "班级名不能为空!");
        AssertUtil.isTrue(homeworkName==null||"".equals(homeworkName), "作业名不能为空!");
        AssertUtil.isTrue(context==null||"".equals(context), "作业内容不能为空!");
    }

    public void updateHomework(Homework homework) {
        //校验参数
        checkParam(homework.getCourseId(),homework.getClassId(),homework.getHomeworkName(), homework.getContext());
        //校验外键数据是否存在
        /*Integer courseId = homeworkMapper.querycourseId(homework.getCourseId());
        AssertUtil.isTrue(courseId==null, "课程不存在!");
        homework.setCourseId(courseId);
        Integer classId = homeworkMapper.queryclassId(homework.getClassId());
        AssertUtil.isTrue(classId==null, "班级不存在!");
        homework.setClassId(classId);*/
        //校验作业是否已存在---可能并不会修改作业名称
        //String hName = homeworkMapper.queryhomeworkName(homework.getHomeworkName());
        //AssertUtil.isTrue(hName!=null, "作业已存在!");

        //设置默认参数
        homework.setUpdateDate(new Date());

        //执行修改,并校验修改操作是否完成
        AssertUtil.isTrue(homeworkMapper.updateHomework(homework)<1, "作业修改失败!");
    }

    //批量删除作业
    public void deleteUsers(Integer[]  ids){
        AssertUtil.isTrue(ids==null||ids.length<1, "未选中任何作业!");
        AssertUtil.isTrue(homeworkMapper.deleteUsers(ids)!=ids.length, "作业删除失败!");
    }

    public List<Map<String, Object>> queryAllCourse() {
        return homeworkMapper.queryAllCourse();
    }

    public List<Map<String, Object>> queryAllClass() {
        return homeworkMapper.queryAllClass();
    }
}
