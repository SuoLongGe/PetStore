package web.petstore.inerWeb.servlet;

import web.petstore.domain.Account;
import web.petstore.domain.Cart;
import web.petstore.persistence.CartDao;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class RemoveCartItemServlet extends HttpServlet {

    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
    private CartDao cartDao = new CartDao(); // 初始化 CartDao

    // 从购物车中删除商品
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // 获取当前用户信息
        Account loginAccount = (Account) session.getAttribute("loginAccount");
        if (loginAccount == null) {
            resp.sendRedirect("login.jsp"); // 如果用户未登录，重定向到登录页面
            return;
        }
        String userId = loginAccount.getUsername(); // 获取用户 ID

        try {
            // 获取要删除的商品 ID
            String itemId = req.getParameter("workingItemId");
            if (itemId == null || itemId.isEmpty()) {
                System.out.println("Item ID is null or empty"); // 日志
                req.setAttribute("errorMessage", "商品 ID 不存在");
                req.getRequestDispatcher(CART_FORM).forward(req, resp);
                return;
            }

            // 从购物车中删除商品
            cartDao.removeItemFromCart(userId, itemId);

            // 更新购物车内容
            Cart cart = new Cart();
            cart.setCartItems(cartDao.getCartItems(cartDao.getCartIdByUserId(userId))); // 设置购物车的商品

            // 将购物车对象更新到 session 中
            session.setAttribute("cart", cart);
            req.getRequestDispatcher(CART_FORM).forward(req, resp); // 转发到购物车页面

        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "删除商品失败");
            req.getRequestDispatcher("/WEB-INF/jsp/cart/error.jsp").forward(req, resp);
        }
    }
}
