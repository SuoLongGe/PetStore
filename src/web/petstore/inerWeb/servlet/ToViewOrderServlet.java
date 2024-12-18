//package web.petstore.inerWeb.servlet;
//
//import web.petstore.domain.Order;
//import web.petstore.service.OrderService;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.sql.SQLException;
//
//public class ToViewOrderServlet extends HttpServlet {
//    private static final String VIEWORDER = "/WEB-INF/jsp/order/finalOrderForm.jsp";
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.doGet(req, resp);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        OrderService orderService = new OrderService();
//
//        int orderId = Integer.parseInt(req.getParameter("orderId"));
//        Order order = new Order();
//        try {
//            order = orderService.getOrder(orderId);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        session.setAttribute("order", order);
//        session.setAttribute("lineItems",order.getLineItems());
//
//        req.getRequestDispatcher(VIEWORDER).forward(req, resp);
//    }
//}



package web.petstore.inerWeb.servlet;

import web.petstore.domain.Account;
import web.petstore.domain.Order;
import web.petstore.service.LogService;
import web.petstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ToViewOrderServlet extends HttpServlet {
    private static final String VIEWORDER = "/WEB-INF/jsp/order/finalOrderForm.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        OrderService orderService = new OrderService();

        int orderId = Integer.parseInt(req.getParameter("orderId"));
        Order order = new Order();
        try {
            order = orderService.getOrder(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        session.setAttribute("order", order);
        session.setAttribute("lineItems",order.getLineItems());

        //xzx 在此处新建记录查看历史订单功能
        LogService logService =  new LogService();
        Account loginAccount = (Account) session.getAttribute("loginAccount");
        String userId = loginAccount.getUsername();
        String order_id  = String.valueOf(order.getOrderId());
        if (userId != null) { // 检查用户是否已登录
            logService.logUserActivity(userId, "浏览", "查看历史订单订单", null, order_id);
        }



        req.getRequestDispatcher(VIEWORDER).forward(req, resp);
    }
}
