package per.hr.resource.manage.sys.servlet;

import per.hr.resource.manage.sys.bean.Emp;
import per.hr.resource.manage.sys.service.EmpService;
import per.hr.resource.manage.sys.service.impl.EmpServiceImpl;
import per.hr.resource.manage.sys.utils.PageUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/view/EmpServlet")
public class EmpServlet extends HttpServlet {

    EmpService empService = new EmpServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String action = req.getParameter("action");
        switch (action) {
            case "addEmp":
                addEmp(req, resp);
                break;
            case "doUpdate":
                String empId = req.getParameter("empId");
                Emp empById = empService.getEmpById(Integer.valueOf(empId));
                //存储转发
                req.setAttribute("emp", empById);
                req.getRequestDispatcher("emp_update.jsp").forward(req, resp);
                break;
            case "query_page":
                String pageIndex = req.getParameter("pageIndex");
                if (pageIndex == null) {
                    pageIndex = "1";
                }
                String pageSize = req.getParameter("pageSize");
                if (pageSize == null) {
                    pageSize = "5";
                }
                //第一次查询,没有选下拉框
                String staffId = req.getParameter("staffId");
                if (staffId == null) {
                    staffId = "-1";
                }
                String empName = req.getParameter("empName");
                String cardNum = req.getParameter("cardNum");
                String gender = req.getParameter("gender");
                if (gender == null) {
                    gender = "-1";
                }
                String phone = req.getParameter("phone");
                String did = req.getParameter("did");
                if (did == null) {
                    did = "-1";
                }
                Emp emp = new Emp(empName, gender, Integer.valueOf(staffId), cardNum, Integer.valueOf(did), phone);
                int count = (int) empService.getTotalCount(emp);
                List<Emp> emps = empService.getEmpsWithPage(emp, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
                PageUtils<Emp> pageUtils = new PageUtils<>(Integer.parseInt(pageIndex), Integer.parseInt(pageSize), count, emps, emp);
                req.setAttribute("pageUtils", pageUtils);
                req.getRequestDispatcher("emp_list.jsp").forward(req, resp);
                break;
        }

    }


    public void addEmp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String empName = req.getParameter("empName");
        String gender = req.getParameter("gender");
        String telNum = req.getParameter("telNum");
        String email = req.getParameter("email");
        String staffId = req.getParameter("staffId");

        String empEdu = req.getParameter("empEdu");
        String cardNum = req.getParameter("cardNum");
        String dId = req.getParameter("dId");
        String empAddress = req.getParameter("empAddress");
        String remark = req.getParameter("remark");

        String hobby = req.getParameter("hobby");
        System.out.println("hobby = " + hobby);
        String political = req.getParameter("political");
        String qq = req.getParameter("qq");
        String ems = req.getParameter("ems");
        String birth = req.getParameter("birth");

        String major = req.getParameter("major");
        String volk = req.getParameter("volk");
        String phone = req.getParameter("phone");
        Emp emp = new Emp(empName, gender, telNum, email, Integer.valueOf(staffId), empEdu, cardNum, Integer.valueOf(dId), empAddress, remark, hobby, political, qq, ems, birth, major, volk, phone);
        boolean b = empService.addEmp(emp);
        if (b) {
            out.println("<script>alert('添加成功！');</script>");
        } else {
            out.println("<script>alert('添加失败！');</script>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

}
