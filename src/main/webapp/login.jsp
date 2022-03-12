<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>人力资源管理系统</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <link href="js/metronic/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="js/metronic/plugins/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
    <link href="js/metronic/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="js/metronic/css/style-metro.css" rel="stylesheet" type="text/css"/>
    <link href="js/metronic/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="js/metronic/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="js/metronic/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="js/metronic/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="js/metronic/css/pages/lock.css" rel="stylesheet" type="text/css"/>
    <!-- END PAGE LEVEL STYLES -->
    <link rel="shortcut icon" href="favicon.ico"/>
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="js/jquery-migrate-1.2.1.js"></script>
    <link href="js/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
    <script src="js/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <script type="text/javascript">
        function changeCode() {
            //静态资源有缓存，加上时间戳
            document.getElementById("pic").src = "verify_code?time=" + new Date();
        }
    </script>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->

<body style="font-family: 微软雅黑">
<div class="page-lock">
    <div class="page-logo" style="margin-bottom: 2px">
        <a class="brand" href="login.html" style="font-size: 22px; color: #FFF;"> 人事管理系统</span>
        </a>
    </div>
    <%
        //读物cookie
        String uname = "";
        String upass = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();//cookie的键
                if ("cookie_uname".equals(name)) {
                    //读取cookie并解码
                    uname = URLDecoder.decode(cookie.getValue(), "UTF-8");
                } else if ("cookie_upass".equals(name)) {
                    upass = URLDecoder.decode(cookie.getValue(), "UTF-8");
                }
            }
        }
    %>
    <form action="view/UserServlet" method="post" id="loginForm">
        <!--
            设置表单提交的隐藏域
            action=login  表示进行登录操作
         -->
        <input type="hidden" name="action" value="login">
        <div class="page-body">
            <img class="page-lock-img" src="js/metronic/img/profile/logo2.jpg" alt="">
            <div class="page-lock-info">
                <span>&nbsp;</span>
                <span style="padding-top: 5px;color: red;"></span>
                <div class="control-group">
                    <div class="controls">
                        <div class="input-icon left">
                            <i class="icon-user"></i>
                            <input class="m-wrap placeholder-no-fix" type="text" placeholder="帐号" id="loginname"
                                   name="userLoginName" value="<%=uname%>">
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="input-icon left">
                            <i class="icon-lock"></i>
                            <input class="m-wrap placeholder-no-fix" type="password" placeholder="密码"
                                   name="userPwd" value="<%=upass%>">
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="input-icon left">
                            <input class="m-wrap placeholder-no-fix" type="checkbox" placeholder="密码"
                                   name="rememberMe"
                                   value="1" <%=uname.equals("")?"":"checked"%>> 记住账号密码
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="input-icon left">
                            <i class="icon-lock"></i>
                            <input class="m-wrap placeholder-no-fix" type="text" placeholder="验证码" id="password"
                                   name="userCode">
                            <img src="verify_code" style="width:80px;" id="pic">
                            <a style="color: white;" href="#" onclick="changeCode();">换一张</a>
                            <!-- 单击登录 -->
                            <button type="submit" id="login-submit-btn" class="btn green" style="margin-left: 20px">
                                登录 </i>
                            </button>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </form>
</div>

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->

<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="js/metronic/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
<script src="js/metronic/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<!--[if lt IE 9]>
<script src="js/metronic/plugins/excanvas.js"></script>
<script src="js/metronic/plugins/respond.js"></script>
<![endif]-->
<script src="js/metronic/plugins/breakpoints/breakpoints.js" type="text/javascript"></script>
<!-- IMPORTANT! jquery.slimscroll.min.js depends on jquery-ui-1.10.1.custom.min.js -->
<script src="js/metronic/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="js/metronic/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<script src="js/metronic/scripts/app.js"></script>
<script src="js/metronic/scripts/lock.js"></script>
<script>
    $(function () {
        App.init();
        Lock.init();
    });
</script>
<!-- END JAVASCRIPTS -->
<script type="text/javascript">
    var _gaq = _gaq || [];
    _gaq.push(['_setAccount', 'UA-37564768-1']);
    _gaq.push(['_setDomainName', 'keenthemes.com']);
    _gaq.push(['_setAllowLinker', true]);
    _gaq.push(['_trackPageview']);
    (function () {
        var ga = document.createElement('script');
        ga.type = 'text/javascript';
        ga.async = true;
        ga.src = ('https:' == document.location.protocol ? 'https://' :
            'http://') +
            'stats.g.doubleclick.net/dc.js';
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(ga, s);
    })();
</script>
</body>
<!-- END BODY -->

</html>
