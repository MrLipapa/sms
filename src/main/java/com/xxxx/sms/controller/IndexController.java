package com.xxxx.sms.controller;

import com.xxxx.sms.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {
    /**
     * 系统登录页
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    // 系统界面欢迎页
    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    // 主页面
    @RequestMapping("main")
    public String main(){
        return "main";
    }
    // 留言页面
    @RequestMapping("massage")
    public String massage(){
        return "massage";
    }

}
