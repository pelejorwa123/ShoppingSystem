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
    <link href="/css/global.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#login").click(function(){
                $.ajax({
                    url:"/user/login",
                    type:"post",
                    dataType:"json",
                    contentType: "application/json; charset=utf-8",
                    data:JSON.stringify(GetJsonData()),
                    success:function(data){
                        if(data.status==200){
                            if(data.data.type==2){
                                window.location.href="/buyer/itemlist";
                            }else {
                                window.location.href="/store/itemlist";
                            }
                        }else{
                            alert("用户名或者密码错误");
                        }

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
<div class="background">
    <img src="/image/background.png" width="100%" height="100%"/>
</div>
<div class="middle">
    <center style="font-size:36px;" >登录界面</center>
    <form action="/user/login" method="post">
        <div class="text_sign">
            <p>
                <label for="mobile">手机号：</label>
                <input type="text" name="mobile" id="mobile" />
            </p>
            <p>
                <label for="password">密&nbsp;&nbsp;&nbsp;码：</label>
                <input type="password" name="password" id="password" />
            </p>
            <p>
                <a href="/user/toSign" style="text-decoration: underline">注册</a>
            </p>
        </div>
        <div class="submit_sign">
            <input type="button" name="login" id="login" value="登录"  >
        </div>
    </form>
</div>
</body>
</html>
