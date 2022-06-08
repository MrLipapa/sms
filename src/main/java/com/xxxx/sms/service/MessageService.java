package com.xxxx.sms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.sms.base.BaseService;
import com.xxxx.sms.dao.MessageMapper;
import com.xxxx.sms.query.MessageQuery;
import com.xxxx.sms.utils.AssertUtil;
import com.xxxx.sms.vo.Message;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService extends BaseService<Message,Integer> {
    @Resource
    private MessageMapper messageMapper;

    /**
     * 多条件查询数据
     * @param messageQuery
     * @return
     */
    public Map<String, Object> queryByParams(MessageQuery messageQuery,Integer sourceId){
        Map<String, Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(messageQuery.getPage(),messageQuery.getLimit());
        List<Message> messages = messageMapper.queryByParams(messageQuery,sourceId);
        //按照分页条件，格式化数据
        PageInfo<Message> messagePageInfo = new PageInfo<>(messages);

        map.put("code",0);
        map.put("msg","");
        map.put("count",messagePageInfo.getTotal());
        map.put("data",messagePageInfo.getList());
        return map;
    }

    /**
     * 添加数据
     *      1.校验参数
     *          userName        接收人姓名 非空
     *          message         留言内容   非空
     *          className       班级名 非空
     *      2.设置默认值
     *          is_valid     数据有效   0无效 1有效
     *          create_date  数据创建时间
     *          update_date  数据修改时间
     *          sourceId   源对象的id  当前登录用户（交给controller层从cookie获取）直接设置到 message对象中
     *
     *       3.执行添加操作，判断是否添加成功
     * @return
     */
    public void addMessage(Message message){
        //校验参数
        checkParams(message.getUserName(),message.getMessage(),message.getClassId()+"");
        //根据目标对象名称 获取 目标对象id
        Integer targetId = messageMapper.queryTargetId(message.getUserName());
        message.setTargetId(targetId);
        //设置默认值
        message.setIsValid(1);
        message.setUpdateDate(new Date());
        message.setCreateDate(new Date());
        //执行添加操作，判断是否添加成功
        AssertUtil.isTrue(messageMapper.insertSelective(message) < 1,"留言添加失败");
    }

        /**
     * 校验添加数据
     *          userName        接收人姓名 非空
     *          message         留言内容   非空
     *          className       班级名 非空
     * @param userName
     * @param message
     * @param className
     */
    private void checkParams(String userName, String message, String className) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"接收人不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(message),"留言内容不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(className),"班级名不能为空");
    }

    /**
     * 修改数据
     *      1.校验参数
     *          id属性是必须存在的，查询数据库校验
     *          userName        接收人姓名 非空
     *          message         留言内容   非空
     *          className       班级名 非空
     *      2.默认值
     *          update_date  修改时间

     *     3.执行修改操作，判断是否修改成功
     *
     * @param message
     */
    public void updateMessage(Message message){
        //判断id是否存在
        AssertUtil.isTrue(message.getId() == null,"数据异常，请重试");
        //校验非空参数
        checkParams(message.getUserName(),message.getMessage(),message.getClassId()+"");
        //设置默认值
        message.setUpdateDate(new Date());

        //通过现有的id查询修改之前的数据
        Message dbSaleChance = messageMapper.selectByPrimaryKey(message.getId());
        AssertUtil.isTrue(dbSaleChance == null,"数据异常，请重试");

        //执行修改操作
        AssertUtil.isTrue(messageMapper.updateByPrimaryKeySelective(message) < 1,"营销数据修改失败");
    }

    public void deleteMessage(Integer[] ids){
        AssertUtil.isTrue(ids == null || ids.length ==0,"请选择要删除的记录");
        AssertUtil.isTrue(messageMapper.deleteBatch(ids)!=ids.length,"营销机会数据删除失败！") ;
    }

    public String queryClassName(Integer id) {
        return messageMapper.queryClassName(id);
    }

    public List<Map<String, Object>> queryAllClass() {
        return  messageMapper.queryAllClass();
    }
}
