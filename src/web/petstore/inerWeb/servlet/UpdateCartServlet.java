package web.petstore.inerWeb.servlet;

import web.petstore.domain.Account;
import web.petstore.domain.Cart;
import web.petstore.persistence.CartDao;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

public class UpdateCartServlet extends HttpServlet {

    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
    private CartDao cartDao = new CartDao(); // 初始化 CartDao

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // 获取登录账号信息
        Account loginAccount = (Account) session.getAttribute("loginAccount");
        if (loginAccount == null) {
            // 如果用户未登录，重定向到登录页面
            resp.sendRedirect(req.getContextPath() + "/signOn");
            return;
        }

        String userId = loginAccount.getUsername(); // 假设用户名用作唯一标识符

        try {
            // 遍历请求参数，查找以 "quantity_" 开头的数量输入项
            Enumeration<String> parameterNames = req.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                if (paramName.startsWith("quantity_")) {
                    String itemId = paramName.substring("quantity_".length()); // 提取 itemId
                    String quantityStr = req.getParameter(paramName);

                    if (quantityStr == null || quantityStr.isEmpty()) {
                        req.setAttribute("errorMessage", "商品数量无效");
                        req.getRequestDispatcher(CART_FORM).forward(req, resp);
                        return;
                    }

                    int quantity = Integer.parseInt(quantityStr);
                    if (quantity < 0) {
                        req.setAttribute("errorMessage", "商品数量不能为负");
                        req.getRequestDispatcher(CART_FORM).forward(req, resp);
                        return;
                    }

                    // 更新购物车中的商品数量
                    if (quantity > 0) {
                        cartDao.updateItemQuantity(userId, itemId, quantity);
                    } else {
                        cartDao.removeItemFromCart(userId, itemId); // 如果数量为0，则删除商品
                    }
                }
            }

            // 获取当前用户的购物车内容并更新 session
            Cart cart = new Cart();
            cart.setCartItems(cartDao.getCartItems(cartDao.getCartIdByUserId(userId))); // 从数据库中获取购物车商品
            session.setAttribute("cart", cart); // 更新session中的购物车对象

            // 重定向到购物车页面，显示更新后的购物车
            resp.sendRedirect(req.getContextPath() + "/cartForm");

        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "更新购物车失败，请重试");
            req.getRequestDispatcher("/WEB-INF/jsp/cart/error.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "商品数量无效，请输入有效的数字");
            req.getRequestDispatcher(CART_FORM).forward(req, resp);
        }
    }
}
