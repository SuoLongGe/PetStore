package web.petstore.inerWeb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import web.petstore.persistence.AccountDao;


public class UserinfoExistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username =req.getParameter("username");
        AccountDao accountDao= new AccountDao();

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        if(accountDao.getAccountByUsername(username)){
            out.print("Exist");
        }
        else{
            out.print("Not Exist");
        }
    }
}