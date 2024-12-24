//package web.petstore.inerWeb.servlet;
//
//import web.petstore.domain.Account;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//public class NewOrderFormServlet extends HttpServlet {
//
//    private static final String NEWORDER_FORM = "/WEB-INF/jsp/order/newOrder.jsp";
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session=req.getSession();
//        Account loginAccount =(Account) session.getAttribute("loginAccount");
//        if(loginAccount==null){
//            resp.sendRedirect("signonForm");
//        }
//        else{
//            req.getRequestDispatcher(NEWORDER_FORM).forward(req,resp);
//        }
//
//    }
//}


//xzx
//package web.petstore.inerWeb.servlet;
//
//import web.petstore.domain.Account;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//public class NewOrderFormServlet extends HttpServlet {
//
//    private static final String NEWORDER_FORM = "/WEB-INF/jsp/order/newOrder.jsp";
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session=req.getSession();
//        Account loginAccount =(Account) session.getAttribute("loginAccount");
//        if(loginAccount==null){
//            resp.sendRedirect("signonForm");
//        }
//        else{
//            req.getRequestDispatcher(NEWORDER_FORM).forward(req,resp);
//        }
//
//    }
//}
package web.petstore.inerWeb.servlet;

import web.petstore.domain.Account;
import web.petstore.domain.Cart;
import web.petstore.domain.CartItem;
import web.petstore.domain.Order;
import web.petstore.persistence.CartDao;
import web.petstore.persistence.ItemDao;
import web.petstore.persistence.LineItemDao;
import web.petstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class NewOrderFormServlet extends HttpServlet {

    private static final String NEWORDER_FORM = "/WEB-INF/jsp/order/newOrder.jsp";
    private final LogService logService = new LogService(); // 假设已经有 LogService 处理日志记录

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account loginAccount = (Account) session.getAttribute("loginAccount");
        String workingItemId = req.getParameter("workingItemId");
        CartDao cartDao = new CartDao();
        ItemDao itemDao = new ItemDao();
        LineItemDao lineItemDao = new LineItemDao();
        List<CartItem> listcartitem=null;
        int cartId = 0; // 获取购物车 ID
        int j = 0;
        int linenum = 0;
        String userId = null;
        userId = loginAccount.getUsername();
        Cart cart=(Cart) session.getAttribute("cart");
        try {
            cartId = cartDao.getCartIdByUserId(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            listcartitem=cartDao.getCartItems(cartId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (loginAccount == null) {
            // 用户未登录，重定向到登录页面
            resp.sendRedirect("signonForm");
        }
        try {
            if (cartDao.isCartEmpty(cartId)) {
                req.setAttribute("errorMessage", "您的购物车为空，请添加商品到购物车后再生成订单。");
                req.getRequestDispatcher("/WEB-INF/jsp/cart/cart.jsp").forward(req, resp);  // 重定向到购物车页面
                System.out.println("111111111111111112222");
                return;

            } else {
                // 记录日志 - 用户访问订单页面的操作


                String actionType = "新建订单";
                String actionDescription = "用户创建了一个新的订单";

                // 调用日志服务记录这次操作
                logService.logUserActivity(userId, actionType, actionDescription, workingItemId, null);

                // 转发到订单页面
                req.getRequestDispatcher(NEWORDER_FORM).forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
