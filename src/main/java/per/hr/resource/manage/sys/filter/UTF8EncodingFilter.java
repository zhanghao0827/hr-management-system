package per.hr.resource.manage.sys.filter;

import javax.servlet.*;
import java.io.IOException;

public class UTF8EncodingFilter implements Filter {

    /**
     * 所有请求统一进行编码处理
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);//放行
    }

}
