package per.hr.resource.manage.sys.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import per.hr.resource.manage.sys.bean.Staff;
import per.hr.resource.manage.sys.service.StaffService;
import per.hr.resource.manage.sys.service.impl.StaffServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/view/StaffServlet")
public class StaffServlet extends HttpServlet {

    StaffService staffService = new StaffServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String action = req.getParameter("action");
        switch (action) {
            case "getStaffs":
                //查询所有职位
                List<Staff> staffs = staffService.getStaffs();
                //集合转json
                ObjectMapper mapper = new ObjectMapper();
                String s = mapper.writeValueAsString(staffs);
                out.print(s);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
