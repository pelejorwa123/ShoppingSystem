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
    <form action="/buyer/search" method="post">
        <input name="itemName" value="${itemName}"/>
        <input type="submit" value="搜索">
    </form>
    <table>
      <tr>
          <th>商品id</th>
          <th>商品名</th>
          <th>店铺名</th>
          <th>价格</th>
          <th>商品库存</th>
      </tr>
        <c:forEach items="${items}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.storeName}</td>
                <td>${item.price}</td>
                <td>${item.num}</td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
