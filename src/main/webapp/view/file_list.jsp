<%@ page import="per.hr.resource.manage.sys.bean.Download" isELIgnored="false" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>人事管理系统 ——文档管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="This is my page"/>
    <link href="../css/css.css" type="text/css" rel="stylesheet"/>
    <link href="../css/pager.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="../js/jquery-migrate-1.2.1.js"></script>
    <link href="../js/ligerUI/skins/Aqua/css/ligerui-dialog.css" rel="stylesheet" type="text/css"/>
    <script src="../js/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="../js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="../js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="../js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
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
        <td class="main_locbg font2"><img src="../images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：文档管理 &gt; 文档查询</td>
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
                        <form name="documentform" method="post" id="documentform" action="DownloadServlet">
                            <input type="hidden" name="action" value="file_query"/>
                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td class="font3">
                                        标题：
                                        <%--
value=                                      "<%=request.getParameter("title")==null?"":request.getParameter("title")%>"
                                        --%>
                                        <input type="text" name="downloadTittle" value="${requestScope.title}"/>
                                        <input type="submit" value="搜索"/>
                                        <input type="button" id="delete" value="删除">
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
                    <td><input type="checkbox" id="checkAll"></td>
                    <td>标题</td>
                    <td>创建时间</td>
                    <td>创建人</td>
                    <td>描述</td>
                    <td>操作</td>
                    <td>下载</td>
                </tr>
                <%
                    List<Download> downloads = (List<Download>) request.getAttribute("downloads");
                    if (downloads != null) {
                        for (Download download : downloads) {
                %>
                <tr ondblclick="down(16);" class="main_trbg" align="center" id="data_0">
                    <td><input type="checkbox" id="box_0" value="16"></td>
                    <td><%=download.getDownloadTittle()%>
                    </td>
                    <td><%=download.getDownloadCreateTime()%>
                    </td>
                    <td><%=download.getUserName()%>
                    </td>
                    <td><%=download.getDownloadDes()%>
                    </td>
                    <td align="center" width="40px;">
                        <a href="DownloadServlet?action=file_goUpdate&id=<%=download.getDownloadId()%>">
                            <img title="修改" src="../images/update.gif"/>
                        </a>
                    </td>
                    <td align="center" width="40px;">
                        <a href="DownloadServlet?action=file_download&id=<%=download.getDownloadId()%>" id="down_16">
                            <img width="20" height="20" title="下载" src="../images/downLoad.png"/>
                        </a>
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
</body>
</html>