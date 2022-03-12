<%@ page import="per.hr.resource.manage.sys.bean.Emp" %>
<%@ page import="per.hr.resource.manage.sys.utils.PageUtils" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>人事管理系统 ——员工管理</title>
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
        <td class="main_locbg font2"><img src="../images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：员工管理 &gt; 员工查询</td>
        <td width="15" height="32"><img src="../images/main_locright.gif" width="15" height="32"></td>
    </tr>
</table>

<script type="text/javascript">
    $(function () {
        $.get("StaffServlet", "action=getStaffs", function (result) {
            //JSON字符串转JOSN对象
            var staffs = JSON.parse(result);
            for (var i = 0; i < staffs.length; i++) {
                var staffName = staffs[i].staffName;
                var staffId = staffs[i].staffId;
                //绑定下拉框
                $("[name=staffId]").append("<option value='" + staffId + "'>" + staffName + "</option>");
            }
            $("[name=staffId]").val(${requestScope.pageUtils.t.staffId});
        });

        $.getJSON("DeptServlet", "action=getDepts", function (depts) {
            for (var i = 0; i < depts.length; i++) {
                var did = depts[i].did;
                var dName = depts[i].dName;
                $("[name=did]").append("<option value='" + did + "'>" + dName + "</option>");
            }
            $("[name=dId]").val(${requestScope.pageUtils.t.dId});
        });
    });
</script>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
    <!-- 查询区  -->
    <tr valign="top">
        <td height="30">
            <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                <tr>
                    <td class="fftd">
                        <form name="empform" method="post" id="empform" action="EmpServlet">
                            <input type="hidden" name="action" value="query_page"/>
                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td class="font3">
                                        职位：
                                        <select name="staffId" style="width:143px;">
                                            <option value="-1">--请选择职位--</option>
                                        </select>

                                        姓名：<input type="text" name="empName"
                                                  value="${requestScope.pageUtils.t.empName}">
                                        身份证号码：<input type="text" name="cardNum" maxlength="18"
                                                     value="${requestScope.pageUtils.t.cardNum}">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="font3">
                                        性别：
                                        <select name="gender" style="width:143px;">
                                            <option value="-1">--请选择性别--</option>
                                            <option value="男" ${requestScope.pageUtils.t.gender=="男"?"selected":""}>男
                                            </option>
                                            <option value="女" ${requestScope.pageUtils.t.gender=="女"?"selected":""}>女
                                            </option>
                                        </select>
                                        手机：<input type="text" name="phone" value="${requestScope.pageUtils.t.phone}">
                                        所属部门：<select name="did" style="width:100px;">
                                        <option value="-1">--部门选择--</option>
                                    </select>&nbsp;
                                        <input type="button" onclick="goPage(1)" value="搜索"/>
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
                    <td>姓名</td>
                    <td>性别</td>
                    <td>手机号码</td>
                    <td>邮箱</td>
                    <td>职位</td>
                    <td>学历</td>
                    <td>身份证号码</td>
                    <td>部门</td>
                    <td>联系地址</td>
                    <td>建档日期</td>
                    <td align="center">操作</td>
                </tr>

                <%
                    PageUtils<Emp> pageUtils = (PageUtils<Emp>) request.getAttribute("pageUtils");
                    if (pageUtils != null) {
                        List<Emp> emps = pageUtils.getList();
                        for (Emp emp : emps) {
                %>
                <tr id="data_0" class="main_trbg" align="center">
                    <td><input type="checkbox" id="box_0" value="1"></td>
                    <td><%=emp.getEmpId()%>
                    </td>
                    <td><%=emp.getEmpName()%>
                    </td>
                    <td><%=emp.getGender()%>
                    </td>
                    <td><%=emp.getPhone()%>
                    </td>
                    <td><%=emp.getEmail()%>
                    </td>
                    <td><%=emp.getStaffName()%>
                    </td>
                    <td><%=emp.getEmpEdu()%>
                    </td>
                    <td><%=emp.getCardNum()%>
                    </td>
                    <td><%=emp.getdName()%>
                    </td>
                    <td><%=emp.getEmpAddress()%>
                    </td>
                    <td><%=emp.getEmpCreateTime()%>
                    </td>
                    <td align="center" width="40px;"><a href="emp_update.html">
                        <img title="修改" src="../images/update.gif"/></a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>

                <tr>
                    <td colspan="13" align="center">
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
                        <a href="javascript:jumpPage(<%=pageUtils.getPageCount()%>);">
                            跳转到&nbsp;
                            <input id="jumpNum" style="width: 25px;" type="text" value="<%=pageUtils.getPageIndex()%>">&nbsp;页
                        </a>
                        <a>
                            每页显示
                            <select id="selectPage" onchange="changePageSize(this.value);">
                                <option value="5" ${requestScope.pageUtils.pageSize==5?"selected":""}>5</option>
                                <option value="10" ${requestScope.pageUtils.pageSize==10?"selected":""}>10</option>
                                <option value="15" ${requestScope.pageUtils.pageSize==15?"selected":""}>15</option>
                                <option value="20" ${requestScope.pageUtils.pageSize==20?"selected":""}>20</option>
                            </select>
                            条
                        </a>
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
        $("#empform").prop("action", "EmpServlet?action=query_page&pageIndex=" + pageIndex+"&pageSize="+$('#selectPage').val());
        //2.表单的查询条件也提交  noticeTitle,noticeContent
        $("#empform").submit();
    }

    function jumpPage(pageCount) {
        var num = $("#jumpNum").val();
        //正则判断
        var exp = /^[1-9]\d*$/;
        if (exp.test(num) == true) {
            if (num >= 1 && num <= pageCount) {
                goPage(num);
            } else {
                alert("页码超出范围!");
            }
        } else {
            alert("格式错误!")
        }
    }

    function changePageSize(pageSize) {
        $("#empform").prop("action", "EmpServlet?action=query_page&pageIndex=1&pageSize=" + pageSize);
        $("#empform").submit();
    }
</script>
</body>
</html>
