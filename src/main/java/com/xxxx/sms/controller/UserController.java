package com.xxxx.sms.controller;

import com.xxxx.sms.base.BaseController;
import com.xxxx.sms.base.ResultInfo;
import com.xxxx.sms.exceptions.ParamsException;
import com.xxxx.sms.service.UserService;
import com.xxxx.sms.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;


    /**
     *  用户登录
     * @param userName
     * @param userPwd
     */
    @PostMapping("login")
    @ResponseBody
    public ResultInfo login(String userName,String userPwd){
        return userService.loginCheck(userName, userPwd);
    }



    /**
     *  修改密码

     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword){
        //获取登录用户的id
        int id = LoginUserUtil.releaseUserIdFromCookie(request);
        userService.userUpdate(id,oldPassword,newPassword,confirmPassword);
        return success();
    }


    //打开修改密码页面
    @RequestMapping("toPasswordPage")
    public String toPasswordPage(){
        return "user/password";
    }
}
