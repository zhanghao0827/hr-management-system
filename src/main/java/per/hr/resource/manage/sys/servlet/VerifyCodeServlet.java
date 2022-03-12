package per.hr.resource.manage.sys.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/verify_code")
public class VerifyCodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 80;
        int height = 25;
        //创建一个画布对象
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();//通过xxx得到一个画笔
        g.setColor(Color.WHITE);//设置画笔颜色为白色
        g.fillRect(0, 0, width, height);//画一个实心矩形
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);//画一个空心矩形,-1达到一个框框的显示效果
        //62个大小字母以及数字的字符数组
        char[] array = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6; i++) {//画干扰线
            int red = r.nextInt(255);
            int green = r.nextInt(255);
            int blue = r.nextInt(255);//获取三个随机色值
            g.setColor(new Color(red, green, blue));//设置颜色
            int x1 = r.nextInt(width);
            int y1 = r.nextInt(height);//起始坐标
            int x2 = r.nextInt(width);
            int y2 = r.nextInt(height);//干扰线的终止坐标
            g.drawLine(x1, y1, x2, y2);
        }
        //绘制四个随机数
        for (int i = 1; i <= 4; i++) {
            int index = r.nextInt(62);
            String str = array[index] + "";
            sb.append(str);
            int red = r.nextInt(255);
            int green = r.nextInt(255);
            int blue = r.nextInt(255);//获取三个随机色值
            g.setColor(new Color(red, green, blue));//设置颜色
            g.setFont(new Font("黑体", Font.BOLD, 18));//设置字体黑体，加粗，18号
            g.drawString(str, i * 16, 18);//画验证码中的内容
        }
        System.out.println("系统验证码:" + sb.toString());
        HttpSession session = request.getSession();
        session.setAttribute("verify_code", sb.toString());//将验证码存入到session中

        response.setContentType("image/jpeg");//设置输出内容的类型是图片
        ServletOutputStream out = response.getOutputStream();//获得一个浏览器字节输出流
        ImageIO.write(bi, "jpeg", out);//输出这个画布
    }

}
