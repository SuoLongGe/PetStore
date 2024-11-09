package web.petstore.inerWeb.servlet;

import web.petstore.domain.Account;
import web.petstore.domain.Order;
import web.petstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListOrderServlet extends HttpServlet {
    private static final String ORDERLIST = "/WEB-INF/jsp/order/listOrders.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account)session.getAttribute("account");

        OrderService orderService = new OrderService();
        List<Order> orderList = new ArrayList<>();
        try {
            orderList = orderService.getOrdersByUsername(account.getUsername());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        session.setAttribute("orderList",orderList);
        req.getRequestDispatcher(ORDERLIST).forward(req,resp);
    }
}
