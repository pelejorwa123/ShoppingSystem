package com.shopping.controller;

import com.shopping.common.pojo.AjaxResult;
import com.shopping.common.utils.JsonUtils;
import com.shopping.pojo.User;
import com.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
*@author: pele
*@time: 2017/12/4 13:19
*@project: ShoppingSystem
*@description:用户相关controller
*/
@Controller
@RequestMapping("user")
public class UserController {
    private static int OK_CODE = 200;
    private static int SESSION_EXPIRE_TIME = 3600;

    @Autowired
    UserService userService;

    @RequestMapping("getInfo")
    @ResponseBody
    public User getUserInfoById(Integer id){
        User user = userService.getUserInfo(id);
        return user;
    }

    /**
     *@author: pele
     *@time: 2017/12/4 16:38
     *@package: com.shopping.controller
     *@descroption:展示登录页面
     */
    @RequestMapping("loginpage")
    public String showLoginPage(){
        return "/user/login";
    }

    /**
     *@author: pele
     *@time: 2017/12/4 13:28
     *@package: com.shopping.controller
     *@descroption:用户注册
     */
    @RequestMapping("register")
    @ResponseBody
    public AjaxResult registerUser(@RequestBody User user){
        AjaxResult result = userService.addUser(user.getUsername(),user.getPassword(),user.getMobile(),user.getType());
        return result;
    }

    /**
     *@author: pele
     *@time: 2017/12/4 14:13
     *@package: com.shopping.controller
     *@descroption:用户登录
     */
    @RequestMapping("login")
    @ResponseBody
    public AjaxResult login(@RequestBody User user, HttpServletRequest request){
        AjaxResult ajaxResult = userService.login(user);
        //判断是否用户登录成功，若成功，加入session中
        if(ajaxResult.getStatus() == OK_CODE){
            User corrctUser = (User) ajaxResult.getData();
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("UserInfo", corrctUser);
            System.out.println(httpSession.getId());
            //设置session过期时间
            httpSession.setMaxInactiveInterval(SESSION_EXPIRE_TIME);
        }
        return ajaxResult;
    }
}
