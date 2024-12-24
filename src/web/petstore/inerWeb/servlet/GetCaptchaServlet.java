package web.petstore.inerWeb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetCaptchaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionCaptcha = (String) req.getSession().getAttribute("captcha");
        String inputCaptcha = req.getParameter("captcha");
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        if(inputCaptcha == sessionCaptcha || inputCaptcha.equals(sessionCaptcha)){
            out.print("yes");
        }
        else {
            out.print("no");
        }
    }
}
