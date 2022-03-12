<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>人事管理系统 </title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link href="fkjava.ico" rel="shortcut icon" type="image/x-icon"/>
    <link href="css/css.css" type="text/css" rel="stylesheet"/>
</head>
<frameset rows="80,*" cols="*" frameborder="no" border="0" framespacing="0">
    <!--
        使用重定向：2次请求 ==out.println("<script>location.href='view/main.jsp';</script>");
            resp.sendRedirect("view/main.jsp"); --- frame src="top.jsp"
            Servlet向浏览器发送一个302状态码，(Location: view/main.jsp)定向到main.html页面


        使用转发：1次请求
            req.getRequestDispatcher("view/main.jsp").forward(req, resp); ---frame src="view/top.jsp"

    -->

    <frame src="top.jsp" name="top" scrolling="no" noresize="noresize">

    <frameset cols="220,*" frameborder="no" border="0" framespacing="0">

        <frame src="left.jsp" name="tree" scrolling="no" marginheight="0" marginwidth="0">

        <frame src="right.jsp" name="main" scrolling="yes" frameborder="0" marginwidth="0" marginheight="0"
               noresize="noresize">

    </frameset>

</frameset>
</html>