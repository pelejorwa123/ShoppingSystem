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
    <title>修改商品</title>
</head>
<body>
    <form action="/store/updateItem" method="post">
       <table border="2" cellspacing="2" cellpadding="2">
           <tr>
               <td>商品名</td>
               <td>
                   <input name="id" type="hidden" value="${item.id}">
                   <input name="name" value="${item.name}">
               </td>
           </tr>
           <tr>
               <td>价格</td>
               <td><input name="price" value="${item.price}"></td>
           </tr>
           <tr>
               <td>库存</td>
               <td>
                   <input name="num" value="${item.num}">
                   <input type="hidden" name="status" value="${item.status}">
               </td>

           </tr>
           <tr>
               <td>商品图片</td>
               <td>
                   <input type="file">
                   <input type="hidden" name="imgUrl" value="${item.imgUrl}">
               </td>
           </tr>
           <tr>
               <td>描述</td>
               <td style="height: 100px"><input name="description" type="text" style="height: 100%;width:100%" value="${item.description}"></td>
           </tr>
           <tr>
               <td><input type="submit" value="提交"></td>
           </tr>
       </table>
    </form>

</body>
</html>
