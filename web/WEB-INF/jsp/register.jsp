<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AaahhhXin
  Date: 2016/6/14
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/6-21.css"/>">
    <title>注册页面</title>
    <script>
        function load() {
            var reg = new RegExp("(^|&)" + "result" + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                var result = unescape(r[2]);
                if (result == "registerfail") {
                    alert("注册失败！");
                }
            }
        }
    </script>
</head>
<body onload="load()">
<h1>注册</h1>
<div id="main">
    <form action="doregister" method="post">
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
                <td colspan="2"><a href="login">已经注册？点此登录。</a></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
