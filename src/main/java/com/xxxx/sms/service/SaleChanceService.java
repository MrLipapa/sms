package com.xxxx.sms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.sms.base.BaseService;
import com.xxxx.sms.dao.SaleChanceMapper;
import com.xxxx.sms.query.SaleChanceQuery;
import com.xxxx.sms.utils.AssertUtil;
import com.xxxx.sms.utils.PhoneUtil;
import com.xxxx.sms.vo.SaleChance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleChanceService extends BaseService {
    @Resource
    private SaleChanceMapper saleChanceMapper;

    /**
     * 1.0
     * 多条件查询数据
     *
     * @param saleChanceQuery
     * @return
     */
    public Map<String, Object> queryByParams(SaleChanceQuery saleChanceQuery) {
        Map<String, Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(saleChanceQuery.getPage(), saleChanceQuery.getLimit());
        List<SaleChance> saleChances = saleChanceMapper.queryByParams(saleChanceQuery);
        //按照分页条件，格式化数据
        PageInfo<SaleChance> saleChancePageInfo = new PageInfo<>(saleChances);
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", saleChancePageInfo.getTotal());
        map.put("data", saleChancePageInfo.getList());
        return map;
    }

    //查询所有信息
    public List<Map<String, Object>> queryClass() {
        return saleChanceMapper.queryClass();
    }

    /**
     * 2.0
     * 添加数据
     * 1.校验参数
     * userName   用户名 非空
     * userPhone  手机号码 非空  手机号11位正则校验
     * userClassId 班级 非空
     * 2.设置默认值
     * is_valid     数据有效   0无效 1有效
     * create_date  数据创建时间
     * update_date  数据修改时间
     * 3.执行添加操作，判断是否添加成功
     *
     * @return
     */
    public void addSaleChance(SaleChance saleChance) {
        //1、校验参数
        checkParams(saleChance.getUserName(), saleChance.getUserPhone(), saleChance.getUserClassId());
        //2、设置默认值
        saleChance.setIsValid(1);//数据有效   0无效 1有效
        saleChance.setUpdateDate(new Date());// 数据修改时间
        saleChance.setCreateDate(new Date());//数据创建时间
        //3.执行添加操作，判断是否添加成功
        AssertUtil.isTrue(saleChanceMapper.insertSelective(saleChance) < 1, "学生信息数据添加失败");
    }

    private void checkParams(String userName, String userPhone, String userClassId) {
        //参数非空判断
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(userPhone), "手机号码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(userClassId), "所在班级不能为空");
        //校验手机号是否符合规范
        AssertUtil.isTrue(!PhoneUtil.isMobile(userPhone), "手机号不符合规范");
    }

    /**
     * 修改数据
     * 1.校验参数
     * id属性是必须存在的，查询数据库校验
     * userName   用户名 非空
     * userPhone  手机号码 非空  手机号11位正则校验
     * userClassId 班级 非空
     * 2.默认值
     * update_date  修改时间
     * 3.执行修改操作，判断是否修改成功
     *
     * @param saleChance
     */
    public void updateSaleChance(SaleChance saleChance) {
        //判断id是否存在
        AssertUtil.isTrue(saleChance.getId() == null, "数据异常，请重试");
        //校验非空参数
        checkParams(saleChance.getUserName(), saleChance.getUserPhone(), saleChance.getUserClassId());
        //设置默认值
        saleChance.setUpdateDate(new Date());
        //通过现有的id查询修改之前的数据
        SaleChance dbSaleChance = saleChanceMapper.selectByPrimaryKey(saleChance.getId());
        AssertUtil.isTrue(dbSaleChance == null, "数据异常，请重试");
        //执行修改操作
        AssertUtil.isTrue(saleChanceMapper.updateByPrimaryKeySelective(saleChance) < 1, "学生信息管理数据修改失败");
    }

    /**
     *数据删除
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSaleChance (Integer[] ids) {
        // 判断要删除的id是否为空
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择需要删除的数据！");
        // 删除数据
        AssertUtil.isTrue(saleChanceMapper.deleteBatch(ids) < 0, "学生信息管理数据删除失败！");
    }

    public String queryClassName(Integer userClassId) {
        return saleChanceMapper.queryClassName(userClassId);
    }
}
