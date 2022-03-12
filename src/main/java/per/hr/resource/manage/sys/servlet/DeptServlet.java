package per.hr.resource.manage.sys.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import per.hr.resource.manage.sys.bean.Dept;
import per.hr.resource.manage.sys.service.DeptService;
import per.hr.resource.manage.sys.service.impl.DeptServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/view/DeptServlet")
public class DeptServlet extends HttpServlet {

    DeptService deptService = new DeptServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String action = req.getParameter("action");
        switch (action) {
            case "getDepts":
                List<Dept> depts = deptService.getDepts();
                String s = new ObjectMapper().writeValueAsString(depts);
                out.print(s);
                break;
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
