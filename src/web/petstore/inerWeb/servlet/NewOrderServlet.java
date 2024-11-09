package web.petstore.inerWeb.servlet;

import web.petstore.domain.Cart;
import web.petstore.domain.CartItem;
import web.petstore.domain.LineItem;
import web.petstore.domain.Order;
import web.petstore.persistence.LineItemDao;
import web.petstore.persistence.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class NewOrderServlet extends HttpServlet {
    private int orderId;
    private String userId;
    private String orderDateString;
    private Date orderDate;
    private String shipAdddr1;
    private String shipAdddr2;
    private String shipCity;
    private String shipState;
    private String shipZip;
    private String shipCountry;
    private String billAddr1;
    private String billAddr2;
    private String billCity;
    private String billState;
    private String billZip;
    private String billCountry;
    private String courier;
    private String billTofirstname;
    private String billTolastname;
    private String shipTofirstname;
    private String shipTolastname;
    private String creditCard;
    private String exprdate;
    private String cardType;
    private String locale;

    private int linenum=1;
    ArrayList<LineItem> lineItemArrayList=new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        OrderDao orderDao=new OrderDao();
        try {
            this.orderId = orderDao.generateNewOrderId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.userId=req.getParameter("order.UserId");
        LocalDate currentDate = LocalDate.now();
        this.orderDate =  Date.valueOf(currentDate);
        this.shipAdddr1=req.getParameter("order.shipAddress1");
        this.shipAdddr2=req.getParameter("order.shipAddress2");
        this.shipCity=req.getParameter("order.shipCity");
        this.shipState=req.getParameter("order.shipState");
        this.shipZip=req.getParameter("order.shipZip");
        this.shipCountry=req.getParameter("order.shipCountry");
        this.billAddr1=req.getParameter("order.billAddress1");
        this.billAddr2=req.getParameter("order.billAddress2");
        this.billCity=req.getParameter("order.billCity");
        this.billState=req.getParameter("order.billState");
        this.billZip=req.getParameter("order.billZip");
        this.billCountry=req.getParameter("order.billCountry");
        this.courier="UPS";

        this.billTofirstname=req.getParameter("order.billToFirstName");
        this.billTolastname=req.getParameter("order.billToLastName");
        this.shipTofirstname=req.getParameter("order.shipToFirstName");
        this.shipTolastname=req.getParameter("order.shipToLastName");
        this.creditCard=req.getParameter("Card_Number");
        this.exprdate=req.getParameter("Expiry_Date");
        this.cardType=req.getParameter("order.cardType");
        this.locale="Square";


        Order order=new Order();
        order.setOrderId(orderId);
        order.setUsername(userId);
        order.setOrderDate(orderDate);
        order.setShipAddress1(shipAdddr1);
        order.setShipAddress2(shipAdddr2);
        order.setShipCity(shipCity);
        order.setShipState(shipState);
        order.setShipZip(shipZip);
        order.setShipCountry(shipCountry);
        order.setBillAddress1(billAddr1);
        order.setBillAddress2(billAddr2);
        order.setBillCity(billCity);
        order.setBillState(billState);
        order.setBillZip(billZip);
        order.setBillCountry(billCountry);
        order.setCourier(courier);
        order.setBillToFirstName(billTofirstname);
        order.setBillToLastName(billTolastname);
        order.setShipToFirstName(shipTofirstname);
        order.setShipToLastName(shipTolastname);
        order.setCreditCard(creditCard);
        order.setExpiryDate(exprdate);
        order.setCardType(cardType);
        order.setLocale(locale);

        Cart cart = (Cart) session.getAttribute("cart");
        List<CartItem> cartItemList= cart.getCartItemList();
        BigDecimal totalprice=BigDecimal.ZERO ;
        for(CartItem item : cartItemList)
        {
            LineItem lineItem=new LineItem();
            lineItem.setOrderId(orderId);
            lineItem.setLineNumber(linenum);
            linenum++;
            lineItem.setItemId(item.getItem().getItemId());
            lineItem.setQuantity(item.getQuantity());
            lineItem.setUnitPrice(item.getItem().getListPrice());
            lineItem.setTotal(item.getTotal());
            totalprice = totalprice.add(item.getTotal());
            lineItem.setItem(item.getItem());
            lineItemArrayList.add(lineItem);
            order.setTotalPrice(totalprice);
        }

        order.setLineItems(lineItemArrayList);


        session.setAttribute("order",order);

        req.getRequestDispatcher("confirmOrderForm").forward(req,resp);
    }
}
/*
public class NewOrderServlet extends HttpServlet {
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
                try {
                    listOrders(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "newOrderForm":
                newOrderForm(req, resp);
                break;
            case "viewOrder":
                try {
                    viewOrder(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                try {
                    listOrders(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
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
                try {
                    listOrders(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void listOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
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

    private void viewOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
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
        resp.sendRedirect(req.getContextPath() + "/NewOrderServlet?action=listOrders");
    }
}
*/