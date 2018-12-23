<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2018/12/12
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>J2EE</title>
    <script src="js/jquery.min.js"></script>
</head>
<body>
<div style="width:500px;margin:0 auto;" >

    <label for="username" style="font-size:18px" >用户名：</label>
    <div style="height:35px;width:400px;position:relative;display:inline" >   <!--相关位置--> <!--此处为相关位置与绝对位置联合使用-->
        <input id="username" type="text" style="height:30px;width:350px;padding-right:50px;">
        <span style="position:absolute;right:18px;top:2px;height:16px;width:16px;display:inline-block;"></span>  <!--绝对位置-->
    </div>
    <br/>
    <br/>
    <label for="password" style="font-size:18px" >&nbsp密码：</label>
    <div style="height:35px;width:400px;position:relative;display:inline" >   <!--相关位置--> <!--此处为相关位置与绝对位置联合使用-->
        <input id="password" type="password" style="height:30px;width:350px;padding-right:50px;">
        <span style="position:absolute;right:16px;top:2px;height:16px;width:16px;display:inline-block;"></span>  <!--绝对位置-->
    </div>
    <br/>
    <div style="width:426px;float: right;">
        <input type="button"  value="登录" onclick="login()" style="height:30px;width:400px;background-color:red; "/>
        <BUTTON onclick="login()"></BUTTON>
    </div>

    <form action="/login" method="get">
        <input type="submit">
    </form>
</div>
</body>
<script type="text/javascript">
    function login() {
        var url="/login"
        $.ajax({
            type:"get",
            async:true,
            url:url,
            data:{
                username:document.getElementById("username").value,
                password:document.getElementById("password").value
            },
            success:function(data){
                console.log(data)
            },
            error:function (err) {

            }

        });

    }


    function jump(){
        window.location.href="shopcar.html"
    }
</script>
</html>
