package web.petstore.inerWeb.servlet;

import web.petstore.domain.Cart;
import web.petstore.domain.CartItem;
import web.petstore.domain.Item;
import web.petstore.persistence.CartDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
public class CartFormServlet extends HttpServlet {

    private CartDao cartDao;
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
    private static final String CART_TABLE_PARTIAL = "/WEB-INF/jsp/cart/cartTable.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取当前用户ID，假设用户已经登录
        HttpSession session = req.getSession();
        String userId = null;

        if (session.getAttribute("loginAccount") != null) {
            userId = ((web.petstore.domain.Account) session.getAttribute("loginAccount")).getUsername();
        } else {
            // 如果用户未登录，重定向到登录页面
            resp.sendRedirect(req.getContextPath() + "/signOn");
            return;
        }

        cartDao = new CartDao();

        try {
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart == null) {
                cart = new Cart();
                Integer cartId = cartDao.getCartIdByUserId(userId);
                List<CartItem> cartItems = cartDao.getCartItems(cartId);
                cart.setCartItems(cartItems);
                session.setAttribute("cart", cart); // 将购物车保存到Session
            } else {
                // 如果购物车已存在，检查是否需要更新购物车商品列表
                int cartId = cartDao.getCartIdByUserId(userId);
                List<CartItem> cartItems = cartDao.getCartItems(cartId);
                cart.setCartItems(cartItems);
            }

            // 判断是否为 AJAX 请求
            String ajaxRequest = req.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equals(ajaxRequest)) {
                // 如果是 AJAX 请求，返回部分内容（如购物车表格）
                req.getRequestDispatcher(CART_TABLE_PARTIAL).forward(req, resp);
            } else {
                // 正常请求，渲染整个购物车页面
                req.getRequestDispatcher(CART_FORM).forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "加载购物车内容失败");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemId = req.getParameter("itemId");
        String quantityStr = req.getParameter("quantity");

        if (itemId != null && quantityStr != null) {
            try {
                int quantity = Integer.parseInt(quantityStr);

                if (quantity > 0) {
                    Cart cart = (Cart) req.getSession().getAttribute("cart");
                    if (cart != null) {
                        if (cart.containsItemId(itemId)) {
                            cart.setQuantityByItemId(itemId, quantity);
                        } else {
                            System.out.println("Item with ID " + itemId + " is not found in the current cart.");
                        }
                    }
                } else {
                    Cart cart = (Cart) req.getSession().getAttribute("cart");
                    if (cart != null) {
                        cart.removeItemById(itemId);
                    }
                }

                // AJAX 请求处理：返回更新后的购物车 HTML 和小计
                Cart cart = (Cart) req.getSession().getAttribute("cart");

                resp.setContentType("application/json");
                req.setAttribute("cart", cart);  // 将 cart 传递给 JSP
                req.setAttribute("subTotal", cart.getSubTotal());// 将小计传递给 JSP
                System.out.println(cart.getSubTotal()+"111111111111111");


                req.getRequestDispatcher(CART_TABLE_PARTIAL).forward(req, resp);

            } catch (NumberFormatException e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid quantity format");
            }
        }
    }
}
