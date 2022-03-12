package per.hr.resource.manage.sys.servlet;

import per.hr.resource.manage.sys.bean.User;
import per.hr.resource.manage.sys.service.UserService;
import per.hr.resource.manage.sys.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

public class UserServlet extends HttpServlet {

    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String action = req.getParameter("action");
        switch (action) { //判断行为，是login,还是query
            case "login":
                login(req, resp);
                break;
            case "query":
                userQuery(req, resp);
                break;
            case "logout":
                HttpSession session = req.getSession();
                session.invalidate();//清空所有session
                resp.sendRedirect("/login.jsp");
                break;
            case "checkLoginName":
                String loginName = req.getParameter("loginName");
                System.out.println("loginName = " + loginName);
                boolean b = userService.checkLoginName(loginName);
                out.print(b);//println不要有换行符
                break;
            case "changeState":
                String id = req.getParameter("id");
                String state = req.getParameter("state");
                boolean b1 = userService.checkUserState(Integer.parseInt(id), Integer.parseInt(state));
                out.print(b1);
                break;
            case "delete":
                String userId = req.getParameter("id");
                boolean b3 = userService.deleteUser(Integer.parseInt(userId));
                out.print(b3);
                break;
            case "user_add":
                String userName = req.getParameter("userName");
                String userState = req.getParameter("userState");
                String userLoginName = req.getParameter("userLoginName");
                String userPwd = req.getParameter("userPwd");
                User user = new User(userLoginName, userPwd, userName, Integer.valueOf(userState));
                boolean b2 = userService.addUser(user);
                if (b2) {
                    out.println("<script>alert('添加成功！');location.href='UserServlet?action=query';</script>");
                } else {
                    out.println("<script>alert('添加失败！');</script>");
                }
                break;
            case "user_goUpdate":
                String user_id = req.getParameter("id");
                User userById = userService.getUserById(Integer.valueOf(user_id));
                req.setAttribute("user", userById);
                req.getRequestDispatcher("user_update.jsp").forward(req, resp);
                break;
            case "user_update":
                String userId1 = req.getParameter("userId");
                String userName1 = req.getParameter("userName");
                String state1 = req.getParameter("state");
                String userLoginName1 = req.getParameter("userLoginName");
                String userPwd1 = req.getParameter("userPwd");
                User user_update = new User(Integer.parseInt(userId1), userLoginName1, userPwd1, userName1, Integer.parseInt(state1));
                boolean b4 = userService.updateUserById(user_update);
                if (b4) {
                    out.println("<script>alert('更新成功！');location.href='UserServlet?action=query';</script>");
                } else {
                    out.println("<script>alert('更新失败！');</script>");
                }
                break;
        }
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        String uname = req.getParameter("userLoginName");
        String upass = req.getParameter("userPwd");
        String userCode = req.getParameter("userCode");
        String rememberMe = req.getParameter("rememberMe");

        String verifyCode = (String) session.getAttribute("verify_code");
        if (userCode.equalsIgnoreCase(verifyCode)) {
            User user = new User(uname, upass);
            User loginUser = userService.login(user);
            if (loginUser != null) {
                if (loginUser.getState() == 1) {
                    /**
                     * 保存账号密码  Cookie键值对
                     * Cookie默认不支持中文（由于Tomcat版本原因可能支持中文）
                     * 存储cookie并编码
                     * URLEncoder.encode(data, "UTF-8")
                     */
                    Cookie cookie_uname = new Cookie("cookie_uname", URLEncoder.encode(uname, "UTF-8"));
                    Cookie cookie_upass = new Cookie("cookie_upass", URLEncoder.encode(upass, "UTF-8"));
                    if (rememberMe != null) {//记住账号密码
                        //设置cookie有效期
                        cookie_uname.setMaxAge(Integer.valueOf(rememberMe) * 600);
                        cookie_upass.setMaxAge(Integer.valueOf(rememberMe) * 600);
                    } else {
                        //不记住账号密码，清空cookie  -1内存中有效，0表示失效
                        cookie_uname.setMaxAge(0);
                        cookie_upass.setMaxAge(0);
                    }
                    //设置cookie作用域
                    cookie_uname.setPath("/");
                    cookie_upass.setPath("/");
                    //cookie写入浏览器
                    resp.addCookie(cookie_uname);
                    resp.addCookie(cookie_upass);

                    //获取session对象
                    String id = session.getId();
                    //session存值
                    session.setAttribute("user", loginUser);

                    //就近原则 设置全session有效期（s）
                    session.setMaxInactiveInterval(60 * 30);

                    //浏览器响应script脚本 2次请求  相对路径main.html  不要/main.jsp == localhost:8080/main.jsp
//                out.println("<script>location.href='main.jsp';</script>");
                    //重定向请求
                    resp.sendRedirect("main.jsp");
                    //转发
//              req.getRequestDispatcher("main.jsp").forward(req, resp);
                } else {
                    out.println("<script>alert('该账户被冻结！');location.href='/login.jsp';</script>");
                }
            } else {
                out.println("<script>alert('账户密码错误！');location.href='/login.jsp';</script>");
            }
        } else {
            out.println("<script>alert('验证码错误！');location.href='/login.jsp';</script>");
        }
    }

    public void userQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("uname");
        String state = req.getParameter("state");
        //第一次点击用户查询 uname和state都为null
        if (state == null) {
            state = "-1";
        }
        User user = new User(uname, Integer.valueOf(state));//第一次state为Null,要进行处理
        List<User> users = userService.findUsers(user);
        /**
         * 重定向是2次请求，request存储的值不能跨请求共享
         * request.setAttribute("users", users);
         * resp.sendRedirect("user_list.jsp");
         * request和user_list.jsp内置的request对象不同
         * user_list.jsp内置的request对象为null，--->NullPointerException
         *
         * 转发： 存储数据，请求转发（存值转发）
         *  request.setAttribute("users", users);和user_list.jsp内置的request对象相同
         *  可以共享request中的值
         */
        req.setAttribute("users", users);//搜索结果
        req.setAttribute("uname", uname);//搜索条件
        req.setAttribute("state", state);//搜索条件
        //写相对路径，不要加/  /user_list.jsp == localhost:8080/user_list.jsp
        req.getRequestDispatcher("user_list.jsp").forward(req, resp);
    }
}
