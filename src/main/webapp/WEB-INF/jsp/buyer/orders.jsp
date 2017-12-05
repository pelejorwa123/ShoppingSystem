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
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
      <tr>
          <th>订单号</th>
          <th>店铺</th>
          <th>商品名</th>
          <th>商品数量</th>
          <th>总价格</th>
          <th>收货地址</th>
          <th>收货电话</th>
          <th>订单时间</th>
      </tr>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.id}</td>
                <td>${order.storeName}</td>
                <td>${order.itemName}</td>
                <td>${order.num}</td>
                <td>${order.totalPrice}</td>
                <td>${order.address}</td>
                <td>${order.mobile}</td>
                <td>${order.createTime}</td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
