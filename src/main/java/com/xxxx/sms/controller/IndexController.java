package com.xxxx.sms.controller;
import com.xxxx.sms.base.BaseController;
import com.xxxx.sms.service.PermissionService;
import com.xxxx.sms.service.UserService;
import com.xxxx.sms.utils.LoginUserUtil;
import com.xxxx.sms.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController extends BaseController {
    @Resource
    private UserService userService;

    @Resource
    private PermissionService permissionService;
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


    /**
     * 后端管理主页面
     * @return
     */
    @RequestMapping("main")
    public String main(HttpServletRequest request){
        //将用户数据查询出来，设置到作用域中
        int id = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.selectByPrimaryKey(id);
        request.setAttribute("user",user);

        //当用户登录时,查询该用户已有的权限码,存放在session作用域中,以便前台判断使用
        List<String> permissions = permissionService.selectUserRoleAvlValue(id);
        request.getSession().setAttribute("permissions",permissions);

        return "main";
    }
}
