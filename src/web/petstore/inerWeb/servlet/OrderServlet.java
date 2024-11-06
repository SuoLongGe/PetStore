package web.petstore.inerWeb.servlet;

import web.petstore.domain.Order;
import web.petstore.service.OrderService;
import web.petstore.domain.Account;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends HttpServlet {
    private static final String LIST_ORDERS = "/WEB-INF/jsp/order/ListOrders.jsp";
    private static final String NEW_ORDER_FORM = "/WEB-INF/jsp/order/NewOrderForm.jsp";
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    private static final String ERROR_PAGE = "/WEB-INF/jsp/common/Error.jsp";

    private OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "listOrders";  // Default action
        }

        switch (action) {
            case "listOrders":
                listOrders(req, resp);
                break;
            case "newOrderForm":
                newOrderForm(req, resp);
                break;
            case "viewOrder":
                viewOrder(req, resp);
                break;
            default:
                listOrders(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "newOrder";  // Default action for POST
        }

        switch (action) {
            case "newOrder":
                newOrder(req, resp);
                break;
            default:
                listOrders(req, resp);
                break;
        }
    }

    private void listOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account != null) {
            List<Order> orderList = orderService.getOrdersByUsername(account.getUsername());
            req.setAttribute("orderList", orderList);
            req.getRequestDispatcher(LIST_ORDERS).forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "You must sign in first.");
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }

    private void newOrderForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account != null) {
            // Create a new order and forward to new order form
            Order order = new Order();
            req.setAttribute("order", order);
            req.getRequestDispatcher(NEW_ORDER_FORM).forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "You must sign in to place an order.");
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }

    private void viewOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        Order order = orderService.getOrder(orderId);
        if (order != null) {
            req.setAttribute("order", order);
            req.getRequestDispatcher(VIEW_ORDER).forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "Order not found.");
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }

    private void newOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // You can add logic to create a new order
        // For now, just redirect to the listOrders
        resp.sendRedirect(req.getContextPath() + "/OrderServlet?action=listOrders");
    }
}
