package com.xxxx.sms.dao;

import com.xxxx.sms.base.BaseMapper;
import com.xxxx.sms.query.SaleChanceQuery;
import com.xxxx.sms.vo.SaleChance;

import java.util.List;
import java.util.Map;

public interface SaleChanceMapper extends BaseMapper<SaleChance,Integer> {
    //多条件查询数据
   public List<SaleChance> queryByParams(SaleChanceQuery saleChanceQuery);
    //查询所有销售人员数据
   public List<Map<String,Object>> queryAllSales();
   //查询所有班级信息
   public List<Map<String,Object>> queryClass();

   //根据班级id查询班级名字
    public String queryClassName(Integer userClassId);

 }