package per.hr.resource.manage.sys.servlet;

import per.hr.resource.manage.sys.bean.Download;
import per.hr.resource.manage.sys.bean.User;
import per.hr.resource.manage.sys.service.DownloadService;
import per.hr.resource.manage.sys.service.impl.DownloadServiceImpl;
import per.hr.resource.manage.sys.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@WebServlet("/view/DownloadServlet")
@MultipartConfig //servlet能够接收文件上传请求
public class DownloadServlet extends HttpServlet {

    DownloadService downloadService = new DownloadServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        /**
         * PrintWriter out = resp.getWriter();
         * 再次resp.getOutputStream();
         * 流不要公用
         */
        String action = req.getParameter("action");
        switch (action) {
            case "file_add":
                file_add(req, resp);
                break;
            case "file_query":
                String title = req.getParameter("downloadTittle");
                List<Download> downloads = downloadService.getDownloads(title);
                //存值转发
                req.setAttribute("downloads", downloads);
                req.setAttribute("title", title);
                req.getRequestDispatcher("file_list.jsp").forward(req, resp);
                break;
            case "file_download":
                file_download(req, resp);
                break;
            case "file_goUpdate":
                String id = req.getParameter("id");
                Download download = downloadService.getDownloadById(Integer.parseInt(id));
                req.setAttribute("download", download);
                req.getRequestDispatcher("file_update.jsp").forward(req, resp);
                break;
            case "file_update":
                String downloadId = req.getParameter("downloadId");
                String downloadTittle = req.getParameter("downloadTittle");
                String downloadDes = req.getParameter("downloadDes");
                String downPath = req.getParameter("downPath");//原文件路径
                Part part = req.getPart("file");
                //上传文件输入流
                InputStream inputStream = part.getInputStream();
                String disposition = part.getHeader("Content-Disposition");
                if (!StringUtils.isDownload(disposition)) {
                    String suffix = disposition.substring(disposition.lastIndexOf("."), disposition.length() - 1);//"计划书.doc" 有引号-1
                    String fileName = UUID.randomUUID() + suffix;
                    //上传真实路径
                    String realPath = req.getServletContext().getRealPath("/upload");
                    FileOutputStream fileOutputStream = new FileOutputStream(realPath + "\\" + fileName);
                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = inputStream.read(bytes)) != -1) {
                        fileOutputStream.write(bytes, 0, length);
                    }
                    inputStream.close();
                    fileOutputStream.close();
                    System.out.println("文件上传成功！");
                    //删除原文件
                    File file = new File(realPath + "\\" + downPath);
                    if (file.exists()) {
                        file.delete();
                    }
                    //设置新文件名
                    downPath = fileName;
                }
                PrintWriter out = resp.getWriter();
                Download down = new Download(Integer.valueOf(downloadId), downloadTittle, downloadDes, downPath);
                boolean b = downloadService.updateDownloadById(down);
                if (b) {
                    out.println("<script>alert('更新成功！');location.href='DownloadServlet?action=file_query';</script>");
                } else {
                    out.println("<script>alert('更新失败！');</script>");
                }
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    public void file_add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PrintWriter out = resp.getWriter();
        //文件上传，数据增加到数据库
        Part part = req.getPart("file");
        //上传文件输入流
        InputStream inputStream = part.getInputStream();
        String disposition = part.getHeader("Content-Disposition");//disposition = form-data; name="file"; filename="计划书.doc"
        //上传文件后缀名
        String suffix = disposition.substring(disposition.lastIndexOf("."), disposition.length() - 1);//"计划书.doc" 有引号-1
        String fileName = UUID.randomUUID() + suffix;
        //上传真实路径
        String realPath = req.getServletContext().getRealPath("/upload");
        FileOutputStream fileOutputStream = new FileOutputStream(realPath + "\\" + fileName);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = inputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, length);
        }
        inputStream.close();
        fileOutputStream.close();
        System.out.println("文件上传成功！");
        String downloadTittle = req.getParameter("downloadTittle");
        String downloadDes = req.getParameter("downloadDes");
        User user = (User) req.getSession().getAttribute("user");
        Download download = new Download(downloadTittle, user.getUserId(), downloadDes, fileName);
        boolean b = downloadService.addDownload(download);
        if (b) {
            out.println("<script>alert('添加成功！');location.href='DownloadServlet?action=file_query';</script>");
        } else {
            out.println("<script>alert('添加失败！');</script>");
        }
    }

    public void file_download(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Download down = downloadService.getDownloadById(Integer.parseInt(id));
        String downFileName = down.getDownPath(); //计划书.doc
        //获取文件名后缀
        String suffix = downFileName.substring(downFileName.lastIndexOf("."), downFileName.length()); //计划书.doc 无引号
        //拼接文件名
        String userDownFileName = down.getDownloadTittle() + suffix;

        String downRealPath = req.getServletContext().getRealPath("/upload") + "/" + downFileName;
        File file = new File(downRealPath);
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(downRealPath);
            ServletOutputStream outputStream = resp.getOutputStream();
            //文件以附件形式打开
            resp.setCharacterEncoding("utf-8");
            resp.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(userDownFileName, "UTF-8"));
            byte[] bys = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bys)) != -1) {
                outputStream.write(bys, 0, len);
            }
            fileInputStream.close();
            outputStream.close();
        } else {
            PrintWriter writer = resp.getWriter();
            writer.println("<script>alert('文件不存在！');location.href='DownloadServlet?action=file_query';</script>");
        }
    }
}
