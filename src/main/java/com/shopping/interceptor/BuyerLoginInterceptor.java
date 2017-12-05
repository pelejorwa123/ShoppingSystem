package com.shopping.interceptor;

import com.shopping.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
*@author: pele
*@time: 2017/12/4 16:25
*@project: ShoppingSystem
*@description:买家登录权限拦截器
*/
public class BuyerLoginInterceptor implements HandlerInterceptor{

    private static int SESSION_EXPIRE_TIME = 3600;

    //定义用户身份编号
    private static Byte BUYER_TYPE = 2;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("UserInfo");
        if(null == user || user.getType()!=BUYER_TYPE.byteValue()){
            response.sendRedirect("/user/loginpage");
            return false;
        }
        //重新刷新session的过期时间
        httpSession.setMaxInactiveInterval(SESSION_EXPIRE_TIME);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
