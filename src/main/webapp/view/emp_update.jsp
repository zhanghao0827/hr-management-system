<%@ page import="per.hr.resource.manage.sys.bean.Emp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>人事管理系统——更新员工</title>
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
    <script language="javascript" type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<%
    Emp emp = (Emp) request.getAttribute("emp");
%>
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
            //下拉框自动选中  设置下拉框的value值
            $("[name=staffId]").val(<%=emp.getStaffId()%>);
        });

        $.getJSON("DeptServlet", "action=getDepts", function (depts) {
            for (var i = 0; i < depts.length; i++) {
                var did = depts[i].did;
                var dName = depts[i].dName;
                $("[name=dId]").append("<option value='" + did + "'>" + dName + "</option>");
            }
            //下拉框自动选中  设置下拉框的value值
            $("[name=dId]").val(<%=emp.getdId()%>);
        });
    });
</script>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td height="10"></td>
    </tr>
    <tr>
        <td width="15" height="32"><img src="../images/main_locleft.gif" width="15" height="32"></td>
        <td class="main_locbg font2"><img src="../images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：员工管理 &gt; 更新员工</td>
        <td width="15" height="32"><img src="../images/main_locright.gif" width="15" height="32"></td>
    </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
    <tr valign="top">
        <td>
            <form action="EmpServlet" id="employeeForm" method="get">
                <!-- 隐藏表单，flag表示添加标记 -->
                <input type="hidden" name="action" value="updateEmp">
                <%--
                    隐藏更新的id *******************
                --%>
                <input type="hidden" name="empId" value="<%=emp.getEmpId()%>">
                <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                    <tr>
                        <td class="font3 fftd">
                            <table>
                                <tr>
                                    <td class="font3 fftd">
                                        姓名：<input type="text" name="empName" value="<%=emp.getEmpName()%>" id="name"
                                                  size="20"/>
                                    </td>
                                    <td class="font3 fftd">身份证号码：<input type="text" name="cardNum" id="cardId"
                                                                        size="20"/></td>
                                </tr>
                                <tr>
                                    <td class="font3 fftd">性别：
                                        <select name="gender" style="width:143px;">
                                            <option value="男">--请选择性别--</option>
                                            <option value="男" <%=emp.getGender().equals("男") ? "selected" : ""%>>男
                                            </option>
                                            <option value="女" <%=emp.getGender().equals("女") ? "selected" : ""%>>女
                                            </option>
                                        </select></td>
                                    <td class="font3 fftd">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：
                                        <select name="staffId" style="width:143px;">
                                            <option value="-1">--请选择职位--</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="font3 fftd">学历：<input name="empEdu" id="education" size="20"/></td>
                                    <td class="font3 fftd">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：<input name="email"
                                                                                                         id="email"
                                                                                                         size="20"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="font3 fftd">手机：<input name="telNum" id="phone" size="20"/></td>
                                    <td class="font3 fftd">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：<input name="phone"
                                                                                                         id="tel"
                                                                                                         size="20"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor"></td>
                    </tr>

                    <tr>
                        <td class="font3 fftd">
                            政治面貌：<input name="political" id="party" size="40"/>&nbsp;&nbsp;
                            QQ&nbsp;&nbsp;号码：<input name="qq" id="qqNum" size="20"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor"></td>
                    </tr>

                    <tr>
                        <td class="font3 fftd">
                            联系地址：<input name="empAddress" id="address" size="40"/>&nbsp;&nbsp;
                            邮政编码：<input name="ems" id="postCode" size="20"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor"></td>
                    </tr>

                    <tr>
                        <td class="font3 fftd">
                            出生日期：<input cssClass="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});"
                                        name="birth" id="birthday" size="40"/>&nbsp;&nbsp;
                            民&nbsp;&nbsp;&nbsp;&nbsp;族：<input name="volk" id="race" size="20"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor"></td>
                    </tr>

                    <tr>
                        <td class="font3 fftd">
                            所学专业：<input name="major" id="speciality" size="40"/>&nbsp;&nbsp;
                            爱好：<input type="text" name="hobby" id="hobby" size="20"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor"></td>
                    </tr>

                    <tr>
                        <td class="font3 fftd">
                            备&nbsp;&nbsp;&nbsp;&nbsp;注：<input name="remark" id="remark" size="40"/>
                            &nbsp;&nbsp;所属部门：
                            <select name="dId" style="width:100px;">
                                <option value="-1">--部门选择--</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor"></td>
                    </tr>

                    <tr>
                        <td align="left" class="fftd">
                            <input type="submit" value="添加">&nbsp;&nbsp;<input type="reset" value="取消 "></td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>
