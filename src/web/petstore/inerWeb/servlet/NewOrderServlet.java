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

    private String newOrderMsg;
    private int linenum=1;
    ArrayList<LineItem> lineItemArrayList=new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        OrderDao orderDao = new OrderDao();
        try {
            this.orderId = orderDao.generateNewOrderId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.userId = req.getParameter("order.UserId");
        LocalDate currentDate = LocalDate.now();
        this.orderDate = Date.valueOf(currentDate);
        this.shipAdddr1 = req.getParameter("order.shipAddress1");
        this.shipAdddr2 = req.getParameter("order.shipAddress2");
        this.shipCity = req.getParameter("order.shipCity");
        this.shipState = req.getParameter("order.shipState");
        this.shipZip = req.getParameter("order.shipZip");
        this.shipCountry = req.getParameter("order.shipCountry");
        this.billAddr1 = req.getParameter("order.billAddress1");
        this.billAddr2 = req.getParameter("order.billAddress2");
        this.billCity = req.getParameter("order.billCity");
        this.billState = req.getParameter("order.billState");
        this.billZip = req.getParameter("order.billZip");
        this.billCountry = req.getParameter("order.billCountry");
        this.courier = "UPS";

        this.billTofirstname = req.getParameter("order.billToFirstName");
        this.billTolastname = req.getParameter("order.billToLastName");
        this.shipTofirstname = req.getParameter("order.shipToFirstName");
        this.shipTolastname = req.getParameter("order.shipToLastName");
        this.creditCard = req.getParameter("Card_Number");
        this.exprdate = req.getParameter("Expiry_Date");
        this.cardType = req.getParameter("order.cardType");
        this.locale = "Square";

        if (!this.Validate()) {
            req.setAttribute("newOrderMsg", this.newOrderMsg);
            req.getRequestDispatcher("newOrderForm").forward(req, resp);
        } else {
            Order order = new Order();
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
            List<CartItem> cartItemList = cart.getCartItemList();
            BigDecimal totalprice = BigDecimal.ZERO;
            for (CartItem item : cartItemList) {
                LineItem lineItem = new LineItem();
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


            session.setAttribute("order", order);

            req.getRequestDispatcher("confirmOrderForm").forward(req, resp);
        }
    }
    private  boolean Validate() {
        if (this.creditCard == null || this.creditCard.equals("")) {
            this.newOrderMsg = "信用卡号不能为空";
            return false;
        }
        if (this.exprdate == null || this.exprdate.equals("")) {
            this.newOrderMsg = "日期不能为空";
            return false;
        }
        return true;
    }

}
