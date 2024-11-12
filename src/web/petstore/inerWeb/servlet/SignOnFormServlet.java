package web.petstore.inerWeb.servlet;

import web.petstore.persistence.CaptchaUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SignOnFormServlet extends HttpServlet {

    private static final String SIGNON_FORM = "/WEB-INF/jsp/account/signon.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action =req.getParameter("action");
        if ("captcha".equals(action)) {
            // 生成验证码
            //String captcha = CaptchaUtil.generateRandomString(4);
            CaptchaUtil captchaUtil=new CaptchaUtil();
            BufferedImage captchaImage = captchaUtil.generateCaptcha(100, 50);
            req.getSession().setAttribute("captcha", captchaUtil.getRandomString()); // 存储在会话中

            // 将验证码图像返回给客户端
            resp.setContentType("image/png");
            ImageIO.write(captchaImage, "png", resp.getOutputStream());
        } else {
            // 处理其他 GET 请求（如显示登录表单）
            req.getRequestDispatcher(SIGNON_FORM).forward(req,resp);
        }

    }
}
