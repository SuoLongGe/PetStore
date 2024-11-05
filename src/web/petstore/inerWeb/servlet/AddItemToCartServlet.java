
package web.petstore.inerWeb.servlet;

import web.petstore.domain.Account;
import web.petstore.domain.Cart;
import web.petstore.domain.Item;
import web.petstore.service.CatalogService;
import web.petstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取商品ID
        String workingItemId = req.getParameter("workingItemId");

        // 获取会话和购物车对象
        HttpSession session = req.getSession();

        if (session.getAttribute("loginAccount") != null) {
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart == null) {
                cart = new Cart();
            }
            // 记录操作日志的相关信息
            LogService logService = new LogService();
            Account loginAccount = (Account) session.getAttribute("loginAccount");
            String userId = loginAccount.getUsername();
            String activityType = "添加到购物车";
            String activityDetail = "用户将商品添加到购物车";

            if (session.getAttribute("isReload") != null) {
                // 检查购物车中是否已经包含该商品
                if (cart.containsItemId(workingItemId)) {
                    cart.incrementQuantityByItemId(workingItemId);
                    activityDetail += "，数量增加";
                } else {
                    CatalogService catalogService = new CatalogService();
                    boolean isInStock = catalogService.isItemInStock(workingItemId);
                    Item item = catalogService.getItem(workingItemId);
                    cart.addItem(item, isInStock);
                }
            }
            // 将购物车信息存入会话中
            session.setAttribute("cart", cart);
            String order_id = null;
            // 记录日志信息
            logService.logUserActivity(userId, activityType, activityDetail, workingItemId, order_id);

            // 转发请求到购物车页面
            req.getRequestDispatcher(CART_FORM).forward(req, resp);
        }
        else
        {
            resp.sendRedirect("signonForm");

        }
    }

}
