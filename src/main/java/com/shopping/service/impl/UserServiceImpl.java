package com.shopping.service.impl;

import com.shopping.common.pojo.AjaxResult;
import com.shopping.mapper.UserMapper;
import com.shopping.pojo.User;
import com.shopping.pojo.UserExample;
import com.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/*
*@author: pele
*@time: 2017/12/4 13:17
*@project: ShoppingSystem
*@description:用户service实现类
*/
@Service
public class UserServiceImpl implements UserService{

    //定义http状态码常量
    private static Integer BAD_REQUEST_CODE = 400;
    //定义用户身份编号
    private static Byte BUYER_TYPE = 2;
    private static Byte STORE_TYPE = 1;

    @Autowired
    UserMapper userMapper;


    @Override
    public User getUserInfo(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    /**
     *@author: pele
     *@time: 2017/12/4 13:38
     *@package: com.shopping.service.impl
     *@descroption:用户注册服务实现
     */
    @Override
    public AjaxResult addUser(String username, String password, String mobile, Byte type) {
        //先进行数据的校验
        //如果用户名为空
        if(null == username || username.trim().equals("")){
            return AjaxResult.build(BAD_REQUEST_CODE,"用户名不能为空");
        }
        //如果密码为空
        if(null == password || password.trim().equals("")){
            return AjaxResult.build(BAD_REQUEST_CODE,"密码不能为空");
        }
        //如果电话为空
        if(null == mobile || mobile.trim().equals("")){
            return AjaxResult.build(BAD_REQUEST_CODE,"电话不能为空");
        }
        //对type进行规整,只要type不为1，就统一修改成2，即买家身份
        if(type != STORE_TYPE){
            type = BUYER_TYPE;
        }
        //检查手机号是否已经被注册，如果已经被注册，则返回注册失败
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andMobileEqualTo(mobile);
        List<User> userList = userMapper.selectByExample(userExample);
        if(null != userList && !userList.isEmpty()){
            return AjaxResult.build(BAD_REQUEST_CODE,"该手机号已经被注册");
        }
        //开始插入新用户
        User user = new User();
        user.setUsername(username);
        //将密码进行md5加密
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        user.setMobile(mobile);
        user.setType(type.byteValue());
        userMapper.insertSelective(user);
        return AjaxResult.ok();
    }

    /**
     *@author: pele
     *@time: 2017/12/4 14:49
     *@package: com.shopping.service.impl
     *@descroption:用户登录服务
     */
    @Override
    public AjaxResult login(User user) {
        if(null == user.getMobile() || user.getMobile().trim().equals("")){
            return AjaxResult.build(BAD_REQUEST_CODE,"手机号不能为空");
        }
        if(null == user.getPassword() || user.getPassword().equals("")){
            return AjaxResult.build(BAD_REQUEST_CODE,"密码不能为空");
        }
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andMobileEqualTo(user.getMobile());
        List<User> users = userMapper.selectByExample(userExample);
        if(null == users || users.isEmpty()){
            return AjaxResult.build(BAD_REQUEST_CODE,"用户名或密码错误");
        }
        User correctUser = users.get(0);
        //比较密码是否相等
        if(!correctUser.getPassword().equals(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()))){
            return AjaxResult.build(BAD_REQUEST_CODE,"用户名或密码错误");
        }
        //去除用户密码
        correctUser.setPassword(null);
        return AjaxResult.ok(correctUser);
    }
}
