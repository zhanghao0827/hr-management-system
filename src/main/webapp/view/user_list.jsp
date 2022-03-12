<%@ page import="per.hr.resource.manage.sys.bean.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>人事管理系统 ——用户管理</title>
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
    <style type="text/css">
        .main_trbg:hover {
            background-color: #DDE8F9;
        }
    </style>
</head>
<body>
<!-- 导航 -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td height="10"></td>
    </tr>
    <tr>
        <td width="15" height="32"><img src="../images/main_locleft.gif" width="15" height="32"></td>
        <td class="main_locbg font2"><img src="../images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：用户管理 &gt; 用户查询</td>
        <td width="15" height="32"><img src="../images/main_locright.gif" width="15" height="32"></td>
    </tr>
</table>

<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
    <!-- 查询区  -->
    <tr valign="top">
        <td height="30">
            <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                <tr>
                    <td class="fftd">
                        <form name="empform" method="post" id="empform" action="UserServlet">
                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td class="font3">
                                        <input type="hidden" name="action" value="query">
                                        用户名：<input type="text" name="uname"
                                                   value="<%=request.getAttribute("uname")==null?"":request.getAttribute("uname")%>">
                                        用户状态：<select name="state" style="width: 153px">
                                        <option value="-1" <%=request.getAttribute("state").equals("-1") ? "selected" : ""%>>
                                            全部
                                        </option>
                                        <option value="1" <%=request.getAttribute("state").equals("1") ? "selected" : ""%>>
                                            启用
                                        </option>
                                        <option value="0" <%=request.getAttribute("state").equals("0") ? "selected" : ""%>>
                                            禁用
                                        </option>
                                    </select>
                                        <input type="submit" value="搜索"/>
                                        <input id="delete" type="button" value="删除"/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </td>
                </tr>
            </table>
        </td>
    </tr>

    <!-- 数据展示区 -->
    <tr valign="top">
        <td height="20">
            <table width="100%" border="1" cellpadding="5" cellspacing="0"
                   style="border:#c2c6cc 1px solid; border-collapse:collapse;">
                <tr class="main_trbg_tit" align="center">
                    <td><input type="checkbox" name="checkAll" id="checkAll"></td>
                    <td>登录名</td>
                    <td>密码</td>
                    <td>用户名</td>
                    <td>状态</td>
                    <td>创建时间</td>
                    <td>状态更新</td>
                    <td align="center">操作</td>
                </tr>
                <%
                    List<User> users = (List<User>) request.getAttribute("users");
                    if (users != null) {
                        for (User user : users) {
                            if (user.getUserId() == ((User) session.getAttribute("user")).getUserId()) {
                                continue;
                            }
                %>

                <tr align="center" class="main_trbg" onMouseOver="move(this);" onMouseOut="out(this);">
                    <td><input type="checkbox" value="1"></td>
                    <td><%=user.getUserLoginName()%>
                    </td>
                    <td><%=user.getUserPwd()%>
                    </td>
                    <td><%=user.getUserName()%>
                    </td>
                    <td><%=user.getState() == 1 ? "启用" : "禁用"%>
                    </td>
                    <td><%=user.getCreateTime()%>
                    </td>
                    <td><a href="#"
                           onclick="changeState(this,<%=user.getUserId()%>);"><%=user.getState() == 0 ? "启用" : "禁用"%>
                    </a></td>
                    <td align="center" width="40px;">
                        <img title="更新" onclick="updateUser(<%=user.getUserId()%>);" src="../images/update.gif"/>
                        <img onclick="deleteUser(<%=user.getUserId()%>,this);" title="删除" src="../images/top_exit.gif"/>
                    </td>
                </tr>

                <%
                        }
                    }
                %>

            </table>
        </td>
    </tr>
    <!-- 分页标签 -->
    <tr valign="top">
        <td align="center" class="font3">
        </td>
    </tr>
</table>
<div style="height:10px;"></div>

<script>
    function changeState(a, id) {
        var state = 0;
        var text = a.innerText;
        if (text == "启用") {
            state = 1;
        }

        if (confirm("是否" + text + "？")) {
            //发送更新请求
            var data = "action=changeState&id=" + id + "&state=" + state;
            $.post('UserServlet', data, function (result) {
                if (result == "true") {
                    a.innerText = (state == 1) ? "禁用" : "启用";
                    //jquery定位元素更新
                    $(a).parent().prev().prev().text(text);
                } else {
                    alert('更新失败！');
                }
            }, 'text');
        }
    }

    function deleteUser(id, img) {
        if (confirm("是否删除?")) {
            $.get('UserServlet', 'action=delete&id=' + id, function (result) {
                if (result == "true") {
                    $(img).parent().parent().fadeOut(1000);
                } else {
                    alert("删除失败！");
                }
            });
        }
    }

    function updateUser(id) {
        location.href = "UserServlet?action=user_goUpdate&id=" + id;
    }
</script>
</body>
</html>
