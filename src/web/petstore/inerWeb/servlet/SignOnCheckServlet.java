package web.petstore.inerWeb.servlet;

import web.petstore.domain.Account;
import web.petstore.persistence.AccountDao;
import web.petstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SignOnCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username =req.getParameter("username");
        String password =req.getParameter("password");
        String captcha =req.getParameter("captcha");
        AccountService accountService = new AccountService();
        Account loginAccount = accountService.getAccount(username, password);

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        if(loginAccount == null){
            out.print("None");
        }
        else{
            out.print("success");
        }
    }
}
