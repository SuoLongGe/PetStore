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

public class MyOrderServlet extends HttpServlet {

    private static final String MY_ORDERS = "/WEB-INF/jsp/order/myOrders.jsp";
    private static final String orderName = "ordernum";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        OrderService orderService = new OrderService();

        int nextId = orderService.getNextId(orderName);
        Account account = (Account) session.getAttribute("loginAccount");
        List<Order> orderLists = new ArrayList<>();
        try {
            orderLists = orderService.getOrdersByUsername(account.getUsername());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        session.setAttribute("orderLists", orderLists);
        req.getRequestDispatcher(MY_ORDERS).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
