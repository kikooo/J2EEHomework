<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AaahhhXin
  Date: 2016/6/11
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>asd</title>
    <link href="<c:url value="/css/test.css" />" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function dochange(o) {
            document.getElementById('hid-back').style.display = "block";
            document.getElementById('alt-hid').style.display = "block";
            var parent = o.parentNode;
            var spans = parent.getElementsByTagName("span");
            var name = spans.item(0);
            var number = spans.item(1);
            var id = spans.item(2);
            document.getElementById("alt-name").value = name.innerHTML;
            document.getElementById("alt-num").value = number.innerHTML;
            document.getElementById("alt-id").value = id.innerHTML;

        }
        function doclose() {
            document.getElementById('hid-back').style.display = "none";
            document.getElementById('alt-hid').style.display = "none";
            document.getElementById('add-hid').style.display = "none";
            document.getElementById('repass-hid').style.display = "none";
        }

        function doadd() {
            document.getElementById('hid-back').style.display = "block";
            document.getElementById('add-hid').style.display = "block";
        }
        function dodelete(o) {
            var parent = o.parentNode;
            var spans = parent.getElementsByTagName("span");
            var name = spans.item(0);
            var id = spans.item(2)
            var con = confirm("确认删除联系人:" + name.innerHTML + "吗?");
            if (con == true) {
                window.location.href = "delete?telsid=" + id.innerHTML;
            }
        }
        function check(o) {
            var spans = o.getElementsByTagName("input");
            var span = spans.item(1);
            var num = span.value;
            var reg = /^1[358]\d{9}/;
            if (!reg.test(num)) {
                alert("手机号码格式不正确!");
                o.value = "";
                return false;
            } else
                return true;
        }
        function repass() {
            document.getElementById('hid-back').style.display = "block";
            document.getElementById('repass-hid').style.display = "block";
        }
        function load() {
            var reg = new RegExp("(^|&)" + "result" + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                var result = unescape(r[2]);
                if (result == "login") {
                    alert("登录成功！");
                } else if (result == "addsuccess") {
                    alert("添加成功！")
                } else if (result == "addfail") {
                    alert("添加失败！")
                } else if (result == "changesuccess") {
                    alert("更改成功！")
                } else if (result == "changefail") {
                    alert("更改失败！")
                } else if (result == "deletesuccess") {
                    alert("删除成功！")
                } else if (result == "deletefail") {
                    alert("删除失败！")
                } else if (result == "nouser") {
                    alert("没有该用户！")
                } else if (result == "wrongpassword") {
                    alert("密码错误！")
                } else if (result == "passwordchanged") {
                    alert("密码更改成功！！")
                }
            }
        }
        function showall() {
            window.location.href = "index"

        }
    </script>
</head>
<body onload="load()">
<nav id="nav-bar">
    <h1>通讯录列表</h1>
    <form method="get" action="research">
        <input type="text" name="keyword" value="搜索联系人"/>
        <input type="submit" value="搜索"/>
        <input type="button" value="添加联系人" onclick="doadd()"/>
        <input type="button" value="显示全部" onclick="showall()"/>
    </form>
    <div id="user">
        用户名：${sessionScope.get("user").username}
        <a href="exit">[退出]</a>
        <a href="#"><span onclick="repass()" style="width:100px;">[修改密码]</span></a>
    </div>
</nav>
<div id="hid-back" class="hidden" style="display:none;"></div>
<div id="repass-hid" class="hidden" style="display:none;">
    <form method="post" action="changepassword">
        <h2>修改密码</h2>
        <span>旧密码：</span><input type="pass" name="password" id="pass"/>
        <br>
        <span>新密码：</span><input type="pass" name="newpassword" id="repass"/>
        <br>
        <input type="hidden" name="userid" value="${sessionScope.get("user").userid}">
        <input class="btn" type="button" value="取消" onclick="doclose()"/>
        <input class="btn" type="submit" value="确认修改"/>
    </form>
</div>

<div id="alt-hid" class="hidden" style="display:none;">
    <form method="post" action="change" onsubmit="return check(this)">
        <h2>修改联系人</h2>
        <span>姓名：</span><input id="alt-name" type="text" name="telsname" value="111"/>
        <br>
        <span>联系方式：</span><input id="alt-num" type="text" name="telnumber" value="123456789"/>
        <br>
        <input id="alt-id" type="text" name="telsid" value="1" style="display: none;"/>
        <input class="btn" type="button" value="取消" onclick="doclose()"/>
        <input class="btn" type="submit" value="确认修改"/>
    </form>
</div>
<div id="add-hid" class="hidden" style="display:none;">
    <form method="post" action="add" onsubmit="return check(this)">
        <h2>添加联系人</h2>
        <span>姓名：</span><input type="text" name="telsname"/>
        <br>
        <span>联系方式：</span><input type="text" name="telnumber"/>
        <br>
        <input type="hidden" name="creatorid" value="${sessionScope.get("user").userid}">
        <br>
        <input class="btn" type="button" value="取消" onclick="doclose()"/>
        <input class="btn" type="submit" value="确认添加"/>
    </form>
</div>
<c:choose>
    <c:when test="${telslist.size()==0}">
        <div class="con-col">
            <div class="list-item">
                <p>还没有添加过联系人，请添加联系人</p>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="con-col">
            <c:forEach items="${telslist}" var="tel">
                <div class="list-item">
                    姓名：<span class="con-name">${tel.telsname}</span>
                    联系方式：<span class="con-tel">${tel.telnumber}</span>
                    <span class="con-id">${tel.telsid}</span>
                    <button onclick="dodelete(this)" class="btn">删除</button>
                    <button onclick="dochange(this)" class="btn">更改</button>
                    <hr>
                </div>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>