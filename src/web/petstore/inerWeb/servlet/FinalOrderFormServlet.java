package web.petstore.inerWeb.servlet;

import web.petstore.domain.Account;
import web.petstore.domain.Cart;
import web.petstore.persistence.CartDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FinalOrderFormServlet extends HttpServlet {
    private static final String FINAL_ORDER_FORM = "/WEB-INF/jsp/order/finalOrderForm.jsp";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 重置购物车


        req.getRequestDispatcher(FINAL_ORDER_FORM).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
