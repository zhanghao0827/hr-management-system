<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>人事管理系统——添加用户</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="This is my page"/>
    <link href="../css/css.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="../js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
    <link href="../js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="../js/jquery-migrate-1.2.1.js"></script>
    <script src="../js/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="../js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="../js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="../js/ligerUI/js/plugins/ligerResizable.jss" type="text/javascript"></script>
    <link href="../css/pager.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td height="10"></td>
    </tr>
    <tr>
        <td width="15" height="32"><img src="../images/main_locleft.gif" width="15" height="32"></td>
        <td class="main_locbg font2"><img src="../images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：用户管理 &gt; 添加用户</td>
        <td width="15" height="32"><img src="../images/main_locright.gif" width="15" height="32"></td>
    </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
    <tr valign="top">
        <td>
            <form action="UserServlet" onsubmit="return checkAll();" id="userForm" method="post">
                <!-- 隐藏表单，flag表示添加标记 -->
                <input type="hidden" name="action" value="user_add">
                <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                    <tr>
                        <td class="font3 fftd">
                            <table>
                                <tr>
                                    <td class="font3 fftd">姓&nbsp;名：
                                        <input name="userName" id="userName" size="20" onblur="checkUserName();"/>
                                        <span id="tip_userName"></span>
                                    </td>
                                    <td class="font3 fftd">状&nbsp;态：
                                        <select style="width: 153px" name="userState">
                                            <option value="1">启用</option>
                                            <option value="0">禁用</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="font3 fftd">登录名：
                                        <input name="userLoginName" id="loginName" onblur="checkLoginName();"
                                               size="20"/>
                                        <span id="tip"></span>
                                    </td>
                                    <td class="font3 fftd">密&nbsp;码：
                                        <input name="userPwd" id="password" size="20" onblur="checkPassword();"/>
                                        <span id="tip_password"></span>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor"></td>
                    </tr>
                    <tr>
                        <td align="left" class="fftd">
                            <input type="submit" value="添加">&nbsp;&nbsp;
                            <input type="reset" value="取消 ">
                        </td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
</table>
<div style="height:10px;"></div>
<script type="text/javascript">

    var flag = false;

    function checkLoginName() {
        var loginName = document.getElementById("loginName").value;
        var tip = document.getElementById("tip");
        if (loginName == "") {
            tip.innerText = "用户名不能为空！";
            tip.style.color = "red";
            return false;
        } else {
            //创建异步对象
            var xhr = new XMLHttpRequest();
            //状态改变回调函数
            xhr.onreadystatechange = function () {
                //异步调用请求完毕之后，自动调用
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var result = xhr.responseText;
                    if (result == 'true') {
                        tip.innerText = "登录名已存在！";
                        tip.style.color = "red";
                        flag = false;
                    } else {
                        tip.innerText = "√";
                        tip.style.color = "green";
                        flag = true;
                    }
                }
            };
            //设置发送数据
            // var url = "UserServlet?action=checkLoginName&loginName=" + loginName;
            //设置发送方式，true:异步方式
            // xhr.open("GET", url, true);
            //发送
            // xhr.send();


            //设置发送数据
            var data = "action=checkLoginName&loginName=" + loginName;
            var url = "UserServlet";
            //设置发送方式，true:异步方式
            xhr.open("POST", url, true);
            //模拟表单请求
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            //发送
            xhr.send(data);
        }
    }

    function checkUserName() {
        var userName = document.getElementById("userName").value;
        var tip_userName = document.getElementById("tip_userName");
        if (userName == "") {
            tip_userName.innerText = "用户名不能为空！";
            tip_userName.style.color = "red";
            return false;
        } else {
            tip_userName.innerText = "√";
            tip_userName.style.color = "green";
            return true;
        }
    }

    function checkPassword() {
        var password = document.getElementById("password").value;
        var tip_password = document.getElementById("tip_password");
        if (password == "") {
            tip_password.innerText = "密码不能为空！";
            tip_password.style.color = "red";
            return false;
        } else {
            tip_password.innerText = "√";
            tip_password.style.color = "green";
            return true;
        }
    }

    function checkAll() {
        return checkUserName() && flag && checkPassword();
    }

</script>


</body>
</html>
