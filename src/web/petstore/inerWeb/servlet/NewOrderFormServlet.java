package web.petstore.inerWeb.servlet;

import web.petstore.domain.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewOrderFormServlet extends HttpServlet {

    private static final String NEWORDER_FORM = "/WEB-INF/jsp/order/newOrder.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Account loginAccount =(Account) session.getAttribute("loginAccount");
        if(loginAccount==null){
            resp.sendRedirect("signonForm");
        }
        else{
            req.getRequestDispatcher(NEWORDER_FORM).forward(req,resp);
        }

    }
}
