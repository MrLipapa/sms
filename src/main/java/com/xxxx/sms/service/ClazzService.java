package com.xxxx.sms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.sms.base.BaseService;
import com.xxxx.sms.base.ResultInfo;
import com.xxxx.sms.dao.ClazzMapper;
import com.xxxx.sms.query.ClazzQuery;
import com.xxxx.sms.utils.AssertUtil;
import com.xxxx.sms.vo.Clazz;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClazzService extends BaseService<Clazz,Integer> {
    @Resource
    private ClazzMapper clazzMapper;
    /**
     * 多条件查询数据
     * @return
     */
    public Map<String, Object> queryByParams(ClazzQuery clazzQuery) {
        Map<String, Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(clazzQuery.getPage(), clazzQuery.getLimit());
        List<Clazz> clazzs = clazzMapper.queryByParams(clazzQuery);
        //按照分页条件，格式化数据
        PageInfo<Clazz> clazzPageInfo = new PageInfo<>(clazzs);

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", clazzPageInfo.getTotal());
        map.put("data", clazzPageInfo.getList());
        return map;
    }

    //添加班级
    public void addClazz(Clazz clazz) {
        //校验数据 班级名称 非空 且 唯一
        checkParam(clazz);
        //设置默认值
        clazz.setIsValid(1);
        clazz.setCreateDate(new Date());
        clazz.setUpdateDate(new Date());
        AssertUtil.isTrue(clazzMapper.addClazz(clazz)!=1,"添加失败！");
    }

    private void checkParam(Clazz clazz) {
        //非空
        AssertUtil.isTrue(StringUtils.isBlank(clazz.getClassName()),"班级名称不能为空！");
        //唯一
        Integer clazzDB=clazzMapper.selectClazzByName(clazz.getClassName());
        System.out.println(clazzDB);
        AssertUtil.isTrue(clazzDB != 0,"班级名称已存在！");
    }

    public void updateClazz(Clazz clazz) {
        //校验数据 班级名称 非空 且 唯一
        checkParam(clazz);
        //设置默认值
        clazz.setUpdateDate(new Date());
        //执行修改操作，校验是否成功
        AssertUtil.isTrue(clazzMapper.updateClazz(clazz)!=1,"修改操作失败！");
    }

    //批量删除作业
    public void deleteUsers(Integer[]  ids){
        AssertUtil.isTrue(ids==null||ids.length<1, "未选中任何作业!");
        AssertUtil.isTrue(clazzMapper.deleteUsers(ids)!=ids.length, "作业删除失败!");
    }
}
