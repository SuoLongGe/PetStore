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

    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
    private CartDao cartDao = new CartDao();

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

        Cart cart = (Cart) session.getAttribute("cart");

        try {
            if (cart == null) {
                cart = new Cart();
                Integer cartId = null;
                try {
                    cartId = cartDao.getCartIdByUserId(userId);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                List<CartItem> cartItems = cartDao.getCartItems(cartId);
                cart.setCartItems(cartItems);
                session.setAttribute("cart", cart); // 将购物车保存到Session
            } else {
                // 如果购物车已存在，检查是否需要更新购物车商品列表
               int cartId = cartDao.getCartIdByUserId(userId);
                List<CartItem> cartItems = cartDao.getCartItems(cartId);
                cart.setCartItems(cartItems);
            }

            req.getRequestDispatcher(CART_FORM).forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "加载购物车内容失败");
            req.getRequestDispatcher("/WEB-INF/jsp/cart/error.jsp").forward(req, resp);
        }
    }


    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            for (CartItem cartItem : cart.getCartItemList()) {
                String itemId = cartItem.getItem().getItemId();
                String quantityStr = req.getParameter(itemId);
                if (quantityStr != null && !quantityStr.isEmpty()) {
                    try {
                        int quantity = Integer.parseInt(quantityStr);
                        if (quantity > 0) {
                            if (cart.containsItemId(itemId)) {
                                cart.setQuantityByItemId(itemId, quantity); // 更新购物车中商品的数量
                            } else {
                                System.out.println("Item with ID " + itemId + " is not found in the current cart.");
                            }
                        } else {
                            cart.removeItemById(itemId); // 删除商品
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity input for item ID " + itemId);
                        // Handle invalid input gracefully (maybe log it)
                    }
                }
            }
        }
        resp.sendRedirect(req.getRequestURI());  // 刷新页面，显示更新后的购物车
    }

}
