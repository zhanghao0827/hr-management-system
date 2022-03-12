<%@ page import="per.hr.resource.manage.sys.bean.Notice" %>
<%@ page import="per.hr.resource.manage.sys.utils.PageUtils" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>人事管理系统 ——公告管理</title>
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
        <td class="main_locbg font2"><img src="../images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：公告管理 &gt; 公告查询</td>
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
                        <form name="noticeform" method="post" id="noticeform" action="NoticeServlet">
                            <input type="hidden" name="action" value="query_page"> </input>
                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td class="font3">
                                        公告名称：<input type="text" name="noticeTitle"
                                                    value="${requestScope.pageUtils.t.noticeTitle}">
                                        公告内容：<input type="text" name="noticeContent"
                                                    value="${requestScope.pageUtils.t.noticeContent}">
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
                    <td>序号</td>
                    <td>公告名称</td>
                    <td>公告内容</td>
                    <td>创建时间</td>
                    <td>公告人</td>
                    <td>操作</td>
                    <td>预览</td>
                </tr>

                <%
                    PageUtils<Notice> pageUtils = (PageUtils<Notice>) request.getAttribute("pageUtils");
                    if (pageUtils != null) {
                        List<Notice> notices = pageUtils.getList();
                        for (Notice notice : notices) {
                %>
                <tr id="data_0" align="center" class="main_trbg" onMouseOver="move(this);" onMouseOut="out(this);">
                    <td><input type="checkbox" id="box_0" value="10"></td>
                    <td><%=notice.getNoticeId()%>
                    </td>
                    <td><%=notice.getNoticeTitle()%>
                    </td>
                    <td><%=notice.getNoticeContent()%>
                    </td>
                    <td><%=notice.getNoticeTime()%>
                    </td>
                    <td><%=notice.getNoticeUser()%>
                    </td>
                    <td align="center" width="40px;">
                        <a href="NoticeServlet?action=notice_goUpdate&id=<%=notice.getNoticeId()%>&pageIndex=<%=pageUtils.getPageIndex()%>">
                            <img title="修改" src="../images/update.gif"/>
                        </a>
                    </td>
                    <td align="center" width="40px;">
                        <a href="#" id="prev_10">
                            <img onclick="deleteNotice(<%=notice.getNoticeId()%>,<%=pageUtils.getPageIndex()%>);"
                                 title="预览" src="../images/prev.gif"/>
                        </a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
                <tr>
                    <td colspan="7" align="center">
                        <a href="javascript:goPage(1);">首页</a>

                        <a href="javascript:goPage(<%=pageUtils.getPageIndex()-1%>);" <%=pageUtils.getPageIndex() == 1 ? "style='display: none'" : ""%>>上一页</a>

                        <%
                            List<Integer> numbers = pageUtils.getNumbers();
                            if (numbers != null) {
                                for (Integer number : numbers) {
                        %>
                        <a href="javascript:goPage(<%=number%>);" <%=pageUtils.getPageIndex() == number ? "style='color: red;font-weight: bolder;'" : ""%>><%=number%>
                        </a>
                        <%
                                }
                            }
                        %>


                        <a href="javascript:goPage(<%=pageUtils.getPageIndex()+1%>);" <%=pageUtils.getPageIndex() == pageUtils.getPageCount() ? "style='display: none'" : ""%>>下一页</a>
                        <a href="javascript:goPage(<%=pageUtils.getPageCount()%>);">尾页</a>

                        <a href="#">[当前<%=pageUtils.getPageIndex()%>/<%=pageUtils.getPageCount()%>]</a>
                        <a href="#">[<%=pageUtils.getTotalCount()%>]</a>
                    </td>
                </tr>
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
    function goPage(pageIndex) {
        //1.传递pageIndex到后台
        $("#noticeform").prop("action", "NoticeServlet?action=query_page&pageIndex=" + pageIndex);
        //2.表单的查询条件也提交  noticeTitle,noticeContent
        $("#noticeform").submit();
    }

    function deleteNotice(id, pageIndex) {
        if (confirm("是否删除?")) {
            location.href = "NoticeServlet?action=notice_delete&id=" + id + "&pageIndex=" + pageIndex;
        }
    }
</script>
</body>
</html>
