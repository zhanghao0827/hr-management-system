package per.hr.resource.manage.sys.filter;

import per.hr.resource.manage.sys.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/view/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestURI = req.getRequestURI();
        System.out.println("资源:" + requestURI);

        String action = req.getParameter("action");
        // /view/**  /view/UserServlet
        User user = (User) req.getSession().getAttribute("user");
        if (user != null || "login".equals(action)) {
            //已经登录放行
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect("/noPermission.html");
        }
    }
}
