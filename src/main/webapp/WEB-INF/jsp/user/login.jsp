<%--
  Created by IntelliJ IDEA.
  User: 14617
  Date: 2017/12/4
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#commit").click(function(){
                $.ajax({
                    url:"http://localhost:8080/user/login",
                    type:"post",
                    dataType:"json",
                    contentType: "application/json; charset=utf-8",
                    data:JSON.stringify(GetJsonData()),
                    success:function(data){
                        window.location.href="/buyer/test";
                    }
                });
                function GetJsonData() {
                    var json = {
                        "mobile":$("#mobile").val(),
                        "password":$("#password").val(),
                    };
                    return json;
                }
            });
        });

    </script>
    <title>User</title>
</head>
<body>
<p>这里是测试界面</p>
用户名：<input type="text" id="mobile"><br>
密码  ：<input type="text" id="password"><br>
提交:<input type="button" id="commit" value="提交">

</body>
</html>
