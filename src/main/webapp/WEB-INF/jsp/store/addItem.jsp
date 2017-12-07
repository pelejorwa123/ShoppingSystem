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
    <script type="text/javascript" src="/js/jquery.form.js"></script>
    <title>网上商城</title>

    <script type="text/javascript">
        function uploadPic() {
            //jquery.form.js
            var options={
                url:"/store/uploadPic",
                type:"post",
                success:function(data){
                    if(data.status==200){
                        $("#imgUrl").val(data.msg);
                    }else {
                        alert(data.msg);
                    }
                }
            };
            $("#jvForm").ajaxSubmit(options);
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
    <center style="font-size:36px;" >添加商品</center>
    <form id="jvForm" action="/store/addItem" method="post">
        <div class="text_sign">
            <p>
                <label for="name">商品名：</label>
                <input type="text" name="name" id="name" />
            </p>
            <p>
                <label for="price">价格：</label>&nbsp;&nbsp;&nbsp;
                <input type="text" name="price" id="price" size="7" />
                <br>
                <br>
                <label for="num">库存：</label>&nbsp;&nbsp;&nbsp;
                <input name="num" id="num" size="7" />
            </p>
            <p>
                <label for="price">图片：</label>&nbsp;&nbsp;&nbsp;
                <input type="file" name="pic" onchange="uploadPic()"/>
                <input type="hidden" name="imgUrl" id="imgUrl"/>
            </p>
            商品介绍（100字以内）：
        </div>
        <center>
            <textarea name="description" id="description" rows=5 cols=60></textarea></center><p>
        <div class="submit_sign">
            <input style="height: 30px;width: 50px;" type="submit" name="login" id="login" value="提交">
        </div>
    </form>
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
