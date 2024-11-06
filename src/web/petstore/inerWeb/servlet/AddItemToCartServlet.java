
package web.petstore.inerWeb.servlet;

import web.petstore.domain.Account;
import web.petstore.domain.Cart;
import web.petstore.domain.Item;
import web.petstore.persistence.CartDao;
import web.petstore.service.CatalogService;
import web.petstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class AddItemToCartServlet extends HttpServlet {


    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
    private CartDao cartDao = new CartDao(); // 初始化 CartDao
    private CatalogService catalogService = new CatalogService(); // 用于获取商品信息

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String workingItemId = req.getParameter("workingItemId"); // 获取当前操作的商品ID
        HttpSession session = req.getSession();
        String userId = null;

        if (session.getAttribute("loginAccount") != null) {
            userId = ((Account) session.getAttribute("loginAccount")).getUsername(); // 假设 Account 类中有 getUserId 方法
        } else {
            resp.sendRedirect(req.getContextPath() + "/signOn");
            return;
        }

        try {
            // 获取商品信息
            Item item = catalogService.getItem(workingItemId);
            if (item == null) {
                req.setAttribute("errorMessage", "无法找到该商品");
                req.getRequestDispatcher(CART_FORM).forward(req, resp);
                return;
            }
            boolean isInStock = catalogService.isItemInStock(workingItemId);


            // 添加商品到购物车
            int cartId = cartDao.getCartIdByUserId(userId); // 获取购物车 ID
            if (cartDao.itemExistsInCart(cartId, item.getItemId())) {
                cartDao.updateItemQuantity(userId, item.getItemId(), 1); // 如果存在，则更新数量
            } else {
                cartDao.addItemToCart(userId, item.getItemId(), 1, isInStock); // 不存在则插入新商品

            }

            // 获取当前用户的购物车内容
            Cart cart = new Cart();
            cart.setCartItems(cartDao.getCartItems(cartId)); // 设置购物车的商品

            session.setAttribute("cart", cart); // 将购物车对象放入session
            req.getRequestDispatcher(CART_FORM).forward(req, resp); // 转发到购物车页面

        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "添加商品到购物车失败");
            req.getRequestDispatcher("/WEB-INF/jsp/cart/error.jsp").forward(req, resp);

        }
    }

}
