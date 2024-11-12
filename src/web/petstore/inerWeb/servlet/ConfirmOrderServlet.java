//package web.petstore.inerWeb.servlet;
//
//import web.petstore.domain.*;
//import web.petstore.persistence.CartDao;
//import web.petstore.persistence.ItemDao;
//import web.petstore.persistence.LineItemDao;
//import web.petstore.service.OrderService;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class ConfirmOrderServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        Order order = (Order) session.getAttribute("order");
//        Cart cart = (Cart)session.getAttribute("cart");
//        Order insertorder = new Order();
//        insertorder.setOrderId(order.getOrderId());
//        insertorder.setUsername(order.getUsername());
//        insertorder.setOrderDate(order.getOrderDate());
//        insertorder.setShipAddress1(order.getShipAddress1());
//        insertorder.setShipAddress2(order.getShipAddress2());
//        insertorder.setShipCity(order.getShipCity());
//        insertorder.setShipState(order.getShipState());
//        insertorder.setShipZip(order.getShipZip());
//        insertorder.setShipCountry(order.getShipCountry());
//        insertorder.setBillAddress1(order.getBillAddress1());
//        insertorder.setBillAddress2(order.getBillAddress2());
//        insertorder.setBillCity(order.getBillCity());
//        insertorder.setBillState(order.getBillState());
//        insertorder.setBillZip(order.getBillZip());
//        insertorder.setBillCountry(order.getBillCountry());
//        insertorder.setCourier(order.getCourier());
//        insertorder.setTotalPrice(order.getTotalPrice());
//        insertorder.setBillToFirstName(order.getBillToFirstName());
//        insertorder.setBillToLastName(order.getBillToLastName());
//        insertorder.setShipToFirstName(order.getShipToFirstName());
//        insertorder.setShipToLastName(order.getShipToLastName());
//        insertorder.setCreditCard(order.getCreditCard());
//        insertorder.setExpiryDate(order.getExpiryDate());
//        insertorder.setCardType(order.getCardType());
//        insertorder.setLocale(order.getLocale());
//        insertorder.setStatus("OK");
//
//
//
//        CartDao cartDao = new CartDao();
//        ItemDao itemDao=new ItemDao();
//        LineItemDao lineItemDao=new LineItemDao();
//        int cartId = 0; // 获取购物车 ID
//        int j=0;int linenum=0;
//        try {
//            cartId = cartDao.getCartIdByUserId(order.getUsername());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        List<CartItem> cartItems;  List<LineItem> lineItems=new ArrayList<>(); ;
//        try {
//            cartItems = cartDao.getCartItems(cartId);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        for (CartItem cartItem : cartItems)
//        {
//            linenum++;
//            LineItem lineItem=new LineItem();
//            lineItem.setOrderId(order.getOrderId());
//            lineItem.setLineNumber(linenum);
//            lineItem.setItemId(cartItem.getItem().getItemId());
//            lineItem.setQuantity(cartItem.getQuantity());
//            lineItem.setUnitPrice(cartItem.getItem().getListPrice());
//            lineItems.add(lineItem);
//        }
//        for (LineItem lineItem : order.getLineItems()) {
//            String itemId = lineItem.getItemId();
//            Integer increment = lineItem.getQuantity();
//            Map<String, Object> param = new HashMap<>(2);
//            param.put("itemId", itemId);
//            param.put("increment", increment);
//            itemDao.updateInventoryQuantity(param);  // 更新库存
//        }
//        for (LineItem lineItem : lineItems) {
//            try {
//                if(lineItemDao.insertLineItem(lineItem))
//                {
//                    j++;// 插入每个订单项
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        boolean flag;
//        if (cart.getNumberOfItems()==j)flag=true;
//        else flag=false;
//
//            OrderService orderService = new OrderService();
//        try {
//            if (orderService.insertOrder(insertorder)&&flag) {
//
//                if (session.getAttribute("cart") != null) {
//                    session.removeAttribute("cart");
//                }
//                resp.sendRedirect("finalOrderForm");
//            } else {
//                req.getRequestDispatcher("newOrderForm").forward(req, resp);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}


package web.petstore.inerWeb.servlet;

