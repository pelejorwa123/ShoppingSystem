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
    <title>我的购物车</title>
    <script type="text/javascript">
        function delCart(itemId) {
            $.ajax({
                url:"http://localhost:8080/buyer/delCart?itemId="+itemId,
                type:"post",
                contentType: "application/json; charset=utf-8",
                success:function(data){
                    if(data.status==200){
                        productId = "#product"+itemId;
                        $(productId).remove();
                    }else{
                        alert("移除购物车失败");
                    }

                }
            });
        }
    </script>
    <script type="text/javascript">
        function toBuy(itemId) {
            numId = "#num"+itemId;
            num = $(numId).val();
            url = "/buyer/toBuy?itemId="+itemId+"&num="+num;
            window.location.href=url;
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
<!--main content begin-->
<div class="content">
    <c:forEach items="${carts}" var="cart">
        <div class="product" id="product${cart.itemId}">
            <div class="photo">
                <img src="${cart.imgUrl}" style="height: 90px;width: 120px"/></div>
            <br>
            <div class="intro">
                商品名称：${cart.itemName}(ID：${cart.itemId})</br>
                店铺名称：${cart.storeName}(ID：${cart.storeId})</br>
                描述：${cart.description}</br>
            </div>
            <div class="submit_buy">
                <ul><br>
                    <li>
                        购买数量:<input type="text" name="num" id="num${cart.itemId}" size="1" value="${cart.num}"/>
                        &nbsp;
                        <button style="border: none" value="移除" id="${cart.itemId}" onclick="delCart(this.id)">移除</button>
                    </li>
                    <br/>
                    <li><a name="${cart.itemId}" href="javascript:void(0)" onclick="toBuy(this.name)" style="text-decoration:underline;color: coral">去结算</a></li>
                </ul></div>
        </div><!--product end-->
    </c:forEach>
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
