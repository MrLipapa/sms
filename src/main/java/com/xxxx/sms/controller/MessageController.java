package com.xxxx.sms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.sms.base.BaseController;
import com.xxxx.sms.base.ResultInfo;
import com.xxxx.sms.query.MessageQuery;
import com.xxxx.sms.service.MessageService;
import com.xxxx.sms.utils.AssertUtil;
import com.xxxx.sms.utils.LoginUserUtil;
import com.xxxx.sms.vo.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("message")
public class MessageController extends BaseController {

    @Resource
    private MessageService messageService;
    /**
     * 多条件分页数据查询数据
     * @param messageQuery
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    public Map<String, Object> queryByParams(MessageQuery messageQuery){
        return messageService.queryByParams(messageQuery);
    }
     // 打开留言栏界面
    @RequestMapping("message")
    public String message(){
        return "message/message";
    }

    // 打开添加留言界面
    @RequestMapping("toaddmessage")
    public String toaddmessage(Integer id,HttpServletRequest request){
        //如果是修改操作，需要将修改的数据映射在页面中
        if(id != null){
            Message message = messageService.selectByPrimaryKey(id);
            AssertUtil.isTrue(message == null,"数据异常，请重试");
            request.setAttribute("message",message);
        }
        return "message/addmessage";
    }
    // 打开公告界面
    @RequestMapping("notice")
    public String notice(){
        return "message/notice";
    }

    // 打开添加公告界面
    @RequestMapping("toaddnotice")
    public String toaddnotice(){
        return "message/addnotice";
    }


    /**
     * 添加数据
     * @return
     */
    @PostMapping("save")
    @ResponseBody
    public ResultInfo save(HttpServletRequest request, Message message){
        /*//获取源对象id
        Integer sourceId = LoginUserUtil.releaseUserIdFromCookie(request);
        //设置留言人ID
        message.setSourceId(sourceId);*/
        //添加数据
        messageService.addMessage(message);
        return success();
    }

    /**
     * 修改数据
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(Message message){
        messageService.updateMessage(message);
        return success();
    }

    /**
     * 删除留言数据
     * @param ids
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteMessage(Integer[] ids){
        messageService.deleteMessage(ids);
        return success();
    }


}
