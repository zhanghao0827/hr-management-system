<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>人事管理系统 ——后台管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="This is my page"/>
    <link href="../css/css.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="../js/jquery-migrate-1.2.1.js"></script>
    <script language="javascript" type="text/javascript">
        $(function () {
            /** 给左侧功能菜单绑定点击事件  */
            $("td[id^='navbg']").click(function () {
                /** 获取一级菜单的id */
                var navbgId = this.id;
                /** 获取对应的二级菜单id */
                var submenuId = navbgId.replace('navbg', 'submenu');
                /** 控制二级菜单显示或隐藏  */
                $("#" + submenuId).toggle();
                /** 控制关闭或者开启的图标*/
                $("#" + navbgId).toggleClass("left_nav_expand");

                /** 控制其他的一级菜单的二级菜单隐藏按钮都关闭  */
                $("tr[id^='submenu']").not("#" + submenuId).hide();
                /** 控制其他一级菜单的图片显示关闭  */
                $("td[id^='navbg']").not(this).removeClass().addClass("left_nav_closed");


            })
        })
    </script>
</head>
<body>
<div style="margin:10px;background-color:#FFFFFF; text-align:left;">
    <table width="200" height="100%" border="0" cellpadding="0" cellspacing="0" class="left_nav_bg">
        <tr>
            <td class="left_nav_top">
                <div class="font1">用户管理</div>
            </td>
        </tr>
        <tr valign="top">
            <td class="left_nav_bgshw" height="50">
                <p class="left_nav_link">
                    <img src="../images/left_nav_arrow.gif">&nbsp;&nbsp;
                    <!--不要加
                        /UserServlet --绝对路径   localhost:8080/UserServlet
                        UserServlet  --相对路径
                        由于在view目录下，相对于view目录，即-> /view/UserServlet

                        不要 href="view/UserServlet?action=query"
                            /view/view/UserServlet
                        或者：
                        /view/UserServlet?action=query  为绝对地址
                        localhost:8080/view/UserServlet?action=query

                     -->
                    <a href="UserServlet?action=query" target="main">用户查询</a>
                    </img>
                </p>
                <p class="left_nav_link">
                    <img src="../images/left_nav_arrow.gif">&nbsp;&nbsp;
                    <a href="user_add.jsp" target="main">添加用户</a></img>
                </p>
            </td>
        </tr>
        <tr>
            <td height="2"></td>
        </tr>
        <tr>
            <td id="navbg1" class="left_nav_closed">
                <div class="font1">部门管理</div>
            </td>
        </tr>
        <tr valign="top" id="submenu1" style="display: none">
            <td class="left_nav_bgshw" height="50">
                <p class="left_nav_link"><img src="../images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="dept_list.html"
                                                                                                target="main">部门查询</a></img>
                </p>
                <p class="left_nav_link"><img src="../images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="dept_add.html"
                                                                                                target="main">添加部门</a></img>
                </p>
            </td>
        </tr>
        <tr>
            <td height="2"></td>
        </tr>

        <tr>
            <td id="navbg2" class="left_nav_closed">
                <div class="font1">职位管理</div>
            </td>
        </tr>
        <tr valign="top" id="submenu2" style="display: none">
            <td class="left_nav_bgshw" height="50">
                <p class="left_nav_link"><img src="../images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="satff_list.html"
                                                                                                target="main">职位查询</a></img>
                </p>
                <p class="left_nav_link"><img src="../images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="staff_add.html"
                                                                                                target="main">添加职位</a></img>
                </p>
            </td>
        </tr>
        <tr>
            <td height="2"></td>
        </tr>

        <tr>
            <td id="navbg3" class="left_nav_closed">
                <div class="font1">员工管理</div>
            </td>
        </tr>
        <tr valign="top" id="submenu3" style="display: none">
            <td class="left_nav_bgshw" height="50">
                <p class="left_nav_link"><img src="../images/left_nav_arrow.gif">&nbsp;&nbsp;
                    <a href="EmpServlet?action=query_page" target="main">员工查询</a></img>
                </p>
                <p class="left_nav_link"><img src="../images/left_nav_arrow.gif">&nbsp;
                    &nbsp;<a href="emp_add.jsp" target="main">添加员工</a></img>
                </p>
            </td>
        </tr>
        <tr>
            <td height="2"></td>
        </tr>

        <tr>
            <td id="navbg4" class="left_nav_closed">
                <div class="font1">公告管理</div>
            </td>
        </tr>
        <tr valign="top" id="submenu4" style="display: none">
            <td class="left_nav_bgshw tdbtmline" height="50">
                <p class="left_nav_link">
                    <img src="../images/left_nav_arrow.gif">&nbsp;&nbsp;
                    <a href="NoticeServlet?action=query_page&pageIndex=1&pageSize=5" target="main">公告查询</a></img>
                </p>
                <p class="left_nav_link"><img src="../images/left_nav_arrow.gif">&nbsp;&nbsp;
                    <a href="notice_add.jsp" target="main">添加公告</a></img>
                </p>
            </td>1
        </tr>
        <tr>
            <td height="2"></td>
        </tr>

        <tr>
            <td id="navbg5" class="left_nav_closed" onClick="showsubmenu(5)">
                <div class="font1">下载中心</div>
            </td>
        </tr>
        <tr valign="top" id="submenu5" style="display: none">
            <td class="left_nav_bgshw tdbtmline" height="50">
                <p class="left_nav_link"><img src="../images/left_nav_arrow.gif">&nbsp;&nbsp;
                    <%--
                        1.走后台查询数据
                        2.再回到页面展示
                    --%>
                    <a href="DownloadServlet?action=file_query" target="main">文档查询</a></img>
                </p>
                <p class="left_nav_link"><img src="../images/left_nav_arrow.gif">&nbsp;&nbsp;
                    <a href="file_add.jsp" target="main">上传文档</a></img>
                </p>
            </td>
        </tr>
        <tr>
            <td height="2"></td>
        </tr>

        <tr valign="top">
            <td height="100%" align="center">
                <div class="copycct"><br/>
                    <strong>技术支持：</strong><br><strong>成都布鲁斯科技有限公司</strong><br>Http://www.bruce.com
                </div>
            </td>
        </tr>
        <tr>
            <td height="10"><img src="../images/left_nav_bottom.gif" height="10"></img></td>
        </tr>
        <tr>
            <td height="10" bgcolor="#e5f0ff">&nbsp;</td>
        </tr>
    </table>
</div>
</body>
</html>