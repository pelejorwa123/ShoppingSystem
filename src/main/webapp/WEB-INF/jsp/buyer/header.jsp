<%--
  Created by IntelliJ IDEA.
  User: 14617
  Date: 2017/12/4
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page  isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="/css/global.css" rel="stylesheet" type="text/css" />
    <title>header</title>
</head>

<body>
<div class="header" >
    <div class="logo">
        <a href="" title="网上商城"><img src="/image/logo.ico" width="66" height="66" border="0" alt="网上商城" title="网上商城"></a>
    </div>
    <div class="header_title">
        网上商城
    </div>
    <div class="header_text">
        <ul>
            <li style="color: #FFFFFF">
                欢迎来到网上商城！	   </li>
            <li>
                <a style="color: #FFFFFF" href="/buyer/itemlist" title="首页" target="_blank">首页</a>       </li>
            <li>
                <a style="color: #FFFFFF" href="#" title="登录" target="_blank">${user.username}</a>       </li>
            <li>
                <a style="color: #FFFFFF" href="/buyer/orderlist" title="订单" target="_blank">订单</a>       </li>
            <li>
                <a style="color: #FFFFFF" href="/buyer/shopcart" title="购物车" target="_blank">购物车</a>       </li>
        </ul>
    </div>
</div>
</body>
</html>

