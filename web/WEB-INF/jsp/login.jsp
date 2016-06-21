<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AaahhhXin
  Date: 2016/6/16
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    function load() {
        var reg = new RegExp("(^|&)" + "result" + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            var result = unescape(r[2]);
            if (result == "nouser") {
                alert("登录失败！没有这个用户！");
            } else if (result == "wrongpassword") {
                alert("登录失败！密码错误！")
            } else if (result == "registersuccess") {
                alert("注册成功！")
            }
        }
    }
</script>
<!DOCTYPE html>
<html>
<head>
    <title>login</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/6-21.css"/>">
</head>
<body onload="load()">
<h1>登陆</h1>
<div id="main">
    <form action="dologin" method="post">
        <table id="text-input">
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="登录"/></td>
            </tr>
            <tr>
                <td colspan="2"><a href="register">还没注册？点此注册</a></td>
            </tr>
    </form>
</div>
</body>
</html>