import web.petstore.domain.*;
import web.petstore.persistence.CartDao;
import web.petstore.persistence.ItemDao;
import web.petstore.persistence.LineItemDao;
import web.petstore.service.LogService;
import web.petstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfirmOrderServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");
        Cart cart = (Cart) session.getAttribute("cart");

        // 如果购物车为空，返回错误
        if (cart == null || cart.getNumberOfItems() == 0) {
            req.setAttribute("errorMessage", "您的购物车为空，请添加商品到购物车后再生成订单。");
            req.getRequestDispatcher("cartPage.jsp").forward(req, resp);  // 重定向到购物车页面
            return;
        }

        Order insertorder = new Order();
        insertorder.setOrderId(order.getOrderId());
        insertorder.setUsername(order.getUsername());
        insertorder.setOrderDate(order.getOrderDate());
        insertorder.setShipAddress1(order.getShipAddress1());
        insertorder.setShipAddress2(order.getShipAddress2());
        insertorder.setShipCity(order.getShipCity());
        insertorder.setShipState(order.getShipState());
        insertorder.setShipZip(order.getShipZip());
        insertorder.setShipCountry(order.getShipCountry());
        insertorder.setBillAddress1(order.getBillAddress1());
        insertorder.setBillAddress2(order.getBillAddress2());
        insertorder.setBillCity(order.getBillCity());
        insertorder.setBillState(order.getBillState());
        insertorder.setBillZip(order.getBillZip());
        insertorder.setBillCountry(order.getBillCountry());
        insertorder.setCourier(order.getCourier());
        insertorder.setTotalPrice(order.getTotalPrice());
        insertorder.setBillToFirstName(order.getBillToFirstName());
        insertorder.setBillToLastName(order.getBillToLastName());
        insertorder.setShipToFirstName(order.getShipToFirstName());
        insertorder.setShipToLastName(order.getShipToLastName());
        insertorder.setCreditCard(order.getCreditCard());
        insertorder.setExpiryDate(order.getExpiryDate());
        insertorder.setCardType(order.getCardType());
        insertorder.setLocale(order.getLocale());
        insertorder.setStatus("OK");

        CartDao cartDao = new CartDao();
        ItemDao itemDao = new ItemDao();
        LineItemDao lineItemDao = new LineItemDao();
        List<CartItem> listcartitem=null;
        int cartId = 0; // 获取购物车 ID
        int j = 0;
        int linenum = 0;

        try {
            cartId = cartDao.getCartIdByUserId(order.getUsername());
            listcartitem=cartDao.getCartItems(cartId);
            if (listcartitem == null) {

                req.setAttribute("errorMessage", "未找到购物车信息，请重新登录或联系管理员。");
                req.getRequestDispatcher("cart.jsp").forward(req, resp);
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<CartItem> cartItems;
        List<LineItem> lineItems = new ArrayList<>();

        try {
            cartItems = cartDao.getCartItems(cartId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (CartItem cartItem : cartItems) {
            linenum++;
            LineItem lineItem = new LineItem();
            lineItem.setOrderId(order.getOrderId());
            lineItem.setLineNumber(linenum);
            lineItem.setItemId(cartItem.getItem().getItemId());
            lineItem.setQuantity(cartItem.getQuantity());
            lineItem.setUnitPrice(cartItem.getItem().getListPrice());
            lineItems.add(lineItem);
        }

        // 更新库存
        for (LineItem lineItem : order.getLineItems()) {
            String itemId = lineItem.getItemId();
            Integer increment = lineItem.getQuantity();
            Map<String, Object> param = new HashMap<>(2);
            param.put("itemId", itemId);
            param.put("increment", increment);
            itemDao.updateInventoryQuantity(param);  // 更新库存
        }

        // 插入订单项
        for (LineItem lineItem : lineItems) {
            try {
                if (lineItemDao.insertLineItem(lineItem)) {
                    j++; // 插入每个订单项
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // 删除购物车中的商品
        for (LineItem lineItem : lineItems) {
            try {
                // 这里调用 CartDao 中的 removeItemFromCart 来删除商品
                cartDao.removeItemFromCart(order.getUsername(), lineItem.getItemId()); // 删除购物车中的商品
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // 判断是否成功生成订单
        boolean flag;
        if (cart.getNumberOfItems() == j) flag = true;
        else flag = false;

        OrderService orderService = new OrderService();
        try {
            if (orderService.insertOrder(insertorder) && flag) {
                resp.sendRedirect("finalOrderForm");
                //xzx  在此处将日志功能加入进来
                LogService logService =  new LogService();
                Account loginAccount = (Account) session.getAttribute("loginAccount");
                String userId = loginAccount.getUsername();
                LineItem lineItem=new LineItem();
                String itemId = lineItem.getItemId();
                String order_id  = String.valueOf(order.getOrderId());
                if (userId != null) { // 检查用户是否已登录
                    logService.logUserActivity(userId, "确认", "新建订单", itemId, order_id);
                }


            } else {
                req.getRequestDispatcher("newOrderForm").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
