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
    <title>我的订单</title>
</head>
<body>
<!--header begin-->
<div class="mid_index" style="
			height:100px; top:0;
			width:990px;margin:0 auto;
			border-width:0px;"
     scrolling="no">
<%--    <iframe src="header"style="
			height:100px; top:0;
			width:990px;margin:0 auto;
			border-width:0px;"
            scrolling="no">
    </iframe>--%>
    <jsp:include page="header.jsp"></jsp:include>
</div>
<!--search begin-->
<div class="search" >
    <form action="/store/order/search" method="post">
        输入要搜索的商品名字：
        <input type="text" name="name" id="search_for" value="${name}" />
        <input type="submit" name="search" id="search" value="搜索">
    </form>
</div>
<!--main content begin-->
<div class="content">
    <table  style="width:990px;margin:0 auto; border-width:0px;" scrolling="no">
        <tr>
            <th align="left">订单号</th>
            <th align="left">买家ID</th>
            <th align="left">商品名</th>
            <th align="left">商品数量</th>
            <th align="left">总价格</th>
            <th align="left">收货地址</th>
            <th align="left">收货电话</th>
            <th align="left">订单时间</th>
        </tr>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.id}</td>
                <td>${order.buyerId}</td>
                <td>${order.itemName}</td>
                <td>${order.num}</td>
                <td>${order.totalPrice}</td>
                <td>${order.address}</td>
                <td>${order.mobile}</td>
                <td><fmt:formatDate value="${order.createTime}" type="both"/></td>
            </tr>
        </c:forEach>
    </table>
</div><!--content end-->


<!--footer begin-->
<div class="mid_index" style="height:100px;
			width:990px;
			border-width:0px;"
     scrolling="no">
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>
