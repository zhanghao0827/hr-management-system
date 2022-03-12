package per.hr.resource.manage.sys.servlet;

import per.hr.resource.manage.sys.bean.Notice;
import per.hr.resource.manage.sys.bean.User;
import per.hr.resource.manage.sys.service.NoticeService;
import per.hr.resource.manage.sys.service.impl.NoticeServiceImpl;
import per.hr.resource.manage.sys.utils.PageUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/view/NoticeServlet")
public class NoticeServlet extends HttpServlet {

    NoticeService noticeService = new NoticeServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String action = req.getParameter("action");
        switch (action) {
            case "query_page":
                query_page(req, resp);
                break;
            case "notice_add":
                notice_add(req, resp);
                break;
            case "notice_delete":
                notice_delete(req, resp);
                break;
            case "notice_goUpdate":
                notice_goUpdate(req, resp);
                break;
            case "notice_update":
                PrintWriter out = resp.getWriter();
                String noticeId = req.getParameter("noticeId");
                String noticeTitle = req.getParameter("noticeTitle");
                String noticeContent = req.getParameter("noticeContent");
                String pageIndex = req.getParameter("pageIndex");
                Notice notice = new Notice(Integer.valueOf(noticeId), noticeContent, noticeTitle);
                boolean b = noticeService.updateNoticeById(notice);
                if (b) {
                    out.println("<script>alert('更新成功！');location.href='NoticeServlet?action=query_page&pageIndex=" + Integer.parseInt(pageIndex) + "&pageSize=5';</script>");
                } else {
                    out.println("<script>alert('更新失败！');location.href='NoticeServlet?action=query_page&pageIndex=" + Integer.parseInt(pageIndex) + "&pageSize=5';</script>");
                }
                break;
        }
    }

    public void notice_goUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pageIndex = req.getParameter("pageIndex");
        req.setAttribute("pageIndex", Integer.parseInt(pageIndex));
        Notice notice = noticeService.getNoticeById(Integer.parseInt(id));
        //存值转发
        req.setAttribute("notice", notice);
        req.getRequestDispatcher("notice_update.jsp").forward(req, resp);
    }

    public void notice_delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("id");
        String pageIndex = req.getParameter("pageIndex");
        boolean b = noticeService.deleteNoticeById(Integer.parseInt(id));
        if (b) {
            out.println("<script>alert('删除成功！');location.href='NoticeServlet?action=query_page&pageIndex=" + Integer.parseInt(pageIndex) + "&pageSize=5';</script>");
        } else {
            out.println("<script>alert('删除失败！');location.href='NoticeServlet?action=query_page&pageIndex=" + Integer.parseInt(pageIndex) + "&pageSize=5';</script>");
        }
    }

    public void notice_add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        //当前登录用户
        User user = (User) req.getSession().getAttribute("user");
        String noticeTitle = req.getParameter("noticeTitle");
        String noticeContent = req.getParameter("noticeContent");
        Notice notice = new Notice(noticeContent, noticeTitle);
        boolean b = noticeService.addNotice(notice, user);
        if (b) {
            out.println("<script>alert('添加成功！');location.href='NoticeServlet?action=query_page&pageIndex=1&pageSize=5';</script>");
        } else {
            out.println("<script>alert('添加失败！');location.href='notice_add.jsp';</script>");
        }
    }

    public void query_page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageIndex = req.getParameter("pageIndex");
        String pageSize = req.getParameter("pageSize");
        String noticeContent = req.getParameter("noticeContent");
        String noticeTitle = req.getParameter("noticeTitle");
        if (pageIndex == null) {
            pageIndex = "1";
        }
        if (pageSize == null) {
            pageSize = "5";
        }
        Notice notice = new Notice(noticeContent, noticeTitle);
        int count = (int) noticeService.getTotalCount(notice);
        List<Notice> notices = noticeService.getNoticesWithPage(notice, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        PageUtils<Notice> pageUtils = new PageUtils<>(Integer.parseInt(pageIndex), Integer.parseInt(pageSize), count, notices, notice);
        req.setAttribute("pageUtils", pageUtils);
        req.getRequestDispatcher("notice_list.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
