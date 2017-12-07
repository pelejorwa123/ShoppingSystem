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
    <script type="text/javascript" src="/js/jquery-1.7.1.min.js"></script>
    <title>网上商城</title>

    <script type="text/javascript">
        function addCart(itemId) {
            $.ajax({
                url:"http://localhost:8080/buyer/addCart?itemId="+itemId,
                type:"post",
                dataType:"json",
                contentType: "application/json; charset=utf-8",
                success:function(data){
                    if(data.status==200){
                       alert("加入购物车成功")
                    }else{
                        alert("加入购物车失败");
                    }

                }
            });
        }
    </script>
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
    <form action="/buyer/search" method="post">
        输入要搜索的内容，并勾选范围：
        <input type="text" name="name" id="search_for" value="${name}"/>
        <input type="submit" name="search" id="search" value="搜索">
        <input type="radio" name="type" value="1" <c:if test="${type !=2}">checked="checked"</c:if>>商品
        <input type="radio" name="type" value="2" <c:if test="${type ==2}">checked="checked"</c:if>>店铺
    </form>
</div>
<!--main content begin-->
<div class="content">
    <table  style=" width:990px;margin:0 auto; border-width:0px;" scrolling="no">
        <tr>
            <th align="left">图片</th>
            <th align="left">商品名</th>
            <th align="left">店铺</th>
            <th align="left">价格</th>
            <th align="left">库存</th>
            <th align="left">商品描述</th>
            <th align="left">操作</th>
        </tr>
        <c:forEach items="${items}" var="item">
            <tr>
                <td>
                    <img src="${item.imgUrl}" style="width: 120px;height: 90px"/>
                </td>
                <td>${item.name}</td>
                <td>${item.storeName}</td>
                <td>${item.price}</td>
                <td>${item.num}</td>
                <td>${item.description}</td>
                <td><button style="height: 30px;border:none" id="${item.id}" onclick="addCart(this.id)" >加入购物车</button></td>
            </tr>
        </c:forEach>
    </table>
</div><!--content end-->


<!--footer begin-->
<div class="mid_index">
    <iframe src="footer" style="height:100px;
			width:990px;
			border-width:0px;"
            scrolling="no" ></iframe>
</div>
</body>
</html>
